package admin;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCodeForAdminModule;

public class SpinToWin extends Basetest {
	
	
	@Test(priority=1)
	public void createSpin2WinByAdminForbusinessUser() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement createButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
		createButtonOnDashboard.click();

		WebElement spin2WinButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Spin 2 Win Request']")));
		spin2WinButton.click();

		String dealName = "Spin2WinCreatedByAdmin";

		WebElement dealTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealName")));
		dealTitle.sendKeys(dealName);

		WebElement businessName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("business_id")));
		// Use the Select class to handle the <select> element
		Select selectBusinessUser = new Select(businessName);
		selectBusinessUser.selectByIndex(8);

		WebElement redemptionMethod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealType")));
		// Use the Select class to handle the <select> element
		Select select = new Select(redemptionMethod);
		select.selectByIndex(1);


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
}
	
	@Test(priority=2)
	public void searchSpin2WinCreatedByAdminForBusinessUserAndCloneThatExistingSpin2Win() throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement spin2WinButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Spin 2 Win']")));
		spin2WinButton.click();
		
		reuse.reuseableCodeForSpin2WinDashBoard();
		 Actions actions = new Actions(driver);
		 WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Deal'][normalize-space()='Clone']")));
			actions.moveToElement(pauseButton).click().perform();

			WebElement yesButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Yes']")));
			yesButton.click();
		
		
		
		
		
	
	}
	
	
	
	
	@Test(priority=3)
	
	public void searchSpin2WinCreatedByAdminForBusinessUserAndPauseThatExistingSpin2Win() throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement spin2WinButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Spin 2 Win']")));
		spin2WinButton.click();
		
		reuse.reuseableCodeForSpin2WinDashBoard();
		
		 Actions actions = new Actions(driver);
			
		    WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//ul[@class='dropdown-menu show']//a[@id='pauseIcon']")));
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
			LocalTime newTime = currentTime.plusHours(4).plusMinutes(31);

			// Format the new time in HH:mm (24-hour format)
			String formattedTime = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	//
			WebElement pauseTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseTime")));
			pauseTime.clear();
			pauseTime.sendKeys(formattedTime);
			WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPauseDeal")));
			confirm.click();
		
		
		
		
		
		
		
	
	}
	
	
	@Test(priority=4)
	public void searchSpin2WinCreatedByAdminForBusinessUserAndEditThatExistingSpin2Win() throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement spin2WinButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Spin 2 Win']")));
		spin2WinButton.click();
		
		reuse.reuseableCodeForSpin2WinDashBoard();
		
		
		 Actions actions = new Actions(driver);
			
		    WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//ul[@class='dropdown-menu show']//a[@title='Edit Deal'][normalize-space()='Edit']")));
			actions.moveToElement(pauseButton).click().perform();
		
			WebElement dealRestriction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dealinfo")));
			// Use the Select class to handle the <select> element
		    dealRestriction.sendKeys("test");
		
			WebElement Checkbox = driver.findElement(By.id("declaration"));
			actions.moveToElement(Checkbox).perform();
			Checkbox.click();

			WebElement submitButton = driver.findElement(By.id("upload"));
			actions.moveToElement(submitButton).perform();
			submitButton.click();
			Thread.sleep(10000);
		
		
		
	
	}
	
	
	
	@Test(priority=5)
	public void searchSpin2WinCreatedByAdminForBusinessUserAndCancelThatExistingSpin2Win() throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
		
		WebElement assetsButtonOnDashboard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Assets']")));
		assetsButtonOnDashboard.click();

		WebElement spin2WinButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Spin 2 Win']")));
		spin2WinButton.click();
		
		reuse.reuseableCodeForSpin2WinDashBoard();
		
		
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
	
	
	
	
	
	
	
	
	
	
	
}