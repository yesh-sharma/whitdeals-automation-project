package pageobjectmodal;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DashboardPage {
	
	
	 private WebDriver driver;
	    private WebDriverWait wait;
	    
	    // Page Elements
	    @FindBy(xpath = "(//div[@class='display-5'])[2]")
	    private WebElement remainingDealCountElement;
	    
	    @FindBy(xpath = "(//div[@class='display-5 mt-2']) [1]")
	    private WebElement totalClaimedCountElement;
	    
	    @FindBy(xpath = "//a[normalize-space()='Daily Deals']")
	    private WebElement dailyDealsLink;
	    
	    // Constructor
	    public DashboardPage(WebDriver driver, WebDriverWait wait) {
	        this.driver = driver;
	        this.wait = wait;
	        PageFactory.initElements(driver, this);
	    }
	    
	    // Page Methods
	    public int getRemainingDealCount() {
	        WebElement element = wait.until(ExpectedConditions.visibilityOf(remainingDealCountElement));
	        return Integer.parseInt(element.getText());
	    }
	    
	    public int getTotalClaimedCount() {
	        return Integer.parseInt(totalClaimedCountElement.getText());
	    }
	    
	    public void clickDailyDealsLink() {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dailyDealsLink));
	        element.click();
	    }
	    
	    public boolean isDisplayed() {
	        return remainingDealCountElement.isDisplayed();
	    }
	
	
	
	
	
	
	
	
	

}
