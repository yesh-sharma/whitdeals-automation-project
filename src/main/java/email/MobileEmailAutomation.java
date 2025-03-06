package email;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;

@Test
public class MobileEmailAutomation {

	// @Test
	public void contactUs() throws InterruptedException {

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement menuButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
		menuButton.click();

		WebElement contactUsButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Contact us\")")));
		contactUsButton.click();

		WebElement name = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
		name.click();
		name.sendKeys("yesh");

		WebElement emailAddress = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
		emailAddress.click();
		emailAddress.sendKeys("yeshsharma516032@gmail.com");

		WebElement phoneNumber = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)")));
		phoneNumber.click();
		phoneNumber.sendKeys("23453523534");

		WebElement Enquiry = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)")));
		Enquiry.click();
		Enquiry.sendKeys("send princing");

		WebElement submitButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Contact Us\").instance(2)")));
		submitButton.click();
		
		Thread.sleep(5000);
		driver1.quit();
	}

	// @Test
	public void BusinessInfoPack() throws InterruptedException {

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement menuButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
		menuButton.click();

		WebElement BusinessInfoPack = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Business Info Pack\")")));
		BusinessInfoPack.click();

		WebElement name = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
		name.click();
		name.sendKeys("Yesh");

		WebElement phoneNumber = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
		phoneNumber.click();
		phoneNumber.sendKeys("2345235345");

		WebElement businessInfo = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)")));
		businessInfo.click();
		businessInfo.sendKeys("groww");

		WebElement email = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)")));
		email.click();
		email.sendKeys("Yeshsharma516032@gmail.com");

		WebElement businessWebsite = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)")));
		businessWebsite.click();
		businessWebsite.sendKeys("http://www.google.com");

		WebElement Enquiry = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(5)")));
		Enquiry.click();
		Enquiry.sendKeys("business info pack");

		WebElement submit = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Request Info Pack\")")));
		submit.click();
		Thread.sleep(5000);
		driver1.quit();

	}

	@Test
	public void reportAProblem() throws InterruptedException {

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement menuButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
		menuButton.click();

		driver1.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().description(\"REPORT A PROBLEM\"));"));

		WebElement reportAProblem = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"REPORT A PROBLEM\")")));
		reportAProblem.click();

		WebElement name = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
		name.click();
		name.sendKeys("yesh");

		WebElement email = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
		email.click();
		email.sendKeys("yeshsharma516032@gmail.com");

		WebElement mobileNumber = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)")));
		mobileNumber.click();
		mobileNumber.sendKeys("78434554545");

		WebElement problem = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"*What is the problem?\")")));
		problem.click();

		WebElement whitdealsAdmin = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"WhitDeals Admin\")")));
		whitdealsAdmin.click();

		WebElement issue = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)")));
		issue.click();
		issue.sendKeys("login issue");

		WebElement submit = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Next\")")));
		submit.click();

		Thread.sleep(3000);

		WebElement submit1 = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit\")")));
		submit1.click();
		Thread.sleep(5000);
		driver1.quit();

	}

}