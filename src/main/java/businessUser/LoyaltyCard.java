package businessUser;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

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

	@Test(priority = 2, dependsOnMethods = {
			"createLoyaltycardByBusinessUserAndNoLoyaltyCardIsPresentAndApproveBytheAdmin" })
	public void createLoyaltyCardByBusinessuserButAtLeastOneLoyaltyCardShouldBePresentAndAdminApprovesTheLoyaltyCard()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

}
