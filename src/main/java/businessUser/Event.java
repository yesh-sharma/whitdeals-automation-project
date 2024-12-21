package businessUser;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCode;

public class Event extends Basetest {

	@Test(priority = 1)
	public void createEventByBusinessuserAndAdminApprovesTheEvent() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

	}
	//@Test(priority = 2)
	public void createEventByBusinessuserAndAdminDeclineTheEventWithReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();
	
		WebElement declineButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		declineButton.click();  
             
	       
			WebElement declineMessage  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("decline_comment")));
			declineMessage.sendKeys("declining because of testing");
	             

             Thread.sleep(2000);
		WebElement confirmDeclineButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmDeclineButton.click();  
             
	
	}
	
	//@Test(priority = 3)
	public void createEventByBusinessuserAndAdminDeclineTheEventWithoutReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();
	
		WebElement approveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		approveButton.click();  
	         
			WebElement declineMessage  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("no_comment")));
			declineMessage.click();
			
	         Thread.sleep(2000);
		WebElement confirmApproveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmApproveButton.click();  
	
	}
	
	
	//@Test(priority = 4)
	public void createEventByBusinessUserAfterThatCancled() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventDashboard();
		Actions actions = new Actions(driver);
		WebElement cancleTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='cancel-btn']")));
		actions.moveToElement(cancleTheEvent).click().perform();
	
		Thread.sleep(1000);
		
		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
		YesToCancle.click();	
		
		
	}	
	
	//@Test(priority = 5)
	public void createEventByBusinessUserAndCloneTheEvent() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventDashboard();
		Actions actions = new Actions(driver);
		WebElement cloneTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Event'][normalize-space()='Clone']")));
		actions.moveToElement(cloneTheEvent).click().perform();
	    Thread.sleep(1000);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
