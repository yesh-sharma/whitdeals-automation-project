package utility;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import io.appium.java_client.android.options.UiAutomator2Options;

public class ReuseableCode extends Basetest {
	

	public ReuseableCode(WebDriver driver) {
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@href='https://staging.whitdeals.com.au/business/my-requests/create']")));
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

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealNameWithTimestamp);

		WebElement redemptionMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(2);

		WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
		// Use the Select class to handle the <select> element
		Select select1 = new Select(dealRestriction);
		select1.selectByIndex(7);

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

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);
   
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		
		
		// Get the current local time
		// Get the current local time in 24-hour format
		LocalTime currentTime = LocalTime.now();

		// Add 4 hours and 35 minutes to the current time
		LocalTime newTime = currentTime.plusHours(4).plusMinutes(29);

		// Format the new time in HH:mm (24-hour format)
		String formattedTime = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));

		// Locate the time input element
		//WebElement timeInput = driver.findElement(By.id("valid_from_time"));
		//timeInput.clear();
		// Send the new time value to the input field
		//timeInput.sendKeys(formattedTime);

		LocalDate currentDate = LocalDate.now();
		LocalDate expiryDate = currentDate.plusDays(1);

		// Format the date in the required format (yyyy-MM-dd)
		String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the expiry date input field
		WebElement expiryDateInput = driver.findElement(By.id("validTo"));

		// Send the formatted date to the input field
		expiryDateInput.sendKeys(newFormattedDate);

		WebElement body1 = driver.findElement(By.tagName("body"));
		body1.click();
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
		
         Thread.sleep(35000);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		// login as admin

		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.loginAsAdmin();

		WebElement BusinessRequest = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
		BusinessRequest.click();

		WebElement PendingRequest = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
		PendingRequest.click();

		String dealTitle1 = dealNameWithTimestamp; // Deal title to search for
		boolean dealFound = false;

		// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='deals_table']//tr"));

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
								.findElement(By.xpath(".//input[contains(@class, 'deal-checkbox checkbox')]"));
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
		return dealNameWithTimestamp;

	}

	public String reusebaleCodeForDailyDealsCreation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement dealButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deal Request']")));
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

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
		dealTitle.sendKeys(dealNameWithTimestamp);

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

		WebElement checkboxScheduleForLater = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_asap")));
		Actions actions = new Actions(driver);
		actions.moveToElement(checkboxScheduleForLater).perform();
		checkboxScheduleForLater.click();

		// Get today's date in the format yyyy-MM-dd
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Locate the date input element
		WebElement dateInput = driver.findElement(By.id("validFrom"));

		// Send the current date to the input field
		dateInput.sendKeys(formattedDate);

		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

//       
//        

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
		
		WebElement expiryTimeInput = driver.findElement(By.id("finish_time"));
		actions.moveToElement(expiryTimeInput).perform();
		expiryTimeInput.click();
		
		
		WebElement setTime = driver.findElement(By.xpath("//a[@data-action='incrementHour']//span[@class='fa fa-chevron-up']"));
		actions.moveToElement(setTime).perform();
	     setTime.click();
	 	body.click();
	   
		
		Thread.sleep(5000);
		

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
		Thread.sleep(35000);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.loginAsAdmin();

		WebElement BusinessRequest = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
		BusinessRequest.click();

		WebElement PendingRequest = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
		PendingRequest.click();

		WebElement dailyDealTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-value='daily deals']")));
		dailyDealTab.click();

		String dealTitle1 = dealNameWithTimestamp; // Deal title to search for
		boolean dealFound = false;

		// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='daily_deals_table']//tr"));

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
						WebElement checkbox = row.findElement(By.xpath(".//input[contains(@class, 'checkbox')]"));
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
		return readableTimestamp;
	}

	public String reusebaleCodeForEventCreation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement eventButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Event Request']")));
		eventButton.click();

		Faker faker = new Faker();
		// Generate a random username
		String baseUsername = faker.name().username();
		// Get the current local time and format it for better readability
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String readableTimestamp = now.format(formatter);

		// Combine username with the readable timestamp
		String eventNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

		WebElement eventTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventTitle")));
		eventTitle.sendKeys(eventNameWithTimestamp);

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
		// js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
		// Provide the absolute file path of the image to the file upload element
		File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
		uploadElement.sendKeys(file.getAbsolutePath());

		WebElement cropImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();

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
		
