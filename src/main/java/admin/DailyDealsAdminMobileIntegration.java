package admin;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;
import utility.ReuseableCodeForAdminModule;

public class DailyDealsAdminMobileIntegration extends Basetest {

	public void createDailyDealByAdminForbusinessUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();

		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Daily Deals']")));
		dealsButton.click();

		WebElement ActiveDeals = driver.findElement(By.xpath("(//div[@class='display-5 mt-2'])[3]"));
		int activeDealCount = Integer.parseInt(ActiveDeals.getText());

		reuse.reusebaleCodeForDailyDealsCreationForMobileIntegration();

		WebElement ActiveDealsAfterCreation = driver.findElement(By.xpath("(//div[@class='display-5 mt-2'])[3]"));
		int activeDealCountAfterCreation = Integer.parseInt(ActiveDealsAfterCreation.getText());

		Assert.assertEquals(activeDealCountAfterCreation, activeDealCount + 1, "Active count did not increase by 1!");
		
		
		Actions actions = new Actions(driver);
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
		dealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Daily Deals\")")));
		allDeal.click();
		
		  

		WebElement claimButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\")")));
		claimButton.click();
		
		
		WebElement wallet = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Go To Wallet\")")));
		wallet.click();
		
		
		
		WebElement instantRedeem = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Instant Redeem\")")));
		instantRedeem.click();
		
		
		
		WebElement ok = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")")));
		ok.click();
		
	
	}
	

	public void dailyDealcreatedByAdminForBusinessUserAndPauseThatDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckPauseFunctionality();

		Thread.sleep(5000);
		
        reuse.reusebaleCodeForDailyDealsDashboardForPauseFunctionality();
        
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
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​a​i​l​y​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​P​a​u​s​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
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
		
		

	 		WebElement claimButton = wait1.until(ExpectedConditions
	 				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\")")));
	 		claimButton.click();
		

	
	}

	
	
	
	public void dealcreatedByAdminForBusinessUserAndCancleThatDeal() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckCancleFunctionality();

		Thread.sleep(5000);
		
        reuse.reusebaleCodeForDailyDealsDashboardForCancleFunctionality();

	    WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='dropdown-menu show']//a[@id='puase-btn']")));
		actions.moveToElement(pauseButton).click().perform();
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
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​a​i​l​y​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​C​a​n​c​l​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
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
	@Test
	public void searchDealcreatedByAdminForBusinessUserAndRTZThatDeal() throws InterruptedException {
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		String createdDealName=reuse.reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckRTZFunctionality();

		Thread.sleep(5000);
		
	
	    Actions actions = new Actions(driver);
	
			boolean dealFound = false;

			// Loop through pagination
			while (true) {
				// Locate the table rows
				List<WebElement> rows = driver.findElements(By.xpath("//table[@id='deals']//tr"));

				// Print the count of rows
				System.out.println("Number of deals found: " + rows.size());

				// Print all rows on the current page
				for (WebElement row : rows) {
					System.out.println("Row text: " + row.getText());
				}

				// Iterate through the rows to find the desired deal
				for (WebElement row : rows) {
					String rowText = row.getText();
					System.out.println("Checking row: " + rowText);

					// Check if the row contains the desired deal title
					if (rowText.toLowerCase().contains(createdDealName.toLowerCase())) {
						System.out.println("Match found for deal title: " + createdDealName);

						try {
							// Locate the checkbox and click it
							WebElement checkbox = row.findElement(By.xpath(".//button[@type='button']"));
							actions.moveToElement(checkbox).click().perform();

							System.out.println("Checkbox clicked for deal: " + createdDealName);
							dealFound = true;
						} catch (Exception e) {
							System.out.println("Error clicking the checkbox: " + e.getMessage());
						}

						// Exit both the row and pagination loops
						break;
					}
				}

				// If deal is found, stop further searching
				if (dealFound) {
					System.out.println("Deal found and approved. Stopping further search.");
					break;
				}

				// Handle pagination if deal is not found
				try {
					WebElement nextButton = driver.findElement(By.xpath("//button[@class='dt-paging-button next']"));
					if (nextButton.isEnabled()) {
						System.out.println("Navigating to the next page...");
						actions.moveToElement(nextButton).click().perform();
						Thread.sleep(2000); // Allow time for the next page to load
					} else {
						System.out.println("No more pages to search.");
						break;
					}
				} catch (NoSuchElementException e) {
					System.out.println("Pagination 'Next' button not found. Ending search.");
					break;
				}
			}

			// Final result
			if (!dealFound) {
				System.out.println("Deal not found: " + createdDealName);
			} else {
				System.out.println("Deal successfully approved: " + createdDealName);
			}
			Thread.sleep(2000);

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
		               
		                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​a​i​l​y​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​R​T​Z​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
		                   
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
	
}
