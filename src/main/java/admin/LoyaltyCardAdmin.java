package admin;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCodeForAdminModule;

public class LoyaltyCardAdmin extends Basetest {

@Test
	public void createLoyaltycardByAdminForbusinessUser()throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
        reuse.reusebaleCodeForLoyaltyCardCreation();
	
	}
}

