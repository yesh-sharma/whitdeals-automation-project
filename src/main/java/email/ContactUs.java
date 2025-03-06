package email;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;

public class ContactUs extends Basetest {
	
	@Test
	public void contactUsFormEmailValidation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	
	      driver.get("http://staging.whitdeals.com.au/contact-us");
	      
	      WebElement name  = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));
			name.sendKeys("yesh"); 
	
			  WebElement email  = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
				email.sendKeys("yeshsharma516032@gmail.com");
		
				  WebElement phoneNumber = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='phone']")));
					phoneNumber.sendKeys("2342325434");
			
					  WebElement enquiry  = wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='enquiry']")));
						enquiry.sendKeys("what is WhitDeals how can accessthe features");					
						
						WebElement contactUs  = wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contact-btn btn']")));
						contactUs.click();  
						
						Thread.sleep(3000);
						
						
//						ContactUsEmailVerification verify = new ContactUsEmailVerification(driver);
//						verify.verifyContactUSCreationEmail("yesh", "sharma");
				
}
}