package basetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginData {
	
	public WebDriver driver;
    public WebDriverWait wait; 
	
	 public LoginData(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
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
	        useremail.sendKeys("ankitsharmaz7086@gmail.com");

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
	
	

}
