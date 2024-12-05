package businessUser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCode;
@Test
public class DailyDeals extends Basetest {
	
	 static int initialRemainingDailyDealCountOnDashboard;
	 
	 static int initialRemainingDealCountOnDealsDashboard;
	 static int initialActiveDealCountOnDealsDashboard;
	
	 @Test(priority=1)
	public void createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		
		WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[2]")); // Replace with the correct locator
	    initialRemainingDailyDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
        System.out.println("Initial Deal Count on dashboard: " + initialRemainingDailyDealCountOnDashboard);
        
        
        WebElement dealDashboard  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deals']")));
		dealDashboard.click();  
        
		// get the deal count
				WebElement remainingDealCount = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [3]")); // Replace with the correct locator
			    initialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
		        System.out.println("Initial Deal Count on deals dashboard: " + initialRemainingDealCountOnDealsDashboard);
		        
		        
		        WebElement activedealCount = driver.findElement(By.xpath("//div[@class='display-5']")); // Replace with the correct locator
			    initialActiveDealCountOnDealsDashboard = Integer.parseInt(activedealCount.getText());
		        System.out.println("Initial Deal Count on deals dashboard: " + initialActiveDealCountOnDealsDashboard);
		
	
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealsCreation();
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

	}
	
	public void createDailyDealByBusinessUserAndAdmindeclineTheDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealsCreation();

		WebElement declineButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		declineButton.click();

		WebElement declineMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decline_comment")));
		declineMessage.sendKeys("declining because of testing");

		Thread.sleep(2000);
		WebElement confirmDeclineButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmDeclineButton.click();


		
		
	}
	
	public void createDailyDealBybusinessUserAndAdminDeclineThedealWithoutReasonForDecline() throws InterruptedException {
	
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealsCreation();

		WebElement declineButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		declineButton.click();

		WebElement declineMessage  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("no_comment")));
		declineMessage.click();
		
	

         Thread.sleep(2000);
	WebElement confirmApproveButton  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
	confirmApproveButton.click();  
         

}
	
	
	@Test(priority = 2,dependsOnMethods = {"createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal"})
	public void dailyDealCreatedNowCheckTheMainDashboardDailyDealsCountAndAfterThatGoToDailyDealDashboardAndCheckTheRemainingDailyDealCountAndActiveDailyDealcount() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

		WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[2]")); // Replace with the correct locator
		int updatedInitialRemainingDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
	    System.out.println("Initial Deal Count on dashboard: " + updatedInitialRemainingDealCountOnDashboard);
	    Assert.assertEquals(updatedInitialRemainingDealCountOnDashboard, initialRemainingDailyDealCountOnDashboard - 1, "Deal count did not decrease by 1!" );

	    WebElement dealDashboard  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deals']")));
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
	

	public void makeDailyDealAndCancleTheDealByBusinessUser() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboard();
		
	
		Actions actions = new Actions(driver);
		WebElement cancleButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='puase-btn']")));
		actions.moveToElement(cancleButton).click().perform();

		Thread.sleep(1000);

		WebElement confirmation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='showAction()']")));
		actions.moveToElement(confirmation).click().perform();

		Thread.sleep(1000);
		WebElement cancleTheDeal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancel-btn")));
		cancleTheDeal.click();
		Thread.sleep(1000);
		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='yes']")));
		YesToCancle.click();
		Thread.sleep(2000);
		WebElement canclellationReason = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancellation_reason")));
		canclellationReason.sendKeys("testing this functionality");

		WebElement submit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
		submit.click();
	}
	@Test
	public void makeDailyDealAndRTZTheDailyDealByBusinessUser() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboard();
		
	
		Actions actions = new Actions(driver);
		WebElement cancleButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='puase-btn']")));
		actions.moveToElement(cancleButton).click().perform();

		Thread.sleep(1000);
		WebElement confirmation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='showAction()']")));
		actions.moveToElement(confirmation).click().perform();

		Thread.sleep(1000);
		WebElement cancleTheDeal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rtz-btn")));
		cancleTheDeal.click();
		Thread.sleep(1000);
		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='yes']")));
		YesToCancle.click();
		
	}
	
	
	public void makeDailyDealAndPauseTheDeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboard();
		
	
		Actions actions = new Actions(driver);
		WebElement pauseButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='pauseIcon']")));
		actions.moveToElement(pauseButton).click().perform();

		Thread.sleep(1000);

		WebElement yesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yesPauseDeal")));
		actions.moveToElement(yesButton).click().perform();
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		WebElement pauseDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseDate")));
		pauseDate.sendKeys(formattedDate);

		// Get the current local time in 24-hour format
		LocalTime currentTime = LocalTime.now();

		// Add 4 hours and 35 minutes to the current time
		LocalTime newTime = currentTime.plusHours(4).plusMinutes(35);

		// Format the new time in HH:mm (24-hour format)
		String formattedTime = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));
//
		WebElement pauseTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseTime")));
		pauseTime.clear();
		pauseTime.sendKeys(formattedTime);
		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPauseDeal")));
		confirm.click();

		
		
	}
	
	public void makeDailyDealAndCloneTheDailyDeal() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboard();
		
	
		Actions actions = new Actions(driver);
		WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Deal'][normalize-space()='Clone']")));
		actions.moveToElement(pauseButton).click().perform();

		Thread.sleep(1000);
		WebElement YesToClone = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));
		YesToClone.click();
		Thread.sleep(3000);
		
		
		
		
	}
}
