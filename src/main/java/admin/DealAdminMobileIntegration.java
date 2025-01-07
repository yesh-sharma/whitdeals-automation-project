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
import org.testng.annotations.Test;

import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;
import utility.ReuseableCodeForAdminModule;

public class DealAdminMobileIntegration extends Basetest {

	
	
	public void createDealByAdminForbusinessUserAndClaimAndRedeemBytheMobileUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();

		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton.click();

	
		reuse.reusebaleCodeFordealsCreation();


		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		WebElement dealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton.click();
		
		Actions actions = new Actions(driver);

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal.click();

		WebElement claimButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\").instance(0)")));
		claimButton.click();

		WebElement goToWalletButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Go To Wallet\")")));
		goToWalletButton.click();

		WebElement RemoveTheDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Remove Deal\")")));
		RemoveTheDeal.click();

		WebElement confirmButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Yes\")")));
		confirmButton.click();

		claimButton.click();
		goToWalletButton.click();
		WebElement instantRedeem = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Instant Redeem\")")));
		instantRedeem.click();
		WebElement confirmOk = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")")));
		confirmOk.click();

		
		
	}

	

	

	

	public void dealCreatedByAdminForBusinessUserAndPauseThatDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton.click();

	
		reuse.reusebaleCodeFordealsCreationByAdminToCheckPauseFunctionality();
		
		Actions actions = new Actions(driver);
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
		
		
		reuse.loginAsAdmin();
		
	
		WebElement assetsButtonOnDashboard1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard1.click();

		WebElement dealsButton1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton1.click();

		
		
		
		reuse.reuseableCodeForDealDashBoard();
		
		
	
	

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

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal.click();
		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​P​a​u​s​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
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

	

	
	public void dealCreatedByAdminForBusinessUserAndCancleThatDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton.click();
		
		

		reuse.reusebaleCodeFordealsCreationByAdminToCheckCancleFunctionality();
		

		
		Actions actions = new Actions(driver);
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
		
		
		

		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal.click();
		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​C​a​n​c​l​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test passed.");
	        	    
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test failed.");
	        	    Assert.fail("Element with description was not found, failing the test.");
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
	 	
		
		
		
		
		
		
		
		
		reuse.loginAsAdmin();
		
	
		WebElement assetsButtonOnDashboard1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard1.click();

		WebElement dealsButton1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton1.click();
		
		
		
		reuse.reuseableCodeForDealDashBoardForCancleFunctionality();

		WebElement pauseButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='puase-btn']")));
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
		

		MobileUtils mobileUtils1 = new MobileUtils();
		AndroidDriver driver11 = mobileUtils1.initializeMobileDriver();

		FluentWait<AndroidDriver> wait21 = new FluentWait<>(driver11).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton1 = wait21.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton1.click();
		

		WebElement allDeal1 = wait21.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal1.click();
		
		  WebDriverWait wait3 = new WebDriverWait(driver11, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait3.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​C​a​n​c​l​e​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test failed.");
	        	    Assert.fail("Element with description was found, failing the test.");
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test passed.");
	        	  
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
	 	
		
		
		
		

	}

  @Test
	public void DealcreatedByAdminForBusinessUserAndRTZThatDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();

		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton.click();

		String createdDealName = reuse.reusebaleCodeFordealsCreationForRTZForAdmin();
		

		Actions actions = new Actions(driver);
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal.click();
		
		  WebDriverWait wait2 = new WebDriverWait(driver1, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait1.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​R​t​Z​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test passed.");
	        	    
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test failed.");
	        	    Assert.fail("Element with description was not found, failing the test.");
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
	 	
		
		
		
		
		
		
		
		
		reuse.loginAsAdmin();
		
	
		WebElement assetsButtonOnDashboard1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard1.click();

		WebElement dealsButton1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Deals']")));
		dealsButton1.click();
		
		
		
	
	

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
		
		

		MobileUtils mobileUtils1 = new MobileUtils();
		AndroidDriver driver11 = mobileUtils1.initializeMobileDriver();

		FluentWait<AndroidDriver> wait21 = new FluentWait<>(driver11).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton1 = wait21.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton1.click();
		

		WebElement allDeal1 = wait21.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal1.click();
		
		  WebDriverWait wait3 = new WebDriverWait(driver11, Duration.ofSeconds(15));
	         try {
	        	    // Try to locate the element
	        	
	           WebElement webView = wait3.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
	               
	                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"​D​e​a​l​C​r​e​a​t​e​d​B​y​A​d​m​i​n​A​n​d​C​h​e​c​k​R​t​Z​F​u​n​c​t​i​o​n​a​l​i​t​y​\").instance(0))"
	                   
	               )
	           ));
	        	    // If the element is found, fail the test
	        	    System.out.println("Element found: Test failed.");
	        	    Assert.fail("Element with description was found, failing the test.");
	        	} catch (TimeoutException e) {
	        	    // If the element is not found, pass the test
	        	    System.out.println("Element not found: Test passed.");
	        	  
	        	} catch (Exception e) {
	        	    // Catch any other unexpected exceptions
	        	    System.out.println("An unexpected error occurred: " + e.getMessage());
	        	    Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
	        	}
		
		
	 	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
