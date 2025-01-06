package businessUser;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;
import utility.ReuseableCode;

public class EventWithMobileIntegration extends Basetest {

	
	// @Test(priority = 1)
	public void createEventByBusinessuserAndAdminApprovesTheEvent() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		String eventTitle = reuse.reusebaleCodeForEventCreationWithMobileIntegration();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement eventButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")));
		eventButton.click();
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement eventButton1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Events']")));
		eventButton1.click();

		WebElement allEvent = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Events\")")));
		allEvent.click();

		WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
		try {
			// Try to locate the element

			WebElement webView = wait2.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(

					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"create event for testing\").instance(0))"

			)

			));
			webView.click();
			// If the element is found, fail the test
			System.out.println("Element found: Test passed.");

		} catch (TimeoutException e) {
			// If the element is not found, pass the test
			System.out.println("Element not found: Test passed.");
			Assert.fail("Element was found, failing the test.");
		} catch (Exception e) {
			// Catch any other unexpected exceptions
			System.out.println("An unexpected error occurred: " + e.getMessage());
			Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
		}

		WebElement Interested = wait2.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(

				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"Interested\").instance(0))"

		)

		));

		Interested.click();

		Thread.sleep(3000);
		driver1.quit();
	}

	


	// @Test(priority = 4)
@Test
	public void createEventByBusinessUserAfterThatCancled() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ReuseableCode reuse = new ReuseableCode(driver);
		String event = reuse.reusebaleCodeForEventDashboardWithMobileIntegration() ;
		Actions actions = new Actions(driver);
		WebElement cancleTheEvent = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='cancel-btn']")));
		actions.moveToElement(cancleTheEvent).click().perform();

		Thread.sleep(1000);

		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
		YesToCancle.click();

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement eventButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")));
		eventButton.click();
		
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement eventButton1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Events']")));
		eventButton1.click();

		WebElement allEvent = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Events\")")));
		allEvent.click();

		WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
		try {
			// Try to locate the element

			WebElement webView = wait2.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(

					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"Event Created and Check Cancle Functionality\").instance(0))"

			)

			));
			webView.click();
			// If the element is found, fail the test
			System.out.println("Event found: Test Failed.");
			Assert.fail("Element was found, failing the test.");
		} catch (TimeoutException e) {
			// If the element is not found, pass the test
			System.out.println("Element not found: Test passed.");
			
		} catch (Exception e) {
			// Catch any other unexpected exceptions
			System.out.println("An unexpected error occurred: " + e.getMessage());
			Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
		}
		driver1.quit();

	}


}
