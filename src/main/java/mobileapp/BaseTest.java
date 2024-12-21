package mobileapp;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.commands.ScrollParameter.ScrollDirection;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

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
            dc.setCapability("app", "/Users/yeshsharma/Downloads/whitdeals1.apk");
            dc.setCapability("appPackage", "com.example.WhitdealsApp");
            dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
       
          
            driver = new AndroidDriver(new URL(appiumServerUrl), dc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
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
	
	     
	     

	 	WebElement dealButton = wait.until(ExpectedConditions
	 			.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
	 	dealButton.click();
	 	

	 	WebElement allDeal = wait.until(ExpectedConditions
	 			.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
	 	allDeal.click();
	 	
Thread.sleep(3000);
	 	
	 	

	 	
//Get the screen size
int screenHeight = driver.manage().window().getSize().getHeight();
int screenWidth = driver.manage().window().getSize().getWidth();

//Calculate the scroll distance (25% of the screen size)
int scrollDistance = screenHeight / 4;

//Define start and end points for the scroll
int startX = screenWidth / 2;
int startY = screenHeight / 2; // Middle of the screen
int endY = startY - scrollDistance;

//Maximum number of scrolls
int maxScrolls = 10;

for (int i = 0; i < maxScrolls; i++) {
 // Wait for the element to be visible before checking
 try {
     WebElement targetElement = new FluentWait<>(driver)
         .withTimeout(Duration.ofSeconds(10))
         .pollingEvery(Duration.ofMillis(500))
         .ignoring(NoSuchElementException.class)
         .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​*​*​*​\"]")));
     
      System.out.println(targetElement);

     System.out.println("Element found after " + (i + 1) + " scroll(s).");
     return; // Stop scrolling if the element is found
 } catch (Exception e) {
     System.out.println("Element not found, continuing to scroll...");
     // Continue scrolling if the element is not found
 }

 // Perform the scroll
 PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
 Sequence scroll = new Sequence(finger, 0);
 scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
 scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
 scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
 scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
 driver.perform(Arrays.asList(scroll));

 System.out.println("Scroll " + (i + 1) + " completed.");
}

System.out.println("Element not found after " + maxScrolls + " scroll(s).");

	 	
	 	
	 	
	 	
	 	
    }

	 	
    }
