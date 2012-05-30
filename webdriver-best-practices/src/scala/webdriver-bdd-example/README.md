# webdriver-bdd-example

This is an example of doing Behavior-Driven Design style testing using Selenium WebDriver and ScalaTest.

## Executing the test code

To run the test code, install [sbt](https://github.com/harrah/xsbt/wiki) and then run the test target as follows:

    $ sbt test
    ... a bunch of building output ...
    [info] FoosbookLoginTest:
    [info] Foosbook Signup 
    [info]   Scenario: An incomplete signup should display an error
    [info]     Given a user is on the signup page 
    [info]     When user enters incomplete form data 
    [info]     And user submits the form 
    [info]     Then an error message is displayed 
    [info]   Scenario: Unmatching emails should display an error
    [info]     Given a user is on the signup page 
    [info]     When user enters form data with unmatched emails 
    [info]     And user submits the form 
    [info]     Then error message is displayed 
    [info]   Scenario: A complete signup should submit successfully
    [info]     Given a user is on the signup page 
    [info]     When user enters complete form data 
    [info]     And user submits the form 
    [info]     Then no error message is displayed 
    [info] Passed: : Total 3, Failed 0, Errors 0, Passed 3, Skipped 0
    [success] Total time: 25 s, completed May 29, 2012 9:31:58 PM


