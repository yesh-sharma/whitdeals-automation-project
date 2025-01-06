package businessUser;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import utility.ReuseableCode;

@Test
public class DealWithMobile extends Basetest  {

	private AndroidDriver driver1;
	public void createDealByBusinessUserAndAdminApprovesTheDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
	    ReuseableCode reuse = new ReuseableCode(driver);
		String Dealname =reuse.reusebaleCodeFordealsCreation();
		System.out.println(Dealname);
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();
		
		try {
			String appiumServerUrl = "http://127.0.0.1:4723/";
			UiAutomator2Options dc = new UiAutomator2Options();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("appium:automationName", "UiAutomator2");
			//dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/whitdeals1.apk");
			 dc.setCapability("app","/Users/yeshsharma/Downloads/whitdeals1.apk");
			dc.setCapability("appPackage", "com.example.WhitdealsApp");
			dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
			
			
			
	

			driver1 = new AndroidDriver(new URL(appiumServerUrl), dc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		 FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1)
	                .withTimeout(Duration.ofSeconds(20))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(Exception.class);
		
			WebElement locationPermission = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
			locationPermission.click();

			WebElement allowNotification = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
			allowNotification.click();
			WebElement menu = wait1.until(ExpectedConditions.elementToBeClickable(
					AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
			menu.click();
			WebElement loginButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			loginButton.click();

			WebElement email = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
			email.click();
			email.sendKeys("saransh1@gmail.com");
			WebElement password = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
			password.click();
			password.sendKeys("qwerty12");

			WebElement submit = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			submit.click();

			WebElement cancleFaceID = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
			Thread.sleep(3000);
			cancleFaceID.click();

			WebElement dealButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
			dealButton.click();

			loginApplication();
			
			WebElement allDeal = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
			allDeal.click();
			
			WebElement click = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​\")")));
			click.click();
			
		
			
	}
	
	
	public void createDealByBusinessUserAndAdminApprovesTheDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
	    ReuseableCode reuse = new ReuseableCode(driver);
		String Dealname =reuse.reusebaleCodeForDailyDealsCreation();
		System.out.println(Dealname);
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();
		
		try {
			String appiumServerUrl = "http://127.0.0.1:4723/";
			UiAutomator2Options dc = new UiAutomator2Options();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("appium:automationName", "UiAutomator2");
			//dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/whitdeals1.apk");
			 dc.setCapability("app","/Users/yeshsharma/Downloads/whitdeals1.apk");
			dc.setCapability("appPackage", "com.example.WhitdealsApp");
			dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");

			driver1 = new AndroidDriver(new URL(appiumServerUrl), dc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		 FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1)
	                .withTimeout(Duration.ofSeconds(20))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(Exception.class);
		
			WebElement locationPermission = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
			locationPermission.click();

			WebElement allowNotification = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
			allowNotification.click();
			WebElement menu = wait1.until(ExpectedConditions.elementToBeClickable(
					AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
			menu.click();
			WebElement loginButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			loginButton.click();

			WebElement email = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
			email.click();
			email.sendKeys("saransh1@gmail.com");
			WebElement password = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
			password.click();
			password.sendKeys("qwerty12");

			WebElement submit = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			submit.click();

			WebElement cancleFaceID = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
			Thread.sleep(3000);
			cancleFaceID.click();

			WebElement dealButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
			dealButton.click();

			loginApplication();
			
			WebElement allDeal = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
			allDeal.click();
			
			
			WebElement claimButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\")")));
			claimButton.click();
			
			loginApplication();
			driver1.quit();
			
			
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}