package utility;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;



import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class MobileUtils {

    private AndroidDriver driver1;

    // Initialize the Mobile Driver
    public AndroidDriver initializeMobileDriver() {
        try {
            String appiumServerUrl = "http://127.0.0.1:4723/";
            UiAutomator2Options dc = new UiAutomator2Options();
            dc.setCapability("platformName", "Android");
            dc.setCapability("deviceName", "emulator-5554");
            dc.setCapability("appium:automationName", "UiAutomator2");
            dc.setCapability("app", "/Users/yeshsharma/Downloads/whitdeals1.apk");
            dc.setCapability("appPackage", "com.example.WhitdealsApp");
            dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");

            driver1 = new AndroidDriver(new URL(appiumServerUrl), dc);
            
            
            

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
   			email.sendKeys("yesh@zasyasolutions.com");
   			WebElement password = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
   					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
   			password.click();
   			password.sendKeys("Yesh255198@");

   			WebElement submit = wait1.until(ExpectedConditions
   					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
   			submit.click();

   			WebElement cancleFaceID = wait1.until(ExpectedConditions
   					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
   			Thread.sleep(3000);
   			cancleFaceID.click();
         
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the mobile driver: " + e.getMessage());
        }
        return driver1;
    }

    
    
}

