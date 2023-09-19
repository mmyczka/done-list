package controllers

import javax.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import views._

import scala.concurrent.{ExecutionContext, Future}
import models.{CompletedTaskRepository, CompletedTask}
import models.CategoryRepository
import models.TermRepository
import java.util.Locale.Category

/**
  * Manage a database of computers
  */
class HomeController @Inject()(taskService: CompletedTaskRepository,
                               categoryService: CategoryRepository,
                               termService: TermRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  /**
    * This result directly redirect to the application home.
    */
  val Home = Redirect(routes.HomeController.list(0, 2, ""))

  /**
    * Describe the task form (used in both edit and create screens).
    */
  val computerForm = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),
      "name" -> nonEmptyText,
      "reflections" -> optional(text),
      "category" -> optional(longNumber),
      "term" -> optional(longNumber),
      "achieved" -> optional(date("yyyy-MM-dd HH:mm")),
    )(CompletedTask.apply)(CompletedTask.unapply)
  )

  // -- Actions

  /**
    * Handle default path requests, redirect to computers list
    */
  def index = Action {
    Home
  }

  /**
    * Display the paginated list of computers.
    *
    * @param page    Current page number (starts from 0)
    * @param orderBy Column to be sorted
    * @param filter  Filter applied on computer names
    */
  def list(page: Int, orderBy: Int, filter: String) = Action.async { implicit request =>
    taskService.list(page = page, orderBy = orderBy, filter = ("%" + filter + "%")).map { page =>
      Ok(html.list(page, orderBy, filter))
    }
  }

  /**
    * Display the 'edit form' of a existing Completed task.
    *
    * @param id Id of the task to edit
    */
  def edit(id: Long) = Action.async { implicit request =>
    for{
      task <- taskService.findById(id)
      options <- categoryService.options
      terms <- termService.options
    } yield {
      task match {
        case Some(task) => Ok(html.editForm(id, computerForm.fill(task), options, terms))
        case _ => NotFound
      }
      }
    }

  /**
    * Handle the 'edit form' submission
    *
    * @param id Id of the computer to edit
    */
  def update(id: Long) = Action.async { implicit request =>
    computerForm.bindFromRequest().fold(
      formWithErrors => {
        logger.warn(s"form error: $formWithErrors")
        for {
          options <- categoryService.options
          terms <- termService.options
        } yield {
          BadRequest(html.editForm(id, formWithErrors, options, terms))
        }
      },
      computer => {
        taskService.update(id, computer).map { _ =>
          Home.flashing("success" -> "Completed task %s has been updated".format(computer.name))
        }
      }
    )
  }

  /**
    * Display the 'new computer form'.
    */
  def create = Action.async { implicit request =>
    for{
      options <- categoryService.options
      terms <- termService.options
    } yield {
      Ok(html.createForm(computerForm, options, terms))
    }
  }

  /**
    * Handle the 'new computer form' submission.
    */
  def save = Action.async { implicit request =>
    computerForm.bindFromRequest().fold(
      formWithErrors => 
        for{
          options <- categoryService.options
          terms <- termService.options
        } yield {
          BadRequest(html.createForm(formWithErrors, options, terms))
      },
      computer => {
        taskService.insert(computer).map { _ =>
          Home.flashing("success" -> "Completed task %s has been created".format(computer.name))
        }
      }
    )
  }

  /**
    * Handle computer deletion.
    */
  def delete(id: Long) = Action.async {
    taskService.delete(id).map { _ =>
      Home.flashing("success" -> "Completed task has been deleted")
    }
  }

}