//		
//		WebElement checkboxForStartAndEndTime = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.id("alldayPlaceholder")));
//		actions.moveToElement(checkboxForStartAndEndTime).perform();
//		checkboxForStartAndEndTime.click();

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

		Thread.sleep(35000);
		WebElement signOut2 = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut2).perform();
		signOut2.click();
	

		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.loginAsAdmin();

		WebElement BusinessRequest = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
		BusinessRequest.click();

		WebElement PendingRequest = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
		PendingRequest.click();

		WebElement dailyDealTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-value='events']")));
		dailyDealTab.click();

		String dealTitle1 = eventNameWithTimestamp; // Deal title to search for
		boolean dealFound = false;

		// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='event_table']//tr"));

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
						WebElement checkbox = row.findElement(By.xpath(".//input[contains(@class, 'checkbox')]"));
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
		return eventNameWithTimestamp;
	}

	public void reusebaleCodeForLoyatyCardCreationOneCardIsAlreadyPresent() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		loginApplication();

		Actions actions = new Actions(driver);
		WebElement loyaltycardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@href='https://staging.whitdeals.com.au/business/loyalty-card/dashboard']")));
		loyaltycardButton.click();

		WebElement scrollToTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deals")));
		actions.moveToElement(scrollToTable).perform();

		// Find all rows in the table
		List<WebElement> rows = scrollToTable.findElements(By.tagName("tr"));
		String endTimeToUse = null; // Variable to store the selected end time

		// Loop through rows (skip the header row)
		for (int i = 1; i < rows.size(); i++) {
			// Get all columns in the current row
			List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

			// Fetch the status and end time
			String statusText = columns.get(2).getText(); // 2nd column contains the status
			WebElement endTimeColumn = columns.get(3); // 3rd column contains the end time

			if (statusText.equalsIgnoreCase("Scheduled")) {
				// If "Scheduled" exists, use its end time and exit the loop
				WebElement dateDiv = endTimeColumn.findElement(By.xpath(".//div[@class='h6']"));
				endTimeToUse = dateDiv.getText();
				System.out.println("Using Scheduled End Time: " + endTimeToUse);
				break; // Exit after finding the first "Scheduled" end time
			} else if (statusText.equalsIgnoreCase("Active") && endTimeToUse == null) {
				// Use "Active" end time only if no "Scheduled" is found
				WebElement dateDiv = endTimeColumn.findElement(By.xpath(".//div[@class='h6']"));
				endTimeToUse = dateDiv.getText();
				System.out.println("Using Active End Time: " + endTimeToUse);
			}
		}

		// Handle the case where no valid status is found
		if (endTimeToUse == null) {
			System.out.println("No Scheduled or Active statuses found in the table.");
			throw new AssertionError("No valid end time found to use in the test.");
		}

		// Use the retrieved end time in the test script
		System.out.println("End Time to be used in the script: " + endTimeToUse);
		// Split the string at the space between the date and time

		// You can add code here to perform further actions based on the end time
		// For example, you might compare the end time with the current date/time
		WebElement ButtonDashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[contains(@class,'mt-3')]//a[@class='text-decoration-none d-flex-start']")));
		ButtonDashboard.click();

// Step 1: Click the create button on the dashboard
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

// Step 2: Navigate to the loyalty card creation page
		WebElement loyaltyCardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@href='https://staging.whitdeals.com.au/business/loyalty-card-requests/create']")));
		loyaltyCardButton.click();

// Step 3: Generate a unique loyalty card name using Faker
		Faker faker = new Faker();
		String baseUsername = faker.name().username();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String readableTimestamp = now.format(formatter);
		String LCNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

// Step 4: Fill out the loyalty card form
		WebElement LCTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_title")));
		LCTitle.sendKeys(LCNameWithTimestamp);

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

// Step 7: Add social media handle
		WebElement socialHandles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("facebook")));
		actions.moveToElement(socialHandles).perform();
		socialHandles.sendKeys("www.facebook.com");

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
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(endTimeToUse, formatter1); // Parse the existing date
		LocalDate updatedDate = date.plusDays(1);
		String updatedDateStr = updatedDate.format(formatter1);
		startDate.sendKeys(updatedDateStr);

		WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_date")));
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(endTimeToUse, formatter2); // Parse the existing date
		LocalDate updatedDate2 = date1.plusDays(4);
		String updatedDateStr1 = updatedDate2.format(formatter2);
		endDate.sendKeys(updatedDateStr1);
		WebElement body = driver.findElement(By.tagName("body"));
		body.click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement rememberCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remember")));
		actions.moveToElement(rememberCheckbox).perform();
		rememberCheckbox.click();

		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upload")));
		actions.moveToElement(submitButton).perform();
		submitButton.click();

		Thread.sleep(35000);

		ReuseableCode reuse = new ReuseableCode(driver);
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

		String dealTitle1 = LCNameWithTimestamp; // Deal title to search for
		boolean dealFound = false;

// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows1 = driver.findElements(By.xpath("//table[@id='loyalty_table']//tr"));

			// Print the count of rows
			System.out.println("Number of deals found: " + rows1.size());

			// Print all rows on the current page
			for (WebElement row : rows1) {
				System.out.println("Row text: " + row.getText());
			}

			// Iterate through the rows to find the desired deal
			for (WebElement row : rows1) {
				String rowText = row.getText();
				System.out.println("Checking row: " + rowText);

				// Check if the row contains the desired deal title
				if (rowText.toLowerCase().contains(dealTitle1.toLowerCase())) {
					System.out.println("Match found for deal title: " + dealTitle1);

					try {
						// Locate the checkbox and click it
						WebElement checkbox = row.findElement(By.xpath(".//input[contains(@class, 'checkbox')]"));
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

	}

	public void reusebaleCodeForLoyatyCardCreationNoCardIsPresent() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		loginApplication();
		Actions actions = new Actions(driver);

		
		try {
			WebElement ButtonDashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@class,'mt-3')]//a[@class='text-decoration-none d-flex-start']")));
			ButtonDashboard.click();

// Step 1: Click the create button on the dashboard
			WebElement createButtonOnDashboard = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
			createButtonOnDashboard.click();

// Step 2: Navigate to the loyalty card creation page
			WebElement loyaltyCardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//a[@href='https://staging.whitdeals.com.au/business/loyalty-card-requests/create']")));
			loyaltyCardButton.click();

// Step 3: Generate a unique loyalty card name using Faker
//			Faker faker = new Faker();
//			String baseUsername = faker.name().username();
//			LocalDateTime now = LocalDateTime.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			String readableTimestamp = now.format(formatter);
//			String LCNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";
			String LCNameWithTimestamp = "LoyaltyCardTest";

// Step 4: Fill out the loyalty card form
			WebElement LCTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_title")));
			LCTitle.sendKeys(LCNameWithTimestamp);

			WebElement LCDescription = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("lc_description")));
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

// Step 7: Add social media handle
			WebElement socialHandles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("facebook")));
			actions.moveToElement(socialHandles).perform();
			socialHandles.sendKeys("www.facebook.com");

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

			WebElement validThru = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.name("reward_valid_thru")));
			actions.moveToElement(validThru).perform();
			Select validThruSelect = new Select(validThru);
			validThruSelect.selectByIndex(2);

// Scroll to the bottom of the page
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_date")));
			LocalDate today = LocalDate.now();
			String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			startDate.sendKeys(formattedDate);

			WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_date")));
			LocalDate currentDate = LocalDate.now();
			LocalDate expiryDate = currentDate.plusDays(1);

			// Format the date in the required format (yyyy-MM-dd)
			String newFormattedDate = expiryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			endDate.sendKeys(newFormattedDate);

			WebElement body = driver.findElement(By.tagName("body"));
			body.click();

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement rememberCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remember")));
			actions.moveToElement(rememberCheckbox).perform();
			rememberCheckbox.click();

			WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upload")));
			actions.moveToElement(submitButton).perform();
			submitButton.click();

			Thread.sleep(35000);

			ReuseableCode reuse = new ReuseableCode(driver);
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

			String dealTitle1 = LCNameWithTimestamp; // Deal title to search for
			boolean dealFound = false;

