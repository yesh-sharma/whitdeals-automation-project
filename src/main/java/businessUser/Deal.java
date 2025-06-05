package businessUser;

import java.time.Duration;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;

import basetest.Basetest;
import pageobjectmodal.DailyDealsPage;
import pageobjectmodal.DashboardPageForDailyDeal;
import utility.ReuseableCode;

//@Test
public class Deal extends Basetest {

	static int initialRemainingDealCountOnDashboard;

	static int initialRemainingDealCountOnDealsDashboard;
	static int initialActiveDealCountOnDealsDashboard;
	


	
	//@Test(priority = 1)
	public void createdealBybusinessUserAndAdminApprovesTheDeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

		// Get the initial deal count on dashboard
		WebElement remainingDealCountElement = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='display-5'])[1]"))
		);
		initialRemainingDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
		System.out.println("Initial Deal Count on dashboard: " + initialRemainingDealCountOnDashboard);

		// Get total claimed count
		WebElement totalClaimedCountBox = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='display-5 mt-2'])[1]"))
		);
		int claimedCountBeforeClaiming = Integer.parseInt(totalClaimedCountBox.getText());
		System.out.println("Initial Claimed Count on deals dashboard: " + claimedCountBeforeClaiming);

		// Click on Deals Dashboard
		WebElement dealDashboard = wait.until(
			ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Deals']"))
		);
		dealDashboard.click();

		// Get remaining deal count on Deals Dashboard
		WebElement remainingDealCount = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='display-5 mt-2'])[3]"))
		);
		initialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
		System.out.println("Initial Deal Count on deals dashboard: " + initialRemainingDealCountOnDealsDashboard);

		// Get active deal count
		WebElement activeDealCount = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='display-5']"))
		);
		initialActiveDealCountOnDealsDashboard = Integer.parseInt(activeDealCount.getText());
		System.out.println("Initial Active Deal Count on deals dashboard: " + initialActiveDealCountOnDealsDashboard);

		// Reuse the deal creation code
		ReuseableCode reuse = new ReuseableCode(driver);
		String dealName = reuse.reusebaleCodeFordealsCreation();

		// Approve the deal
		WebElement approveButton = wait.until(
			ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Approve']"))
		);
		approveButton.click();

		Thread.sleep(2000); // Optional: try to avoid static waits

		WebElement confirmApproveButton = wait.until(
			ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Approve']"))
		);
		confirmApproveButton.click();
	}


	// @Test(priority = 4)
	public void createdealBybusinessUserAndAdminDeclineThedealWithReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeFordealsCreation();

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

	// @Test(priority = 3)
	public void createdealBybusinessUserAndAdminDeclineThedealWithoutReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeFordealsCreation();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		approveButton.click();

		WebElement declineMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("no_comment")));
		declineMessage.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmApproveButton.click();

	}

//	 @Test(priority = 2, dependsOnMethods = {
//	 "createdealBybusinessUserAndAdminApprovesTheDeal" })
	public void dealCreatedNowCheckTheMainDashboardDealsCountAndAfterThatGoToDealDashboardAndCheckTheRemainingDealCountAndActiveDealcount()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

		WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[1]"));
		int updatedInitialRemainingDealCountOnDashboard = Integer.parseInt(remainingDealCountElement.getText());
		System.out.println("Initial Deal Count on dashboard: " + updatedInitialRemainingDealCountOnDashboard);
		Assert.assertEquals(updatedInitialRemainingDealCountOnDashboard, initialRemainingDealCountOnDashboard - 1,
				"Deal count did not decrease by 1!");

		WebElement dealDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deals']")));
		dealDashboard.click();

		WebElement remainingDealCount = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [3]"));
		int updatedInitialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
		System.out
				.println("Initial Deal Count on deals dashboard: " + updatedInitialRemainingDealCountOnDealsDashboard);
		Assert.assertEquals(updatedInitialRemainingDealCountOnDealsDashboard,
				initialRemainingDealCountOnDealsDashboard - 1, "Deal count did not decrease by 1!");

		WebElement activedealCount = driver.findElement(By.xpath("//div[@class='display-5']"));
		int updatedInitialActiveDealCountOnDealsDashboard = Integer.parseInt(activedealCount.getText());
		System.out.println("Initial Deal Count on deals dashboard: " + updatedInitialActiveDealCountOnDealsDashboard);
		Assert.assertEquals(updatedInitialActiveDealCountOnDealsDashboard, initialActiveDealCountOnDealsDashboard + 1,
				"Active count did not increase by 1!");

	}
	
   
	 //@Test(priority = 5)
	public void makeDealAndCancleTheDealByBusinessUser() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		String createdDealName = reuse.reusebaleCodeForDealDashboard();

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
	public void makeDealAndRtzTheDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		
		reuse.reusebaleCodeForDealDashboard();
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
		 
		Thread.sleep(5000);
		
	
		
	}


 	
	
    @FindBy(id = "pauseDate")
    private WebElement pauseDateField;

     //@Test(priority = 7)
	public void makeDealAndPauseTheDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		String DealName =reuse.reusebaleCodeForDealDashboard();
		System.out.println("yash123"+DealName);
		Actions actions = new Actions(driver);

		WebElement pauseButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='pauseIcon']")));
		actions.moveToElement(pauseButton).click().perform();

		Thread.sleep(1000);

		WebElement yesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yesPauseDeal")));
		actions.moveToElement(yesButton).click().perform();
		
		WebElement pausefield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseDate")));
		actions.moveToElement(pausefield).click().perform();
		
		
		
		LocalDate today = LocalDate.now();
		String day = String.valueOf(today.getDayOfMonth());

		WebElement todayDate = driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']"));
		todayDate.click();
	        
		
		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPauseDeal")));
		confirm.click();
		
		
			 	
	}
	
	 @Test(priority = 8)
	public void makeDealAndCloneTheDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);

		reuse.reusebaleCodeForDealDashboard();
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

