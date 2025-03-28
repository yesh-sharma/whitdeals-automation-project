package admin;


import org.testng.annotations.Test;

import basetest.Basetest;
import utility.ReuseableCodeForAdminModule;

public class LoyaltyCardAdmin extends Basetest {

@Test
	public void createLoyaltycardByAdminForbusinessUser()throws InterruptedException {

	
		ReuseableCodeForAdminModule reuse = new ReuseableCodeForAdminModule(driver);
		reuse.loginAsAdmin();
        reuse.reusebaleCodeForLoyaltyCardCreation(); 
	
	}
}

