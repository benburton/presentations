import org.openqa.selenium.{By, WebDriver}

/**
 * Page object representation of Foosbook signup page.
 */
class FoosbookSignupPage(driver: WebDriver) {

  //val url = "http://benburton.github.com/presentations/webdriver-best-practices/src/examples/foosbook/"
  val url = "file:///Users/bburton/Documents/workspace/presentations/webdriver-best-practices/src/examples/foosbook/index.html"

  val firstNameSelector = By.cssSelector("input[name=first-name]")
  val lastNameSelector = By.cssSelector("input[name=last-name]")
  val emailSelector = By.cssSelector("input[name=email]")
  val emailConfirmSelector = By.cssSelector("input[name=email-confirm]")
  val passwordSelector = By.cssSelector("input[name=password]")
  val submitButtonSelector = By.cssSelector("input[type=submit]")
  val errorMessageSelector = By.cssSelector("#error-message")

  driver.get(url)

  /**
   * Enters form data to the Foosbook signup form
   */
  def enterSignupData(firstName: String = null,
                      lastName: String = null,
                      email: String = null,
                      emailConfirm: String = null,
                      password: String = null) = {
    enterData(firstNameSelector, Option(firstName))
    enterData(lastNameSelector, Option(lastName))
    enterData(emailSelector, Option(email))
    enterData(emailConfirmSelector, Option(emailConfirm))
    enterData(passwordSelector, Option(password))
  }

  /**
   * Enter data if passed a Some[String], but do not enter data if passed
   * a None.
   */
  private def enterData(selector: By, keys: Option[String]) =
    keys match {
      case keys: Some[String] =>
        driver.findElement(selector).sendKeys(keys.get)
      case _ =>
  }

  /**
   * Submits the signup form.
   */
  def submit() = driver.findElement(submitButtonSelector).click

  /**
   * Returns Some(String) with error message text if available,
   * None if there is no visible or located error message element.
   */
  def getErrorMessage(): Option[String] = {
    try {
      val errorElement = driver.findElement(errorMessageSelector)
      if (errorElement.isDisplayed) Some(errorElement.getText) else None
    }
    catch {
      case e: NoSuchElementException => {
        None
      }
    }
  }

}
