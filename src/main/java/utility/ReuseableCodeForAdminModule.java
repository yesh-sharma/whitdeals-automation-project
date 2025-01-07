package utility;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ReuseableCodeForAdminModule extends Basetest {

	public ReuseableCodeForAdminModule(WebDriver driver) {
		this.driver = driver;
	}

	public void loginAsAdmin() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://staging.whitdeals.com.au/login");
		WebElement useremail = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_name")));

		useremail.sendKeys("admin");
		WebElement passwordEle = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_password")));

		passwordEle.sendKeys("whitdealsappadmin");
		WebElement submitbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

		submitbutton.click();

	}

	public String reusebaleCodeFordealsCreation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal']")));
		dealButton.click();

		// Faker faker = new Faker();
		// Generate a random username
		// String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");
		// String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		// String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp
		// + "]";
		String dealName = "DealCreatedByAdmin";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(5);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
       Actions action = new Actions(driver);

		WebElement calenderIcon = driver.findElement(By.xpath("//div[@class='row g-3 mt-40 row-gap-3']//div[@class='col-xs-12 col-lg-5 mt-0']//img[@alt='calendar-svg']"));

		// Send the current date to the input field
		action.moveToElement(calenderIcon).click().perform();

		
		
		
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		
		expiryDateInput.clear();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
         Thread.sleep(2000);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		

		return dealName;

	}

	public void reuseableCodeForDealDashBoard() throws InterruptedException {

		String createdDealName = "DealCreatedByAdminAndCheckPauseFunctionality";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}

	public String reusebaleCodeFordealsCreationForRTZ() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal Request']")));
		dealButton.click();

		Faker faker = new Faker();
		// Generate a random username
		String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealNameWithTimestamp);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(8);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);

		// Get the current local time
		// Get the current local time in 24-hour format
		LocalTime currentTime = LocalTime.now();

		// Add 4 hours and 35 minutes to the current time
		LocalTime newTime = currentTime.plusHours(4).plusMinutes(29);

		// Format the new time in HH:mm (24-hour format)
		String formattedTime = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));

		// Locate the time input element
		WebElement timeInput = driver.findElement(By.id("valid_from_time"));
		timeInput.clear();
		// Send the new time value to the input field
		timeInput.sendKeys(formattedTime);

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));

		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		Thread.sleep(30000);

		return dealNameWithTimestamp;

	}

	public String reusebaleCodeForDailyDealsCreation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();

		// Combine username with the readable timestamp
		String dealName = "DailyDealCreatedByAdmin";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(8);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		Thread.sleep(10000);
		return dealName;

	}

	public void reusebaleCodeForDailyDealsDashboard() throws InterruptedException {

		String createdDealName = "DailyDealCreatedByAdmin";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}

	public String reusebaleCodeForDailyDealsCreationForRTZ() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();
		// Combine username with the readable timestamp

		Faker faker = new Faker();
		// Generate a random username
		String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealNameWithTimestamp);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(8);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		Thread.sleep(10000);
		return dealNameWithTimestamp;

	}

	public String reusebaleCodeForEventCreation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement eventButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Event']")));
		eventButton.click();

//		Faker faker = new Faker();
//		// Generate a random username
//		String baseUsername = faker.name().username();
//		// Get the current local time and format it for better readability
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String readableTimestamp = now.format(formatter);
//
//		// Combine username with the readable timestamp
//		 eventNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

		String eventNameWithTimestamp = "EventCreatedByAdmin";

		WebElement eventTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventTitle")));
		eventTitle.sendKeys(eventNameWithTimestamp);
		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(5);

		WebElement eventMainCategory = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-main_category-container")));
		eventMainCategory.click();

		WebElement searchLiveMusic = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Search']")));
		searchLiveMusic.sendKeys("Live Music");

		Actions actions = new Actions(driver);

		actions.sendKeys(Keys.ENTER).perform();

		WebElement eventSubCategory = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Select Sub Category']")));
		String[] subCategories = { "Sports", "food" };
		for (String subCategory : subCategories) {
			eventSubCategory.sendKeys(subCategory);
			actions.sendKeys(Keys.ENTER).perform();
		}
		
		WebElement eventDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
		eventDescription.sendKeys("Get ready to embark on a culinary adventure like noet Dining Experience! Indulge ");

		WebElement eventAdditionalInfo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		eventAdditionalInfo.sendKeys(
				"Get ready to embark on a culinary adveExperience! Indulge in a meticulously crafted menu feature");

		System.out.println("Time at Event Title input: " + System.currentTimeMillis());

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));

		actions.moveToElement(scrollFirst).perform();
		// Scroll to the file upload elemen
		//

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement addScheduled = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add schedule']")));
		actions.moveToElement(addScheduled).click().perform();

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		LocalDate startDate = today.plusDays(1);
		String formattedDate = startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));
		actions.moveToElement(dateInput).perform();
		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);

		WebElement calenderIcon = driver
				.findElement(By.xpath("//div[@class='form-group ']//div[@class='col-6']//img[@alt='calendar-svg']"));
		actions.moveToElement(calenderIcon).click().perform();

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(2);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();

		expiryDateInput.sendKeys(newFormattedDate);
		WebElement calenderIcon2 = driver
				.findElement(By.xpath("//div[@class='form-group ']//div[@class=' col-6']//img[@alt='calendar-svg']"));
		actions.moveToElement(calenderIcon2).click().perform();
		Thread.sleep(2000);
		WebElement save = driver.findElement(By.xpath("//button[@id='notificationCreateBtn']"));
		actions.moveToElement(save).click().perform();
		// Send the formatted date to the input field

