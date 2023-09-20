import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneServerPerTest

/**
 * Runs a browser based test against the application.
 *
 * http://doc.scalatest.org/3.0.0/index.html#org.scalatest.selenium.WebBrowser
 * http://www.scalatest.org/user_guide/using_selenium
 * https://www.playframework.com/documentation/latest/ScalaFunctionalTestingWithScalaTest#Testing-with-a-web-browser
 */
class BrowserSpec extends PlaySpec
  with OneBrowserPerTest
  with GuiceOneServerPerTest
  with HtmlUnitFactory {

  def $(str: String) = find(cssSelector(str)).getOrElse(throw new IllegalArgumentException(s"Cannot find $str"))

  "Application" should {
    
    "work from within a browser" in {
      System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")

      go to(s"http://localhost:$port/")

      find("header-title").get.text must equal("Done list")
        find("section-title").get.text must equal("11 Completed tasks found")
        
      find(cssSelector(".current")).get.text must equal("Displaying 1 to 10 of 11")

        click on $("#pagination li.next a")
        
        $("#pagination li.current").text must equal("Displaying 11 to 11 of 11")

        click on id("searchbox")
        enter("Went")
        click on id("searchsubmit")
        
        $("section h1").text must equal("2 Completed tasks found")
        click on linkText("Went grocery shopping")
        
        click on id("achieved")
        enter("xxx")
        submit()

        find(cssSelector("dl.error")) must not be empty
        $("dl.error label").text must equal("Achieved")

        click on id("achieved")
        enter("")
        submit()

        $("section h1").text must equal("11 Completed tasks found")
        $(".alert-message").text must equal("Done! Completed task Went grocery shopping has been updated")

        click on id("searchbox")
        enter("Washed")
        submit()
        
        click on linkText("Washed the dishes")
        click on $("input.danger")

        $("section h1").text must equal("10 Completed tasks found")
        $(".alert-message").text must equal("Done! Completed task has been deleted")
        
        click on $("#searchbox")
        enter("Washed")
        submit()  // $("#searchsubmit").click()
        
        $("section h1").text must equal("No completed tasks found")
    }
  }
}
