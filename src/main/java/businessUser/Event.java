package businessUser;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCode;

public class Event extends Basetest {


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
	@Test
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
	@Test
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
	
}
