package utility;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import basetest.Basetest;

public class ReuseableCode extends Basetest {

	public ReuseableCode(WebDriver driver) {
		this.driver = driver;
	}
public  void loginAsAdmin(){
	
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	  driver.get("https://staging.whitdeals.com.au/login");
	WebElement useremail = wait.until(ExpectedConditions
            .elementToBeClickable(By.id("user_name")));

    useremail.sendKeys("admin");
    WebElement passwordEle = wait.until(ExpectedConditions
            .elementToBeClickable(By.id("user_password")));

    passwordEle.sendKeys("whitdealsappadmin");
    WebElement submitbutton = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//button[@type='submit']")));

    submitbutton.click();
	

}

public void reusebaleCodeFordealsCreation() throws InterruptedException {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	
	WebElement createButtonOnDashboard  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
	createButtonOnDashboard.click();

	WebElement dealButton  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://staging.whitdeals.com.au/business/my-requests/create']")));
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

    
	WebElement dealTitle  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
	dealTitle.sendKeys(dealNameWithTimestamp);

	
	WebElement redemptionMethod  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
	// Use the Select class to handle the <select> element
    Select select = new Select(redemptionMethod);
    select.selectByIndex(2);
	
    WebElement dealRestriction  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
	// Use the Select class to handle the <select> element
    Select select1 = new Select(dealRestriction);
    select1.selectByIndex(2);
	
    WebElement dealDescription  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
	dealDescription.sendKeys("Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");
	
	
	
	WebElement dealTermsAndCondition  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
	dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");
	
	WebElement allocationCount  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
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
     
     
     WebElement cropImage  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();
     
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
     

			WebElement address  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
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
        LocalTime newTime = currentTime.plusHours(4).plusMinutes(35);

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
    Thread.sleep(40000);
   
     //login as admin
    
	ReuseableCode reuse = new ReuseableCode(driver);
     reuse.loginAsAdmin();
    
 	WebElement BusinessRequest  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
	BusinessRequest.click();
    
 	WebElement PendingRequest  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
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
	                WebElement checkbox = row.findElement(By.xpath(".//input[contains(@class, 'deal-checkbox checkbox')]"));
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

public void reusebaleCodeForDailyDealsCreation() throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	WebElement createButtonOnDashboard  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("btnGroupDrop1")));
	createButtonOnDashboard.click();

	WebElement dealButton  = wait.until(
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

    
	WebElement dealTitle  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_title")));
	dealTitle.sendKeys(dealNameWithTimestamp);


	WebElement redemptionMethod  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("validate_method")));
	// Use the Select class to handle the <select> element
    Select select = new Select(redemptionMethod);
    select.selectByIndex(2);
    WebElement dealRestriction  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_term_id")));
	// Use the Select class to handle the <select> element
    Select select1 = new Select(dealRestriction);
    select1.selectByIndex(2);
	
    WebElement dealDescription  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("deal_description")));
	dealDescription.sendKeys("Get ready to embark on a culinary adventure like no other with our exclusive Gourmet Dining Experience! Indulge in a meticulously crafted menu featuring locally sourced, fresh ingredients, paired perfectly with a selection of fine wines and handcrafted cocktails.");
	
	
	
	WebElement dealTermsAndCondition  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("additional_terms")));
	dealTermsAndCondition.sendKeys("This deal is valid only for the specified dates mentioned in the offer.");
	
	WebElement allocationCount  = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("max_coupon_allocate")));
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
     
     
     WebElement cropImage  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
		cropImage.click();
     
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
     

			WebElement address  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("address_id")));
			// Use the Select class to handle the <select> element
	        Select select2 = new Select(address);
	        select2.selectByIndex(2);

			  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        WebElement checkboxScheduleForLater  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("start_asap")));
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
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
       
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
    Thread.sleep(40000);
    
   	ReuseableCode reuse = new ReuseableCode(driver);
        reuse.loginAsAdmin();
       
    	WebElement BusinessRequest  = wait.until(
   			ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='panel6']//a[@class='text-decoration-none']")));
   	BusinessRequest.click();
       
    	WebElement PendingRequest  = wait.until(
   			ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Pending')]")));
   	PendingRequest.click();
   	
   	
   	
   	WebElement dailyDealTab  = wait.until(
   			ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-value='daily deals']")));
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








}

}