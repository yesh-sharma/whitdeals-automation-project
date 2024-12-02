package mobileapp;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.github.ashwith.flutter.FlutterFinder;
import io.github.ashwith.flutter.FlutterElement;
public class BaseTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        try {
            // Setting desired capabilities for the Android device
            String appiumServerUrl = "http://127.0.0.1:4723";
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("platformName", "Android"); // Ensure correct capitalization
            dc.setCapability("deviceName", "emulator-5554");
            dc.setCapability("automationName", "Flutter"); // Use Flutter for a Flutter app
            dc.setCapability("app", "/Users/yeshsharma/Downloads/app-debug.apk");
            dc.setCapability("appPackage", "com.example.WhitdealsApp");
            dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");

            // Initialize the AndroidDriver with Appium server and capabilities
            driver = new AndroidDriver(new URL(appiumServerUrl), dc);
            System.out.println("AppiumDriver initialized successfully.");
        } catch (MalformedURLException e) {
            System.err.println("Invalid Appium Server URL!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during driver initialization:");
            e.printStackTrace();
        }
    }

    @Test
    public void multiply() {
        try {
            // Instantiate the FlutterFinder object
            FlutterFinder finder = new FlutterFinder(driver);

            // Locate the "allow" button and perform a click action
          //  driver.findElement(finder.byText("allow")).click();
            System.out.println("Clicked on 'allow' button.");
        } catch (Exception e) {
            System.err.println("Error during test execution:");
            e.printStackTrace();
        }
    }
}
































//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
//
//import java.net.URL;
//import java.time.Duration;
//
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
//
//public class BaseTest {
//
//	public AndroidDriver driver;
//
//	@BeforeMethod
//
//	public void setUp() throws InterruptedException {
//		try {
//			String appiumServerUrl = "http://127.0.0.1:4723";
//			DesiredCapabilities dc = new DesiredCapabilities();
//
//			dc.setCapability("deviceName", "emulator 5554");
//
//			dc.setCapability("appPackage", "com.example.WhitdealsApp");
//			dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
//
//			dc.setCapability("platformName", "Android");
//			dc.setCapability("appium:automationName", "UiAutomator2");
//			dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/whitdeals2.apk");
//
//			driver = new AndroidDriver(new URL(appiumServerUrl), dc);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		FluentWait<AndroidDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(50))
//				.pollingEvery(Duration.ofMillis(2000)).ignoring(Exception.class);
//		WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(
//				AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")));
//		element11.click();
//		WebElement element1 = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Email\")")));
//		element1.sendKeys("yesh@zasyasolutions.com");
//		WebElement element2 = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")")));
//		element2.sendKeys("Yesh255198@");
//		WebElement element3 = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Login\")")));
//		element3.click();
//		Thread.sleep(8000);
//
//		// com.android.permissioncontroller:id/permission_allow_button
//
//	}
//
//}
