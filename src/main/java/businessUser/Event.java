package businessUser;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import basetest.Basetest;

public class Event extends Basetest {
	@Test
	public void createEventGotapprovedByTheAdmin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		loginApplication();

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
		String dealNameWithTimestamp = baseUsername + " [Date: " + readableTimestamp + "]";

		WebElement eventTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventTitle")));
		eventTitle.sendKeys(dealNameWithTimestamp);

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
		eventSubCategory.click();
		eventSubCategory.sendKeys("Sports");
		actions.sendKeys(Keys.ENTER).perform();
		eventSubCategory.sendKeys("food");
		actions.sendKeys(Keys.ENTER).perform();
//		eventSubCategory.sendKeys("Night Life");
//		actions.sendKeys(Keys.ENTER).perform();
		
		WebElement eventDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
		eventDescription.sendKeys(
				"Get ready to embark on a culinary adventure like noet Dining Experience! Indulge ");
		WebElement eventAdditionalInfo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='additional_terms']")));
		eventAdditionalInfo.sendKeys(
				"Get ready to embark on a culinary adveExperience! Indulge in a meticulously crafted menu feature");
		
		 WebElement scrollFirst = driver.findElement(By.id("showImageHere"));
		 WebElement uploadElement = driver.findElement(By.id("imageInput"));
		 // Scroll to the file upload elemen
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("arguments[0].scrollIntoView(true);", scrollFirst);
	     // Provide the absolute file path of the image to the file upload element
	     File file = new File("/Users/yeshsharma/Downloads/doctor.jpeg");
	     uploadElement.sendKeys(file.getAbsolutePath());
	     
	     
	     WebElement cropImage  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
			cropImage.click();
	     
			  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	     
		
		
		
		
		
		
		
		
		
		
	}

}
