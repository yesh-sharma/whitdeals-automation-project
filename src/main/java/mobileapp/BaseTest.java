package mobileapp;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {

    public AndroidDriver driver;

    @Test
    public void setUp() throws InterruptedException {
        try {
            String appiumServerUrl = "http://127.0.0.1:4723/";
            UiAutomator2Options dc = new UiAutomator2Options();
            dc.setCapability("platformName", "Android");
            dc.setCapability("deviceName", "emulator-5554");
            dc.setCapability("appium:automationName", "UiAutomator2");
            dc.setCapability("app", "/Users/yeshsharma/Downloads/whitdeals1.apk");
            dc.setCapability("appPackage", "com.example.WhitdealsApp");
            dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
            dc.setCapability("fullReset", false);

            driver = new AndroidDriver(new URL(appiumServerUrl), dc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        
        // Handle permissions
        WebElement locationPermission = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
        locationPermission.click();

        WebElement allowNotification = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
        allowNotification.click(); 

        // Navigate through the app
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\nTab 5 of 5\")")));
        menu.click();

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
        loginButton.click();
        
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
        email.click();
        email.sendKeys("yesh@zasyasolutions.com");

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
        password.click();
        password.sendKeys("Yesh255198@");

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
        submit.click();

        WebElement cancelFaceID = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
        Thread.sleep(3000);
        cancelFaceID.click();

        WebElement dealButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
        dealButton.click();

        WebElement allDeal = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
        allDeal.click();

        Thread.sleep(3000);
//
//        // Use your dynamic deal name here
        String deal = "​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​";  
//        String xpath = "new UiSelector().description(\'"+deal+"​\')";
//        System.out.println("Using dynamic XPath: " + xpath);  // Debug log
//
//        // Get screen size
//        int screenHeight = driver.manage().window().getSize().getHeight();
//        int screenWidth = driver.manage().window().getSize().getWidth();
//
//        // Scroll distance (25% of the screen size)
//        int scrollDistance = screenHeight / 3;
//
//        // Start and end points for scrolling
//        int startX = screenWidth / 2;
//        int startY = screenHeight / 2; // Middle of the screen
//        int endY = startY - scrollDistance;
//
//        // Maximum number of scrolls
//        int maxScrolls = 10;
//
//        for (int i = 0; i < maxScrolls; i++) {
//            // Try to find the element before scrolling
//            try {
//                WebElement targetElement = new FluentWait<>(driver)
//                        .withTimeout(Duration.ofSeconds(10))
//                        .pollingEvery(Duration.ofMillis(500))
//                        .ignoring(NoSuchElementException.class)
//                        .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​\")")));
//
//                System.out.println("Element found: " + targetElement);
//                System.out.println("Element found after " + (i + 1) + " scroll(s).");
//                return;  // Stop scrolling if the element is found
//            } catch (Exception e) {
//                System.out.println("Element not found, continuing to scroll...");
//            }
//
//            // Perform the scroll action
//            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//            Sequence scroll = new Sequence(finger, 0);
//            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
//            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//            scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
//            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//
//            driver.perform(Arrays.asList(scroll));  // Perform the scroll
//
//            System.out.println("Scroll " + (i + 1) + " completed.");
//        }
//
//        System.out.println("Element not found after " + maxScrolls + " scroll(s).");
//    }
        
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement webView = wait1.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator(
//            String.format(
//                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"%s\").instance(0))",
//                deal
//            )
//        )));
//        webView.click();
        
        
        
        
        

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
       	    // Try to locate the element
       	    WebElement webView = wait2.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator(
       	        String.format(
       	            "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"%s\").instance(0))",
       	            deal
       	        )
       	    )));
       	    // If the element is found, fail the test
       	    System.out.println("Element found: Test failed.");
       	    Assert.fail("Element with description \"" + deal + "\" was found, failing the test.");
       	} catch (TimeoutException e) {
       	    // If the element is not found, pass the test
       	    System.out.println("Element not found: Test passed.");
       	} catch (Exception e) {
       	    // Catch any other unexpected exceptions
       	    System.out.println("An unexpected error occurred: " + e.getMessage());
       	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
       	}
        
        
        
        
        
        
        
        
        
        

        
        
        
        
        
    }
}
