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
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("emulator-5554");
            options.setAutomationName("UiAutomator2");
            options.setApp("/Users/yeshsharma/Downloads/whitdeals1.apk");
            options.setAppPackage("com.example.WhitdealsApp");
            options.setAppActivity("com.example.WhitdealsApp.MainActivity");

            driver1 = new AndroidDriver(new URL(appiumServerUrl), options);

            FluentWait<AndroidDriver> wait = new FluentWait<>(driver1)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class);

            // Handle location permission
            WebElement locationPermission = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
            locationPermission.click();

            try {
                // Handle notifications permission
                WebElement allowNotification = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
                allowNotification.click();
            } catch (Exception e) {
                System.out.println("Error handling notifications permission: " + e.getMessage());
                e.printStackTrace();
            }

            // Navigate through the app
            WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\nTab 5 of 5\")")));
            menu.click();

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
            loginButton.click();

            WebElement email = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
            email.click();
            email.sendKeys("yesh@zasyasolutions.com");

            WebElement password = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            password.click();
            password.sendKeys("Yesh255198@");

            WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
            submit.click();
            Thread.sleep(7000);
            
                // Handle notifications permission
            	   WebElement cancelFaceID = wait.until(ExpectedConditions.elementToBeClickable(
                           AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
                   cancelFaceID.click();
         

         

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the mobile driver: " + e.getMessage());
        }
        return driver1;
    }

    // Tear down driver
    public void tearDown() {
        if (driver1 != null) {
            driver1.quit();
        }
    }
}
