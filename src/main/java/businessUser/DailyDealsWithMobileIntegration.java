package businessUser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
@Test
public class DailyDealsWithMobileIntegration extends Basetest {
	
	 static int initialRemainingDailyDealCountOnDashboard;
	 
	 static int initialRemainingDealCountOnDealsDashboard;
	 static int initialActiveDealCountOnDealsDashboard;
	
	
	public void createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		loginApplication();
		
		WebElement remainingDealCountElement = driver.findElement(By.xpath("(//div[@class='display-5'])[2]")); 
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
				WebElement remainingDealCount = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [3]")); 
			    initialRemainingDealCountOnDealsDashboard = Integer.parseInt(remainingDealCount.getText());
		        System.out.println("Initial Deal Count on deals dashboard: " + initialRemainingDealCountOnDealsDashboard);
		        
		        
		        WebElement activedealCount = driver.findElement(By.xpath("//div[@class='display-5']")); 
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
		
		Thread.sleep(4000);
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
		Thread.sleep(5000);
		loginApplication();

		WebElement TotalRedeemedCountBox1 = driver.findElement(By.xpath("(//div[@class='display-5 mt-2']) [2]"));
		int redeemedCountAfterRedeeming = Integer.parseInt(TotalRedeemedCountBox1.getText());

		Assert.assertEquals(redeemedCountbeforeRedeeming, redeemedCountAfterRedeeming - 1,
				"Redeemed count did not increase by 1!");
		
		
		
		driver1.quit();
		
		
		
	}
	
	
	

	public void makeDailyDealAndCancleTheDealByBusinessUser() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboardForMobileIntegration1();
		
	
		Actions actions = new Actions(driver);
		WebElement cancleButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='puase-btn']")));
		actions.moveToElement(cancleButton).click().perform();

	    Thread.sleep(10000);

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

		Thread.sleep(2000);
		WebElement submit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
		submit.click();
		
		Thread.sleep(10000);
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement dailyDealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
		dailyDealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
		allDeal.click();
		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​a​i​l​y​D​e​a​l​C​r​e​a​t​e​d​C​h​e​c​k​C​a​n​c​l​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test failed.");
	        	    Assert.fail("Element with description was found, failing the test.");
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test failed.");
	        	  
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
		
	
		
	}
	
	public void makeDailyDealAndRTZTheDailyDealByBusinessUserAndSearchOnMobile() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboardForMobileIntegration();
		
	
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
		
		
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement dailyDealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
		dailyDealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
		allDeal.click();
		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​a​i​l​y​D​e​a​l​C​r​e​a​t​e​d​C​h​e​c​k​R​T​Z​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test failed.");
	        	    Assert.fail("Element with description was found, failing the test.");
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test failed.");
	        	  
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void makeDailyDealAndPauseTheDeal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForDailyDealDashboardForMobileUserToTestPauseFunctionality();
		
	
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

		WebElement calenderIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input-group date mt-2']//img[@alt='calendar-svg']")));
		calenderIcon.click();
		

		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPauseDeal")));
		confirm.click();
		
		
	
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dailyDealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
		dailyDealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
		allDeal.click();

		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​C​h​e​c​k​ ​D​a​i​l​y​ ​D​e​a​l​ ​C​r​e​a​t​i​o​n​ ​A​n​d​ ​P​a​u​s​e​ ​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test passed.");
	        	    
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test failed.");
	        	    Assert.fail("Element with description was found, failing the test.");
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
		
		
		
		
		
		
	}
	
}
