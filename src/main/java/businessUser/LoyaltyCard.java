package businessUser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCode;

public class LoyaltyCard extends Basetest {

    @Test(priority = 1)
	public void createLoyaltycardByBusinessUserAndNoLoyaltyCardIsPresentAndApproveBytheAdmin()
			throws InterruptedException {

		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForLoyatyCardCreationNoCardIsPresent();

	}

	
	 @Test(priority = 2, dependsOnMethods ={"createLoyaltycardByBusinessUserAndNoLoyaltyCardIsPresentAndApproveBytheAdmin" })
	
	public void createLoyaltyCardByBusinessuserButAtLeastOneLoyaltyCardShouldBePresentAndAdminApprovesTheLoyaltyCard()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForLoyatyCardCreationOneCardIsAlreadyPresent();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

	}

	@Test(priority = 3)
	public void createLoyaltyCardByBusinessuserButAtLeastOneLoyaltyCardShouldBePresentAndAdminDeclineWithOutMessageTheLoyaltyCard()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForLoyatyCardCreationOneCardIsAlreadyPresent();

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

	 @Test(priority = 4)
	
	public void createLoyaltyCardByBusinessuserButAtLeastOneLoyaltyCardShouldBePresentAndAdminDeclineWithMessageTheLoyaltyCard()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForLoyatyCardCreationOneCardIsAlreadyPresent();
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
	 @Test(priority = 5)

	public void searchLoyaltycardAndCloneTheloyaltycard() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForLoyatycardDashboard();
        Thread.sleep(2000);
		WebElement pauseButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Loyalty Card'][normalize-space()='Clone']")));
		actions.moveToElement(pauseButton).click().perform();

		Thread.sleep(3000);

	}
	 @Test(priority = 6)
	
	public void searchLoyaltycardAndPauseTheloyaltycard() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForLoyatycardDashboard();
		   Thread.sleep(2000);
		WebElement pauseButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Pause Loyalty Card'][normalize-space()='Pause']")));
		actions.moveToElement(pauseButton).click().perform();

		Thread.sleep(1000);

		WebElement yesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yesPauseLc")));
		actions.moveToElement(yesButton).click().perform();
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		WebElement pauseDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseDate")));
		pauseDate.sendKeys(formattedDate);

		// Get the current local time in 24-hour format
		LocalTime currentTime = LocalTime.now();

		// Add 4 hours and 35 minutes to the current time
		LocalTime newTime = currentTime.plusHours(4).plusMinutes(45);

		// Format the new time in HH:mm (24-hour format)
		String formattedTime = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));
//
		WebElement pauseTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseTime")));
		pauseTime.clear();
		pauseTime.sendKeys(formattedTime);
		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPauseLc")));
		confirm.click();

	}

	 @Test(priority = 7)
	
	public void searchLoyaltycardAndcancleTheloyaltycardPartially() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForLoyatycardDashboard();
		   Thread.sleep(2000);
		WebElement cancleButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cancel-btn']")));
		actions.moveToElement(cancleButton).click().perform();
		WebElement YesButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='showAction()']")));
		actions.moveToElement(YesButton).click().perform();
		WebElement partiallyButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("partially-btn")));
		actions.moveToElement(partiallyButton).click().perform();
		
	
		Thread.sleep(1000);

	}
	
	 @Test(priority = 9)
	public void searchLoyaltycardAndcancleTheloyaltycardCompletely() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForLoyatycardDashboard();
		   Thread.sleep(2000);
		WebElement cancleButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cancel-btn']")));
		actions.moveToElement(cancleButton).click().perform();
		WebElement YesButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='showAction()']")));
		actions.moveToElement(YesButton).click().perform();
		
		WebElement permanentButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanent-btn")));
		actions.moveToElement(permanentButton).click().perform();
		
		Thread.sleep(2000);
		WebElement reasonText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancellation_reason")));
		actions.moveToElement(reasonText).click().perform();
		actions.moveToElement(reasonText).sendKeys("testing").perform();
		
		WebElement submitButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
		actions.moveToElement(submitButton).click().perform();
		
		
		Thread.sleep(1000);
		

	}
	
	 @Test(priority = 8)
	
	public void searchLoyaltycardAndEditLoyaltyCardAndApprovedByAdmin() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCode reuse = new ReuseableCode(driver);
		Actions actions = new Actions(driver);
		reuse.reusebaleCodeForLoyatycardDashboard();
		   Thread.sleep(2000);
		WebElement editButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Edit Loyalty Card'][normalize-space()='Edit']")));
		actions.moveToElement(editButton).click().perform();


		WebElement EditLCTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_term_condtion")));
		EditLCTermsAndCondition.clear();
		EditLCTermsAndCondition.sendKeys("Edited for testing");
		WebElement rememberCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remember")));
		actions.moveToElement(rememberCheckbox).perform();
		rememberCheckbox.click();

		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upload")));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		Alert alert = driver.switchTo().alert();

        // Print the alert message
        System.out.println(alert.getText());

        // Accept the alert (click OK)
        alert.accept();
        
        Thread.sleep(7000);
        reuse.loginAsAdmin();
      
	

		WebElement BusinessRequest = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
		BusinessRequest.click();

		WebElement PendingRequest = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
		PendingRequest.click();
		
		WebElement dailyDealTab = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-value='loyalty cards']")));
		dailyDealTab.click();
		
		String dealTitle1 = "LoyaltyCardTest"; // Deal title to search for
		boolean dealFound = false;

		// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='loyalty_table']//tr"));

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
				if (rowText.toLowerCase().contains(dealTitle1.toLowerCase())) {
					System.out.println("Match found for deal title: " + dealTitle1);

					try {
						// Locate the checkbox and click it
						WebElement checkbox = row
								.findElement(By.xpath(".//input[contains(@class, 'checkbox')]"));
						actions.moveToElement(checkbox).click().perform();

						System.out.println("Checkbox clicked for deal: " + dealTitle1);
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
			System.out.println("Deal not found: " + dealTitle1);
		} else {
			System.out.println("Deal successfully approved: " + dealTitle1);
		}
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();
}
	
}
