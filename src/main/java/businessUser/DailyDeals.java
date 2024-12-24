package businessUser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;
import utility.ReuseableCode;

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
     // GET TOTAL CLAIM TEXT
     		WebElement TotalClaimedCountBox = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [1]"));
     		int claimedCountBeforeClaiming = Integer.parseInt(TotalClaimedCountBox.getText());
     		System.out.println("Initial Claimed Count on deals dashboard: " + claimedCountBeforeClaiming);
        
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
		
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dailyDealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
		dailyDealButton.click();
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
		allDeal.click();

		WebElement claimButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\").instance(0)")));
		claimButton.click();
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
		loginApplication();

		WebElement TotalClaimedCountBox2 = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [1]"));
		int claimedCountAfterClaiming = Integer.parseInt(TotalClaimedCountBox2.getText());

		Assert.assertEquals(claimedCountBeforeClaiming + 1, claimedCountAfterClaiming,
				"Claimed count did not increase by 1!");

		WebElement goToWalletButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Go To Wallet\")")));
		goToWalletButton.click();

		WebElement RemoveTheDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Remove Deal\")")));
		RemoveTheDeal.click();

		WebElement confirmButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Yes\")")));
		confirmButton.click();
		WebElement signOut3 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut3).perform();
		signOut3.click();
		loginApplication();

		WebElement TotalRedeemedCountBox = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [2]"));
		int redeemedCountbeforeRedeeming = Integer.parseInt(TotalRedeemedCountBox.getText());

		WebElement TotalClaimedCountBox3 = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [1]"));
		int claimedCountAfterRemoving = Integer.parseInt(TotalClaimedCountBox3.getText());

		Assert.assertEquals(claimedCountAfterClaiming - 1, claimedCountAfterRemoving,
				"Claimed count did not decrease by 1!");
		WebElement signOut4 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut4).perform();
		signOut4.click();
		claimButton.click();
		goToWalletButton.click();

		WebElement instantRedeem = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Instant Redeem\")")));
		instantRedeem.click();
		WebElement confirmOk = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")")));
		confirmOk.click();
		WebElement signOut5 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut5).perform();
		signOut5.click();
		loginApplication();

		WebElement TotalRedeemedCountBox1 = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [2]"));
		int redeemedCountAfterRedeeming = Integer.parseInt(TotalRedeemedCountBox1.getText());

		Assert.assertEquals(redeemedCountbeforeRedeeming, redeemedCountAfterRedeeming - 1,
				"Redeemed count did not increase by 1!");
		
		
		
		
		driver1.quit();
		
		
	}
	 //@Test(priority = 3)
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
	// @Test(priority = 4)
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
	
	
	//@Test(priority = 2,dependsOnMethods = {"createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal"})
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
	
	//@Test(priority = 5)
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
	//@Test(priority = 6)
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
	
	//@Test(priority = 7)
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
	//@Test(priority = 8)
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
