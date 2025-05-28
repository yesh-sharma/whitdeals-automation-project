package basetest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.ConfigReader;

public class Basetest {
    public WebDriver driver;
    public WebDriverWait wait;  // Shared WebDriverWait instance
    private static final int TIMEOUT_SECONDS = 60; // Centralized timeout

    ConfigReader config = new ConfigReader(); // Instance of ConfigReader

   

	@BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : config.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-notifications", "--incognito");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");
            options.addArguments("--disable-blink-features=AutomationControlled");
          

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.managed_default_content_settings.images", 2);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Browser " + browser + " is not supported.");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        
        // Initialize shared WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        PageFactory.initElements(driver, this);
    }

    public void loginApplication() throws InterruptedException {
        driver.get("https://staging.whitdeals.com.au/login");

        WebElement useremail = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_name")));
        useremail.sendKeys("yeshsharma516032@gmail.com");

        WebElement passwordEle = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_password")));
        passwordEle.sendKeys("Yesh255198@");

        WebElement submitbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitbutton.click();
    }

    public void loginApplicationAsCashier() throws InterruptedException {
        driver.get("https://staging.whitdeals.com.au/login");

        WebElement useremail = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_name")));
        useremail.sendKeys("hiceh26329@aqqor.com");

        WebElement passwordEle = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_password")));
        passwordEle.sendKeys("Yesh255198@");

        WebElement submitbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitbutton.click();
    }

    public void loginApplicationAsAdmin() throws InterruptedException {
        driver.get("https://staging.whitdeals.com.au/login");

        WebElement useremail = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_name")));
        useremail.sendKeys("admin");

        WebElement passwordEle = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_password")));
        passwordEle.sendKeys("whitdealsappadmin");

        WebElement submitbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitbutton.click();
    }
    
    
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        File destination = new File(destinationPath);
        FileUtils.copyFile(source, destination);
        return destinationPath;


}
    
    
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    } 
//    
    
    
    
    

}
