package businessUser;

import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import basetest.Basetest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utility.MobileUtils;
import utility.ReuseableCode;

public class Event extends Basetest {

	//@Test(priority = 1)
	public void createEventByBusinessuserAndAdminApprovesTheEvent() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();

		WebElement approveButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Approve']")));
		approveButton.click();

		Thread.sleep(2000);
		WebElement confirmApproveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Approve']")));
		confirmApproveButton.click();
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement eventButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")));
		eventButton.click();
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement allEvent = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Events\")")));
		allEvent.click();

		//Wait for the element to be visible
		int screenHeight = driver.manage().window().getSize().getHeight();
		int screenWidth = driver.manage().window().getSize().getWidth();

		//Calculate the scroll distance (25% of the screen size)
		int scrollDistance = screenHeight / 2;

		//Define start and end points for the scroll
		int startX = screenWidth / 2;
		int startY = screenHeight / 2; // Middle of the screen
		int endY = startY - scrollDistance;

		//Maximum number of scrolls
		int maxScrolls = 10;

		for (int i = 0; i < maxScrolls; i++) {
		 // Wait for the element to be visible before checking
		 try {
			 
		     WebElement targetElement = new FluentWait<>(driver)
		         .withTimeout(Duration.ofSeconds(10))
		         .pollingEvery(Duration.ofMillis(500))
		         .ignoring(NoSuchElementException.class)
		         .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"​Deal Cancle Test\"]")));
		     System.out.println("yesh" +targetElement);
		   //android.view.View[@content-desc="​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​"]
		   //android.view.View[@content-desc=\"" + deal\ + ""]
		    // android.view.View[@content-desc=\"​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​\"]
		      System.out.println(targetElement);

		     System.out.println("Element found after " + (i + 1) + " scroll(s).");
		     return; // Stop scrolling if the element is found
		 } catch (Exception e) {
		     System.out.println("Element not found, continuing to scroll...");
		     // Continue scrolling if the element is not found
		 }

		 // Perform the scroll
		 PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		 Sequence scroll = new Sequence(finger, 0);
		 scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		 scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		 scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
		 scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		 driver1.perform(Arrays.asList(scroll));

		 System.out.println("Scroll " + (i + 1) + " completed.");
		}

		System.out.println("Element not found after " + maxScrolls + " scroll(s).");

			 	
			 	
	

		driver1.quit();
		
		
		
		
		
		
	
	}
	
	//@Test(priority = 2)
	public void createEventByBusinessuserAndAdminDeclineTheEventWithReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();
	
		WebElement declineButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		declineButton.click();  
             
	       
			WebElement declineMessage  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("decline_comment")));
			declineMessage.sendKeys("declining because of testing");
	             

             Thread.sleep(2000);
		WebElement confirmDeclineButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmDeclineButton.click();  
             
	
	}
	
	//@Test(priority = 3)
	public void createEventByBusinessuserAndAdminDeclineTheEventWithoutReasonForDecline() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginApplication();
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventCreation();
	
		WebElement approveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Decline']")));
		approveButton.click();  
	         
			WebElement declineMessage  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("no_comment")));
			declineMessage.click();
			
	         Thread.sleep(2000);
		WebElement confirmApproveButton  = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='handleDeclineModal(true)']")));
		confirmApproveButton.click();  
	
	}
	
	
	//@Test(priority = 4)
	@Test
	public void createEventByBusinessUserAfterThatCancled() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ReuseableCode reuse = new ReuseableCode(driver);
		String event =reuse.reusebaleCodeForEventDashboard();
		Actions actions = new Actions(driver);
		WebElement cancleTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@id='cancel-btn']")));
		actions.moveToElement(cancleTheEvent).click().perform();
	
		Thread.sleep(1000);
		
		WebElement YesToCancle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
		YesToCancle.click();	
		
		MobileUtils mobileUtils = new MobileUtils();
		AndroidDriver driver1 = mobileUtils.initializeMobileDriver();

		FluentWait<AndroidDriver> wait1 = new FluentWait<>(driver1).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		
		WebElement eventButton = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")));
		eventButton.click();
	
		WebElement signOut = driver.findElement(By.xpath("//span[normalize-space()='Sign Out']"));
		actions.moveToElement(signOut).perform();
		signOut.click();
		loginApplication();

		WebElement allEvent = wait1.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"All Events\")")));
		allEvent.click();

		//Wait for the element to be visible
		int screenHeight = driver.manage().window().getSize().getHeight();
		int screenWidth = driver.manage().window().getSize().getWidth();

		//Calculate the scroll distance (25% of the screen size)
		int scrollDistance = screenHeight / 2;

		//Define start and end points for the scroll
		int startX = screenWidth / 2;
		int startY = screenHeight / 2; // Middle of the screen
		int endY = startY - scrollDistance;

		//Maximum number of scrolls
		int maxScrolls = 10;

		for (int i = 0; i < maxScrolls; i++) {
		 // Wait for the element to be visible before checking
		 try {
			 
		     WebElement targetElement = new FluentWait<>(driver)
		         .withTimeout(Duration.ofSeconds(10))
		         .pollingEvery(Duration.ofMillis(500))
		         .ignoring(NoSuchElementException.class)
		         .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"​Deal Cancle Test\"]")));
		     System.out.println("yesh" +targetElement);
		   //android.view.View[@content-desc="​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​"]
		   //android.view.View[@content-desc=\"" + deal\ + ""]
		    // android.view.View[@content-desc=\"​P​a​u​s​e​d​ ​R​e​d​e​e​m​-​ ​S​a​v​e​d​ ​U​n​i​q​u​e​\"]
		      System.out.println(targetElement);

		     System.out.println("Element found after " + (i + 1) + " scroll(s).");
		     return; // Stop scrolling if the element is found
		 } catch (Exception e) {
		     System.out.println("Element not found, continuing to scroll...");
		     // Continue scrolling if the element is not found
		 }

		 // Perform the scroll
		 PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		 Sequence scroll = new Sequence(finger, 0);
		 scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		 scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		 scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
		 scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		 driver1.perform(Arrays.asList(scroll));

		 System.out.println("Scroll " + (i + 1) + " completed.");
		}

		System.out.println("Element not found after " + maxScrolls + " scroll(s).");

			 	
			 	
	

		driver1.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}	
	
	//@Test(priority = 5)
	public void createEventByBusinessUserAndCloneTheEvent() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ReuseableCode reuse = new ReuseableCode(driver);
		reuse.reusebaleCodeForEventDashboard();
		Actions actions = new Actions(driver);
		WebElement cloneTheEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//a[@title='Clone Event'][normalize-space()='Clone']")));
		actions.moveToElement(cloneTheEvent).click().perform();
	    Thread.sleep(1000);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
