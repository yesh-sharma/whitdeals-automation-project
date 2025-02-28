package admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCodeForAdminModule;

public class EventAdmin extends Basetest {
	
	
	
	@Test(priority=1)
	public void createEventByAdminForbusinessUser() throws InterruptedException {
		
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
	    String  eventName = reuse.reusebaleCodeForEventCreation();
	    System.out.println(eventName);
	    

}
	
	
	@Test(priority=2)
	public void searchEventCreatedByAdminForBusinessUserAndCloneThatEvent() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
	    reuse.reusebaleCodeForEventdashboard();
	    Actions actions = new Actions(driver);
	
	    WebElement cloneTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Event'][normalize-space()='Clone']")));
		actions.moveToElement(cloneTheEvent).click().perform();
	    Thread.sleep(1000);
	
	}
	
	
	@Test(priority=3)
	public void searchEventCreatedByAdminForBusinessUserAndEditThatEvent() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
	    reuse.reusebaleCodeForEventdashboard();
	    Actions actions = new Actions(driver);
	    WebElement cloneTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@class='dropdown-item'][normalize-space()='Edit']")));
		actions.moveToElement(cloneTheEvent).click().perform();
	    Thread.sleep(1000);
	
	    WebElement eventDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
		actions.moveToElement(eventDescription).perform();
		eventDescription.sendKeys("Get ready to embark on a culinary adventure like noet Dining Experience! Indulge TEST 123232");
	
		WebElement submit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		actions.moveToElement(submit).perform();
		submit.click();
	     Thread.sleep(3000);
	
	
	}
	

	@Test(priority=4)
	public void searchEventCreatedByAdminForBusinessUserAndCancleThatEvent() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
	    reuse.reusebaleCodeForEventdashboard();
	    Actions actions = new Actions(driver);
	    WebElement cloneTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='cancel-btn']")));
		actions.moveToElement(cloneTheEvent).click().perform();
	    Thread.sleep(2000);
	
		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
		YesToCancle.click();
		Thread.sleep(2000);
		
	
	
	
	}
	
}