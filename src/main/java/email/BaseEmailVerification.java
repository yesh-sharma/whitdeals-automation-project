package email;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseEmailVerification implements EmailVerification {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseEmailVerification(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Open Gmail in a new tab
    protected void openGmail() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://mail.google.com/");
    }

    // Search for an email by subject
    protected boolean searchEmail(String subject) {
        try {
            openGmail();
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            searchBox.sendKeys(subject + Keys.ENTER);
            Thread.sleep(5000); // Wait for results

            return driver.findElements(By.xpath("//span[contains(text(),'" + subject + "')]")).size() > 0;
        } catch (Exception e) {
            System.out.println("Error while searching email: " + e.getMessage());
            return false;
        }
    }
    
    
    public abstract boolean verifyEmailReceived(String subject, String expectedContent);
}