// Loop through pagination
			while (true) {
				// Locate the table rows
				List<WebElement> rows1 = driver.findElements(By.xpath("//table[@id='loyalty_table']//tr"));

				// Print the count of rows
				System.out.println("Number of deals found: " + rows1.size());

				// Print all rows on the current page
				for (WebElement row : rows1) {
					System.out.println("Row text: " + row.getText());
				}

				// Iterate through the rows to find the desired deal
				for (WebElement row : rows1) {
					String rowText = row.getText();
					System.out.println("Checking row: " + rowText);

					// Check if the row contains the desired deal title
					if (rowText.toLowerCase().contains(dealTitle1.toLowerCase())) {
						System.out.println("Match found for deal title: " + dealTitle1);

						try {
							// Locate the checkbox and click it
							WebElement checkbox = row.findElement(By.xpath(".//input[contains(@class, 'checkbox')]"));
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

		} catch (Exception e) {

			System.out.println("Error: Loyalty card alredy present " + e.getMessage());
		}

	}

	public String reusebaleCodeForDealDashBoardWithMobileToCheckCancleFunctionality() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

		ReuseableCode reuse = new ReuseableCode(driver);

		String createdDealName = reuse.reusebaleCodeFordealsCreation();

		System.out.println("Created Deal Name: " + createdDealName);
		
	
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

		Thread.sleep(5000);
		
		
		
		// Mobile autmomation part

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

		WebElement claimButton = wait1.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.androidUIAutomator("new UiSelector().description(\"Claim\").instance(0)")));
		claimButton.click();
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();
		WebElement dealDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deals']")));
		dealDashboard.click();

		
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
        return createdDealName;
	}

	public void reusebaleCodeForDailyDealDashboard() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		loginApplication();

		ReuseableCode reuse = new ReuseableCode(driver);

		String createdDealName = reuse.reusebaleCodeForDailyDealsCreation();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

		Thread.sleep(5000);

		loginApplication();

		WebElement dealDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Daily Deals']")));
		dealDashboard.click();

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

	public String reusebaleCodeForEventDashboard() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		loginApplication();

		ReuseableCode reuse = new ReuseableCode(driver);

		String createdDealName = reuse.reusebaleCodeForEventCreation();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();
        Actions actions = new Actions(driver);
		Thread.sleep(5000);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement dealDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Events']")));
		dealDashboard.click();

		
		// Deal title to search for
		boolean dealFound = false;

		// Loop through pagination
		while (true) {
			// Locate the table rows
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='deals']//tr"));

			// Print the count of rows
			System.out.println("Number of event found: " + rows.size());

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
					System.out.println("Match found for event title: " + createdDealName);

					try {
						// Locate the checkbox and click it
						WebElement checkbox = row.findElement(By.xpath(".//button[@type='button']"));
						actions.moveToElement(checkbox).click().perform();

						System.out.println("Checkbox clicked for evnet: " + createdDealName);
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
				System.out.println("Event  found and approved. Stopping further search.");
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

		return createdDealName;
	}
	
	
	
	public void reusebaleCodeForLoyatycardDashboard() throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		loginApplication();
		
		WebElement LoyaltyCardDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Loyalty Cards']")));
		LoyaltyCardDashboard.click();
		
        String createdDealName = "LoyaltyCardTest";
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
	
	
	public void reuseableCodeformobile(AndroidDriver driver1) throws InterruptedException {
	
 try{
			String appiumServerUrl = "http://127.0.0.1:4723/";
			UiAutomator2Options dc = new UiAutomator2Options();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("appium:automationName", "UiAutomator2");
			//dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/whitdeals1.apk");
			 dc.setCapability("app","/Users/yeshsharma/Downloads/whitdeals1.apk");
			dc.setCapability("appPackage", "com.example.WhitdealsApp");
			dc.setCapability("appActivity", "com.example.WhitdealsApp.MainActivity");
			
			driver1 = new AndroidDriver(new URL(appiumServerUrl), dc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1)
	                .withTimeout(Duration.ofSeconds(20))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(Exception.class);
		
			WebElement locationPermission = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")));
			locationPermission.click();

			WebElement allowNotification = wait1
					.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
							"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")));
			allowNotification.click();
			WebElement menu = wait1.until(ExpectedConditions.elementToBeClickable(
					AppiumBy.androidUIAutomator("new UiSelector().description(\"Menu\n" + "Tab 5 of 5\")")));
			menu.click();
			WebElement loginButton = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			loginButton.click();

			WebElement email = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
			email.click();
			email.sendKeys("saransh1@gmail.com");
			WebElement password = wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy
					.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
			password.click();
			password.sendKeys("qwerty12");

			WebElement submit = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Login\")")));
			submit.click();

			WebElement cancleFaceID = wait1.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"Cancel\")")));
			Thread.sleep(3000);
			cancleFaceID.click();
	
	}
	
	
	public String reusebaleCodeForDealDashboard() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

		ReuseableCode reuse = new ReuseableCode(driver);

		String createdDealName = reuse.reusebaleCodeFordealsCreation();

		System.out.println("Created Deal Name: " + createdDealName);
		
	
		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();

		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		Thread.sleep(10000);
		loginApplication();
		WebElement dealDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Deals']")));
		dealDashboard.click();

		
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
return createdDealName;
	
	
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}