package businessUser;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import org.testng.annotations.Test;

import basetest.Basetest;
import pageobjectmodal.DailyDealsPage;
import pageobjectmodal.DashboardPage;
import utility.ReuseableCode;

//@Test
public class Deal extends Basetest {

	private static int initialRemainingDailyDealCountOnDashboard;
    private static int initialRemainingDealCountOnDealsDashboard;
    private static int initialActiveDealCountOnDealsDashboard;
    
    private DashboardPage dashboardPage;
    private DailyDealsPage dailyDealsPage;

    @Test(priority = 1)
    public void createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dashboardPage = new DashboardPage(driver, wait);
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        // Get initial counts from dashboard
        initialRemainingDailyDealCountOnDashboard = dashboardPage.getRemainingDealCount();
        System.out.println("Initial Deal Count on dashboard: " + initialRemainingDailyDealCountOnDashboard);
        
        int claimedCountBeforeClaiming = dashboardPage.getTotalClaimedCount();
        System.out.println("Initial Claimed Count on deals dashboard: " + claimedCountBeforeClaiming);
        
        // Navigate to daily deals page
        dashboardPage.clickDailyDealsLink();
        
        // Get initial counts from deals dashboard
        initialRemainingDealCountOnDealsDashboard = dailyDealsPage.getRemainingDealCount();
        System.out.println("Initial Deal Count on deals dashboard: " + initialRemainingDealCountOnDealsDashboard);
        
        initialActiveDealCountOnDealsDashboard = dailyDealsPage.getActiveDealCount();
        System.out.println("Initial Active Deal Count on deals dashboard: " + initialActiveDealCountOnDealsDashboard);
        
        // Create deal and approve
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealsCreation();
        
        dailyDealsPage.approveDeal();
    }

    @Test(priority = 3)
    public void createDailyDealByBusinessUserAndAdminDeclineTheDailyDeal() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dashboardPage = new DashboardPage(driver, wait);
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealsCreation();
        
        dailyDealsPage.declineDealWithReason("declining because of testing");
    }

    @Test(priority = 4)
    public void createDailyDealByBusinessUserAndAdminDeclineTheDealWithoutReasonForDecline() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dashboardPage = new DashboardPage(driver, wait);
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealsCreation();
        
        dailyDealsPage.declineDealWithoutReason();
    }

    @Test(priority = 2, dependsOnMethods = { "createDailyDealByBusinessUserAndAdminApprovesTheDailyDeal" })
    public void verifyDealCountsAfterDealCreation() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dashboardPage = new DashboardPage(driver, wait);
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        // Verify updated counts on dashboard
        int updatedRemainingDealCountOnDashboard = dashboardPage.getRemainingDealCount();
        System.out.println("Updated Deal Count on dashboard: " + updatedRemainingDealCountOnDashboard);
        Assert.assertEquals(updatedRemainingDealCountOnDashboard, 
                           initialRemainingDailyDealCountOnDashboard - 1,
                           "Deal count did not decrease by 1!");
        
        // Navigate to deals dashboard
        dashboardPage.clickDailyDealsLink();
        
        // Verify updated counts on deals dashboard
        int updatedRemainingDealCountOnDealsDashboard = dailyDealsPage.getRemainingDealCount();
        System.out.println("Updated Deal Count on deals dashboard: " + updatedRemainingDealCountOnDealsDashboard);
        Assert.assertEquals(updatedRemainingDealCountOnDealsDashboard,
                           initialRemainingDealCountOnDealsDashboard - 1,
                           "Deal count did not decrease by 1!");
        
        int updatedActiveDealCountOnDealsDashboard = dailyDealsPage.getActiveDealCount();
        System.out.println("Updated Active Deal Count on deals dashboard: " + updatedActiveDealCountOnDealsDashboard);
        Assert.assertEquals(updatedActiveDealCountOnDealsDashboard,
                           initialActiveDealCountOnDealsDashboard + 1,
                           "Active count did not increase by 1!");
    }

    @Test(priority = 5)
    public void cancelDailyDealByBusinessUser() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealDashboard();
        
        dailyDealsPage.cancelDeal("testing this functionality");
    }

    @Test(priority = 6)
    public void rtzDailyDealByBusinessUser() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealDashboard();
        
        dailyDealsPage.rtzDeal();
    }

    @Test(priority = 7)
    public void pauseDailyDeal() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealDashboard();
        
        dailyDealsPage.pauseDeal();
    }

    @Test(priority = 8)
    public void cloneDailyDeal() throws InterruptedException {
        loginApplication();
        
        // Initialize page objects
        dailyDealsPage = new DailyDealsPage(driver, wait);
        
        ReuseableCode reuse = new ReuseableCode(driver);
        reuse.reusebaleCodeForDailyDealDashboard();
        
        dailyDealsPage.cloneDeal();
    }

}
