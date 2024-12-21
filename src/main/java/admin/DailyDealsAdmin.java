package admin;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCodeForAdminModule;

public class DailyDealsAdmin extends Basetest {
	@Test(priority=1)
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

		reuse.reusebaleCodeForDailyDealsCreation();

		WebElement ActiveDealsAfterCreation = driver.findElement(By.xpath("(//div[@class='display-5 mt-2'])[3]"));
		int activeDealCountAfterCreation = Integer.parseInt(ActiveDealsAfterCreation.getText());

		Assert.assertEquals(activeDealCountAfterCreation, activeDealCount + 1, "Active count did not increase by 1!");

	}
	@Test(priority=2)
	public void searchDailyDealcreatedByAdminForBusinessUserAndCloneThatDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Daily Deals']")));
		dealsButton.click();
		reuse.reusebaleCodeForDailyDealsDashboard();
		Actions actions = new Actions(driver);

		WebElement cloneButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Deal'][normalize-space()='Clone']")));
		actions.moveToElement(cloneButton).click().perform();

		WebElement yesButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Yes']")));
		yesButton.click();

	}

	@Test(priority=4)
	public void searchDailyDealcreatedByAdminForBusinessUserAndPauseThatDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Daily Deals']")));
		dealsButton.click();
		reuse.reusebaleCodeForDailyDealsDashboard();
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

	@Test(priority=3)
	public void searchDailyDealcreatedByAdminForBusinessUserAndEditThatDailyDeal() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Daily Deals']")));
		dealsButton.click();
		reuse.reusebaleCodeForDailyDealsDashboard();
		Actions actions = new Actions(driver);

		WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='dropdown-menu show']//a[@title='Edit Deal'][normalize-space()='Edit']")));
		actions.moveToElement(pauseButton).click().perform();

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(4);

		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();


		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		Thread.sleep(30000);

	}
	
	@Test(priority=5)
	public void searchDealcreatedByAdminForBusinessUserAndCancleThatDeal() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Daily Deals']")));
		dealsButton.click();
		reuse.reusebaleCodeForDailyDealsDashboard();
		Actions actions = new Actions(driver);


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
	
			

	}
	@Test(priority=6)
	public void searchDealcreatedByAdminForBusinessUserAndRTZThatDeal() throws InterruptedException {
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		
		
		String createdDealName= reuse.reusebaleCodeForDailyDealsCreationForRTZ();
		

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
		

	}
	
}
