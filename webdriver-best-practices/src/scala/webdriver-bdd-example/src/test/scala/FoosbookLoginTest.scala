import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}

class FoosbookLoginTest extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  var driver: WebDriver = _

  before {
    driver = new FirefoxDriver()
  }

  feature("Foosbook Signup") {

    scenario("An incomplete signup should display an error") {

      given("a user is on the signup page")
      val signupPage = new FoosbookSignupPage(driver)

      when("user enters incomplete form data")
      signupPage.enterSignupData(
        firstName = "Ben",
        lastName = "Burton"
      )

      and("user submits the form")
      signupPage.submit

      then("an error message is displayed")
      assert(signupPage.getErrorMessage != None)

    }

    scenario("Unmatching emails should display an error") {

      given("a user is on the signup page")
      val signupPage = new FoosbookSignupPage(driver)

      when("user enters form data with unmatched emails")
      signupPage.enterSignupData(
        firstName = "Ben",
        lastName = "Burton",
        email = "bburton@theladders.com",
        emailConfirm = "benjamin.joseph.burton@gmail.com",
        password = "test123"
      )

      and("user submits the form")
      signupPage.submit

      then("error message is displayed")
      assert(signupPage.getErrorMessage != None)

    }

    scenario("A complete signup should submit successfully") {

      given("a user is on the signup page")
      val signupPage = new FoosbookSignupPage(driver)

      when("user enters complete form data")
      signupPage.enterSignupData(
        firstName = "Ben",
        lastName = "Burton",
        email = "bburton@theladders.com",
        emailConfirm = "bburton@theladders.com",
        password = "test123"
      )

      and("user submits the form")
      signupPage.submit

      then("no error message is displayed")
      assert(signupPage.getErrorMessage() == None)

    }

  }

  after {
    driver.close()
  }

}
