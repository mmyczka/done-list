
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class ModelSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {
  import models._

  import scala.concurrent.ExecutionContext.Implicits.global

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(date) == str
  }
  
  // --

  def completedTaskService: CompletedTaskRepository = app.injector.instanceOf(classOf[CompletedTaskRepository])

  "Completed task model" should {

    "be retrieved by id" in {
      whenReady(completedTaskService.findById(8)) { maybeTask =>
        val taskCleaning = maybeTask.get

        taskCleaning.name must equal("Washed the dishes")
        taskCleaning.achieved.value must matchPattern {
          case date:java.util.Date if dateIs(date, "2023-09-18 12:37") =>
        }
      }
    }
    
    "be listed along its companies" in {
        whenReady(completedTaskService.list()) { tasks =>

          tasks.total must equal(11)
          tasks.items must have length(10)
        }
    }
    
    "be updated if needed" in {

      val result = completedTaskService.findById(21).flatMap { computer =>
        completedTaskService.update(7, CompletedTask(name="Washer",
         reflections=None, 
         categoryId=Some(1), 
         termId=Some(1), 
         achieved=None)).flatMap { _ =>
          completedTaskService.findById(7)
        }
      }

      whenReady(result) { task =>
        val wash = task.get

        wash.name must equal("Washer")
        wash.achieved mustBe None
      }
    }
  }
}
