package mobileapp;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {

    public AndroidDriver driver;

    @Test
    public void setUp() throws InterruptedException {
        try {
            String appiumServerUrl = "http://127.0.0.1:4723/";
            UiAutomator2Options dc = new UiAutomator2Options  ();
            dc.setCapability("platformName", "Android");
            dc.setCapability("deviceName", "emulator-5554");
            dc.setCapability("appium:automationName", "UiAutomator2");
            dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/whitdeals1.apk");
           // dc.setCapability("app","/Users/yeshsharma/Downloads/whitdeals1.apk");
            dc.setCapability("appPackage", "com.example.WhitdealsApp");
            dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
       
          
            driver = new AndroidDriver(new URL(appiumServerUrl), dc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        WebElement locationPermission = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
	     locationPermission.click(); 
        
	     WebElement allowNotification = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
	     allowNotification.click(); 
	     WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n"
	     		+ "Tab 5 of 5\")")));
	     menu.click(); 
	     WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
	     loginButton.click(); 
	     
	     
	     WebElement email = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
	     email.click();
	     email.sendKeys("saransh1@gmail.com");
	     WebElement password = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
	    password.click();
	     password.sendKeys("qwerty12");
	     
	     WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
	     submit.click(); 
	     
	     
	     WebElement cancleFaceID = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
	     Thread.sleep(3000);
	     cancleFaceID.click(); 
	   
    }
    
    
    
    
    
    
    
}
