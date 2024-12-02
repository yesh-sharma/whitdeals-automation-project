package businessUser;


import java.time.Duration;



import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import basetest.Basetest;
import utility.ReuseableCode;
@Test
public class Createdeal extends Basetest {
	 static int initialRemainingDealCountOnDashboard;
	 
	 static int initialRemainingDealCountOnDealsDashboard;
	 static int initialActiveDealCountOnDealsDashboard;

	  @Test(priority = 1)
	public void createdealBybusinessUserAndAdminApprovesThedeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		// get the deal count
		WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[1]")); // Replace with the correct locator
	    initialRemainingDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
        System.out.println("Initial Deal Count on dashboard: " + initialRemainingDealCountOnDashboard);
        
        
        WebElement dealDashboard  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deals']")));
		dealDashboard.click();  
        
		// get the deal count
				WebElement remainingDealCount = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [3]")); // Replace with the correct locator
			    initialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
		        System.out.println("Initial Deal Count on deals dashboard: " + initialRemainingDealCountOnDealsDashboard);
		        
		        
		        WebElement activedealCount = driver.findElement(By.xpath("//div[@class='display-5']")); // Replace with the correct locator
			    initialActiveDealCountOnDealsDashboard = Integer.parseInt(activedealCount.getText());
		        System.out.println("Initial Deal Count on deals dashboard: " + initialActiveDealCountOnDealsDashboard);
		       
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeFordealsCreation();
             
		WebElement approveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();  
             
             Thread.sleep(2000);
		WebElement confirmApproveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();  
             
         
        
         }
	

	public void createdealBybusinessUserAndAdminDeclineThedealWithReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeFordealsCreation();
             
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
	

public void createdealBybusinessUserAndAdminDeclineThedealWithoutReasonForDecline() throws InterruptedException {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	loginApplication();
	ReuseableCode reuse = new ReuseableCode(driver);
	reuse.reusebaleCodeFordealsCreation();
         
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

@Test(priority = 2,dependsOnMethods = {"createdealBybusinessUserAndAdminApprovesThedeal"})
public void dealCreatedNowCheckTheMainDashboardDealsCountAndAfterThatGoToDealDashboardAndCheckTheRemainingDealCountAndActiveDealcount() throws InterruptedException {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	loginApplication();

	WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[1]")); // Replace with the correct locator
	int updatedInitialRemainingDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
    System.out.println("Initial Deal Count on dashboard: " + updatedInitialRemainingDealCountOnDashboard);
    Assert.assertEquals(updatedInitialRemainingDealCountOnDashboard, initialRemainingDealCountOnDashboard - 1, "Deal count did not decrease by 1!" );

    WebElement dealDashboard  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deals']")));
	dealDashboard.click();  
    

	WebElement remainingDealCount = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [3]")); // Replace with the correct locator
	int updatedInitialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
    System.out.println("Initial Deal Count on deals dashboard: " + updatedInitialRemainingDealCountOnDealsDashboard);
    Assert.assertEquals(updatedInitialRemainingDealCountOnDealsDashboard, initialRemainingDealCountOnDealsDashboard - 1, "Deal count did not decrease by 1!" );
    
    
    
    WebElement activedealCount = driver.findElement(By.xpath("//div[@class='display-5']")); // Replace with the correct locator
	int updatedInitialActiveDealCountOnDealsDashboard = Integer.parseInt(activedealCount.getText());
    System.out.println("Initial Deal Count on deals dashboard: " + updatedInitialActiveDealCountOnDealsDashboard);
    Assert.assertEquals(updatedInitialActiveDealCountOnDealsDashboard, initialActiveDealCountOnDealsDashboard + 1, "Active count did not increase by 1!" );




}


}
