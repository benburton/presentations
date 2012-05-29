package com.benburton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FoosbookSignupTest {

	private static final String EMAIL_CONFIRMATION_SELECTOR = "input[name=email-confirm]";
	private static final String FIRST_NAME_SELECTOR = "input[name=first-name]";
	private static final String LAST_NAME_SELECTOR = "input[name=last-name]";
	private static final String EMAIL_SELECTOR = "input[name=email]";

	private static final String URL = "file:///Users/bburton/Documents/workspace/presentations/webdriver-best-practices/src/examples/foosbook/index.html";

	private WebDriver driver;

	@Before
	public void setup() {
		driver = new FirefoxDriver();
		driver.get(URL);
	}

	@Test
	public void testSubmitWithMissingFields() {
		submitForm();
		assertTrue(getErrorElement().isDisplayed());
	}

	@Test
	public void testSubmitWithUnmatchedEmails() {
		sendKeysToSelector(FIRST_NAME_SELECTOR, "Ben");
		sendKeysToSelector(LAST_NAME_SELECTOR, "Burton");
		sendKeysToSelector(EMAIL_SELECTOR, "bburton@theladders.com");
		sendKeysToSelector(EMAIL_CONFIRMATION_SELECTOR,
				"bburton@tehladders.com");
		submitForm();

		assertTrue(getErrorElement().isDisplayed());
		assertEquals("Your emails do not match. Please try again.",
				getErrorElement().getText());
	}

	@Test
	public void testSuccessfulSubmission() {
		sendKeysToSelector(FIRST_NAME_SELECTOR, "Ben");
		sendKeysToSelector(LAST_NAME_SELECTOR, "Burton");
		sendKeysToSelector(EMAIL_SELECTOR, "bburton@theladders.com");
		sendKeysToSelector(EMAIL_CONFIRMATION_SELECTOR,
				"bburton@theladders.com");
		sendKeysToSelector("input[type=password]", "test123");
		submitForm();

		assertFalse(getErrorElement().isDisplayed());
	}

	private WebElement getErrorElement() {
		return driver.findElement(By.cssSelector("#error-message"));
	}

	private void submitForm() {
		driver.findElement(By.cssSelector("input[type=submit]")).click();
	}

	private void sendKeysToSelector(String selector, String firstName) {
		driver.findElement(By.cssSelector(selector)).sendKeys(firstName);
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
