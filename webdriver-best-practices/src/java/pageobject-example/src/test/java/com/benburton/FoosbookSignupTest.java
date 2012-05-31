package com.benburton;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FoosbookSignupTest {

	private static final String URL = "file:///Users/bburton/Documents/workspace/presentations/webdriver-best-practices/src/examples/foosbook/index.html";

	private WebDriver driver;

	@Before
	public void setup() {
		driver = new FirefoxDriver();
	}

	@Test
	public void testSuccessfulSubmission() {

		driver.get(URL);

		sendText("input[name=first-name]", "Ben");
		sendText("input[name=last-name]", "Burton");
		sendText("input[name=email]", "bburton@theladders.com");
		sendText("input[name=email-confirm]", "bburton@theladders.com");
		sendText("input[type=password]", "test123");

		driver.findElement(By.cssSelector("input[type=submit]")).click();

		assertFalse(driver.findElement(By.cssSelector("#error-message"))
				.isDisplayed());
	}

	private void sendText(String cssSelector, String text) {
		driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