//		
//		WebElement body = driver.findElement(By.tagName("body"));
//		body.click();
//		

		WebElement checkboxForStartListing = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_soon_placeholder")));
		actions.moveToElement(checkboxForStartListing).perform();
		checkboxForStartListing.click();

		LocalDate today1 = LocalDate.now();
		String formattedDate1 = today1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		WebElement selectListingDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listing_date")));
		actions.moveToElement(selectListingDate).perform();
		selectListingDate.sendKeys(formattedDate1);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		actions.moveToElement(address).perform();
		Select address1 = new Select(address);
		address1.selectByIndex(3);

		WebElement externalBookingURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("booking_url")));
		actions.moveToElement(externalBookingURL).perform();
		externalBookingURL.sendKeys("www.whitdeals.com");

		WebElement contactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactName")));
		actions.moveToElement(contactName).perform();
		contactName.sendKeys("john wick");

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
		phone.sendKeys("3453453464");

		WebElement websiteURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("url")));
		websiteURL.sendKeys("https://staging.whitdeals.com.au/business/event-request/create");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.sendKeys("john1@gmail.com");

		WebElement submit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		actions.moveToElement(submit).perform();
		submit.click();

		Thread.sleep(25000);

		return eventNameWithTimestamp;

	}

	public void reusebaleCodeForEventdashboard() throws InterruptedException {
		String eventNameWithTimestamp = "EventCreatedByAdmin";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement dealsButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Events']")));
		dealsButton.click();

		Actions actions = new Actions(driver);
		// Deal title to search for
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
				if (rowText.toLowerCase().contains(eventNameWithTimestamp.toLowerCase())) {
					System.out.println("Match found for deal title: " + eventNameWithTimestamp);

					try {
						// Locate the checkbox and click it
						WebElement checkbox = row.findElement(By.xpath(".//button[@type='button']"));
						actions.moveToElement(checkbox).click().perform();

						System.out.println("Checkbox clicked for deal: " + eventNameWithTimestamp);
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
			System.out.println("Deal not found: " + eventNameWithTimestamp);
		} else {
			System.out.println("Deal successfully approved: " + eventNameWithTimestamp);
		}
		Thread.sleep(2000);

	}

	public void reusebaleCodeForLoyaltyCardCreation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		String LCNameWithTimestamp = "LoyaltyCardbyAdmin";
		Actions actions = new Actions(driver);
		// Step 4: Fill out the loyalty card form
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement eventButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Loyalty Card']")));
		eventButton.click();

		WebElement LCTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_title")));
		LCTitle.sendKeys(LCNameWithTimestamp);
		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(8);

		WebElement LCDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_description")));
		LCDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement LCTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_term_condtion")));
		LCTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		// Step 5: Upload an image
		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);

		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		// Step 6: Select fixed location and address
		WebElement fixedLocationCheckbox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("fixed_location")));
		actions.moveToElement(fixedLocationCheckbox).perform();
		fixedLocationCheckbox.click();

		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		actions.moveToElement(selectLocation).perform();
		Select locationSelect = new Select(selectLocation);
		locationSelect.selectByIndex(2);

		// Step 7:
		// Step 8: Configure stamp allocation
		WebElement maxDailyStampAllocation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_daily_allocation")));
		actions.moveToElement(maxDailyStampAllocation).perform();
		Select dailyStampSelect = new Select(maxDailyStampAllocation);
		dailyStampSelect.selectByIndex(2);

		WebElement totalStamps = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total_stamps")));
		actions.moveToElement(totalStamps).perform();
		Select totalStampSelect = new Select(totalStamps);
		totalStampSelect.selectByIndex(2);

		// Step 9: Add reward details
		WebElement awardName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lc_reward[]")));
		actions.moveToElement(awardName).perform();
		awardName.sendKeys("testaward");

		WebElement validThru = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reward_valid_thru")));
		actions.moveToElement(validThru).perform();
		Select validThruSelect = new Select(validThru);
		validThruSelect.selectByIndex(2);

		// Scroll to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_date")));
		actions.moveToElement(startDate).perform();
		
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		startDate.sendKeys(formattedDate);
		
		
		WebElement calenderIcon = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row gy-3']//div[@class='input-group date mt-2']//img[@alt='calendar-svg']")));
		actions.moveToElement(calenderIcon).perform();
		calenderIcon.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		Thread.sleep(3000);
		
		WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_date")));
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		endDate.sendKeys(newFormattedDate);
	

		WebElement calenderIcon1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='col-9 col-md-4']//div[@class='input-group date mt-2']//img[@alt='calendar-svg']")));
		actions.moveToElement(calenderIcon1).perform();
		calenderIcon1.click();
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement rememberCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remember")));
		actions.moveToElement(rememberCheckbox).perform();
		rememberCheckbox.click();

		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upload")));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		
		

		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		

		

	}
	
	
	public void reuseableCodeForSpin2WinDashBoard() throws InterruptedException {
	
		String createdDealName = "Spin2WinCreatedByAdmin";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}
	
	
	public String reusebaleCodeFordealsCreationByAdminToCheckPauseFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal']")));
		dealButton.click();

		// Faker faker = new Faker();
		// Generate a random username
		// String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");
		// String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		// String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp
		// + "]";
		String dealName = "DealCreatedByAdminAndCheckPauseFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
       Actions action = new Actions(driver);

		WebElement calenderIcon = driver.findElement(By.xpath("//div[@class='row g-3 mt-40 row-gap-3']//div[@class='col-xs-12 col-lg-5 mt-0']//img[@alt='calendar-svg']"));

		// Send the current date to the input field
		action.moveToElement(calenderIcon).click().perform();

		
		
		
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		
		expiryDateInput.clear();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
         Thread.sleep(2000);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		

	return dealName;

	}
	
	
	
	
	
	
	
	public String reusebaleCodeFordealsCreationByAdminToCheckCancleFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal']")));
		dealButton.click();

		// Faker faker = new Faker();
		// Generate a random username
		// String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");
		// String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		// String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp
		// + "]";
		String dealName = "DealCreatedByAdminAndCheckCancleFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
       Actions action = new Actions(driver);

		WebElement calenderIcon = driver.findElement(By.xpath("//div[@class='row g-3 mt-40 row-gap-3']//div[@class='col-xs-12 col-lg-5 mt-0']//img[@alt='calendar-svg']"));

		// Send the current date to the input field
		action.moveToElement(calenderIcon).click().perform();

		
		
		
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		
		expiryDateInput.clear();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
         Thread.sleep(2000);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();

	return dealName;

	}
	
	
	public void reuseableCodeForDealDashBoardForCancleFunctionality() throws InterruptedException {

		String createdDealName = "DealCreatedByAdminAndCheckCancleFunctionality";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}
	
	
	
	public String reusebaleCodeFordealsCreationForRTZForAdmin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

	

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal']")));
		dealButton.click();

		// Faker faker = new Faker();
		// Generate a random username
		// String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");
		// String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		// String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp
		// + "]";
		String dealName = "DealCreatedByAdminAndCheckRtZFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
       Actions action = new Actions(driver);

		WebElement calenderIcon = driver.findElement(By.xpath("//div[@class='row g-3 mt-40 row-gap-3']//div[@class='col-xs-12 col-lg-5 mt-0']//img[@alt='calendar-svg']"));

		// Send the current date to the input field
		action.moveToElement(calenderIcon).click().perform();

		
		
		
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		
		expiryDateInput.clear();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
         Thread.sleep(2000);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
	
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();

	return dealName;
	}

	
	public String reusebaleCodeForDailyDealsCreationForMobileIntegration() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();

		// Combine username with the readable timestamp
		String dealName = "DailyDealCreatedByAdminAndClaimedByMobileUser";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		
		Thread.sleep(10000);
		return dealName;

	}
	
	
	
	public String reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckPauseFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();

		// Combine username with the readable timestamp
		String dealName = "DailyDealCreatedByAdminAndCheckPauseFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		Thread.sleep(10000);
		return dealName;

	}
	
	public void reusebaleCodeForDailyDealsDashboardForPauseFunctionality() throws InterruptedException {

		String createdDealName = "DailyDealCreatedByAdminAndCheckPauseFunctionality";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}

	
	public String reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckCancleFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();

		// Combine username with the readable timestamp
		String dealName = "DailyDealCreatedByAdminAndCheckCancleFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		Thread.sleep(10000);
		return dealName;

	}
	
	
	
	
	public void reusebaleCodeForDailyDealsDashboardForCancleFunctionality() throws InterruptedException {

		String createdDealName = "DailyDealCreatedByAdminAndCheckCancleFunctionality";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}
	
	
	public String reusebaleCodeForDailyDealsCreationForMobileIntegrationAndCheckRTZFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal']")));
		dealButton.click();

		// Combine username with the readable timestamp
		String dealName = "DailyDealCreatedByAdminAndCheckRTZFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);
		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		Actions actions = new Actions(driver);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(3);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		actions.moveToElement(expiryDateInput).perform();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement Checkbox = driver.findElement(By.id("remember"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		
		
		Thread.sleep(10000);
		return dealName;

	}
	
	
	
	

	public String reusebaleCodeFordealsCreationByAdminToCheckcashierFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deal']")));
		dealButton.click();

		// Faker faker = new Faker();
		// Generate a random username
		// String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		// LocalDateTime now = LocalDateTime.now();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");
		// String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		// String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp
		// + "]";
		String dealName = "DealCreatedByAdminAndCheckcashierFunctionality";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(6);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(1);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(2);

		WebElement dealDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
		dealDescription.sendKeys(
				"Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");

		WebElement dealTermsAndCondition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
		dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");

		WebElement allocationCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealAllocation")));
		allocationCount.sendKeys("4");

		WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		WebElement uploadElement = driver.findElement(By.id("imageInput"));
		// Scroll to the file upload element
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
		// Use the Select class to handle the <select> element
		Select select2 = new Select(address);
		select2.selectByIndex(2);

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
       Actions action = new Actions(driver);

		WebElement calenderIcon = driver.findElement(By.xpath("//div[@class='row g-3 mt-40 row-gap-3']//div[@class='col-xs-12 col-lg-5 mt-0']//img[@alt='calendar-svg']"));

		// Send the current date to the input field
		action.moveToElement(calenderIcon).click().perform();

		
		
		
		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));
		
		expiryDateInput.clear();
		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);
         Thread.sleep(2000);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Actions actions = new Actions(driver);

		WebElement showTimerCheckbox = driver.findElement(By.xpath("//input[@name='show_timer']"));
		actions.moveToElement(showTimerCheckbox).perform();
		showTimerCheckbox.click();

		WebElement Checkbox = driver.findElement(By.id("declaration"));
		actions.moveToElement(Checkbox).perform();
		Checkbox.click();

		WebElement submitButton = driver.findElement(By.id("upload"));
		actions.moveToElement(submitButton).perform();
		submitButton.click();
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[.='Ok']")));
		actions.moveToElement(okButton).perform();
		okButton.click();
		

	
		
		
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement dealButton1 = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Deals\")")));
		dealButton1.click();
		

		WebElement allDeal = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Deals\")")));
		allDeal.click();

		WebElement claimButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\").instance(0)")));
		claimButton.click();
		WebElement goToWallet = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Go To Wallet\")")));
		goToWallet.click();
		
		WebElement RedeemButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Redeem\")")));
		RedeemButton.click();
		
		WebElement okButtonMobile = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")")));
		okButtonMobile.click();
		

		WebElement viewCode = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"View Code\")")));
		viewCode.click();
		

		WebElement Code = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("//android.view.View[@index=\"10\"]")));
		
		Thread.sleep(3000);
	
		@SuppressWarnings("deprecation")
		String contentDesc = Code.getAttribute("content-desc");
		System.out.println("Dynamic Content-Desc: " + contentDesc);

		
		Thread.sleep(3000);
		driver1.quit();
		
		
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
	
		loginApplicationAsCashier() ;
		
		WebElement codeInput = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("dealCode")));
		codeInput.sendKeys(contentDesc);
		
		
		
		
		WebElement validate= wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("validate")));
		validate.click();
		
		
		WebElement done= wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Done']")));
		done.click();
		
		
		Thread.sleep(4000);
		
		
		WebElement signOut1 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut1).perform();
		signOut1.click();
		loginApplicationAsAdmin();
		
		
		
		
		
		
		
		

	return dealName;

	}
	
	
	
	
	
	
	
	public void reuseableCodeForDealDashBoardForCashierFunctionality() throws InterruptedException {

		String createdDealName = "DealCreatedByAdminAndCheckcashierFunctionality";
		Actions actions = new Actions(driver);
		// Deal title to search for
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

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}















