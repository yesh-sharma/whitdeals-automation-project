package pageobjectmodal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DailyDealsPage {
	

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    
    // Page Elements - Deal Counts
    @FindBy(xpath = "(//div[@class='display-5 mt-2']) [3]")
    private WebElement remainingDealCountElement;
    
    @FindBy(xpath = "//div[@class='display-5']")
    private WebElement activeDealCountElement;
    
    // Page Elements - Approval/Decline Actions
    @FindBy(xpath = "//button[normalize-space()='Approve']")
    private WebElement approveButton;
    
    @FindBy(xpath = "//span[.='Approve']")
    private WebElement confirmApproveButton;
    
    @FindBy(xpath = "//button[normalize-space()='Decline']")
    private WebElement declineButton;
    
    @FindBy(id = "decline_comment")
    private WebElement declineMessageField;
    
    @FindBy(id = "no_comment")
    private WebElement noCommentCheckbox;
    
    @FindBy(xpath = "//button[@onclick='handleDeclineModal(true)']")
    private WebElement confirmDeclineButton;
    
    // Page Elements - Deal Actions Menu
    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[@id='puase-btn']")
    private WebElement cancelButton;
    
    @FindBy(xpath = "//button[@onclick='showAction()']")
    private WebElement confirmationButton;
    
    @FindBy(id = "cancel-btn")
    private WebElement cancelDealButton;
    
    @FindBy(xpath = "//button[normalize-space()='yes']")
    private WebElement yesButton;
    
    @FindBy(id = "cancellation_reason")
    private WebElement cancellationReasonField;
    
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    private WebElement submitButton;
    
    @FindBy(id = "rtz-btn")
    private WebElement rtzButton;
    
    // Page Elements - Pause Deal
    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[@id='pauseIcon']")
    private WebElement pauseButton;
    
    @FindBy(id = "yesPauseDeal")
    private WebElement yesPauseDealButton;
    
    @FindBy(id = "pauseDate")
    private WebElement pauseDateField;
    
    @FindBy(id = "confirmPauseDeal")
    private WebElement confirmPauseDealButton;
    
    // Page Elements - Clone Deal
    @FindBy(xpath = "//ul[@class='dropdown-menu show']//a[@title='Clone Deal'][normalize-space()='Clone']")
    private WebElement cloneButton;
    
    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement yesCloneButton;
    
    // Constructor
    public DailyDealsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
   

	// Deal Count Methods
    public int getRemainingDealCount() {
        return Integer.parseInt(remainingDealCountElement.getText());
    }
    
    public int getActiveDealCount() {
        return Integer.parseInt(activeDealCountElement.getText());
    }
    
    // Approval Methods
    public void approveDeal() throws InterruptedException {
        WebElement approve = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        approve.click();
        
        Thread.sleep(2000);
        
        WebElement confirmApprove = wait.until(ExpectedConditions.elementToBeClickable(confirmApproveButton));
        confirmApprove.click();
        
        Thread.sleep(12000);
    }
    
    public void declineDealWithReason(String reason) throws InterruptedException {
        WebElement decline = wait.until(ExpectedConditions.elementToBeClickable(declineButton));
        decline.click();
        
        WebElement declineMessage = wait.until(ExpectedConditions.visibilityOf(declineMessageField));
        declineMessage.sendKeys(reason);
        
        Thread.sleep(2000);
        
        WebElement confirmDecline = wait.until(ExpectedConditions.elementToBeClickable(confirmDeclineButton));
        confirmDecline.click();
    }
    
    public void declineDealWithoutReason() throws InterruptedException {
        WebElement decline = wait.until(ExpectedConditions.elementToBeClickable(declineButton));
        decline.click();
        
        WebElement noComment = wait.until(ExpectedConditions.elementToBeClickable(noCommentCheckbox));
        noComment.click();
        
        Thread.sleep(2000);
        
        WebElement confirmDecline = wait.until(ExpectedConditions.elementToBeClickable(confirmDeclineButton));
        confirmDecline.click();
    }
    
    // Deal Management Methods
    public void cancelDeal(String cancellationReason) throws InterruptedException {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        actions.moveToElement(cancel).click().perform();
        
        Thread.sleep(1000);
        
        WebElement confirmation = wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        actions.moveToElement(confirmation).click().perform();
        
        Thread.sleep(1000);
        
        WebElement cancelDeal = wait.until(ExpectedConditions.elementToBeClickable(cancelDealButton));
        cancelDeal.click();
        
        Thread.sleep(1000);
        
        WebElement yesToCancel = wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        yesToCancel.click();
        
        Thread.sleep(2000);
        
        WebElement reasonField = wait.until(ExpectedConditions.visibilityOf(cancellationReasonField));
        reasonField.sendKeys(cancellationReason);
        
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submit.click();
    }
    
    public void rtzDeal() throws InterruptedException {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        actions.moveToElement(cancel).click().perform();
        
        Thread.sleep(1000);
        
        WebElement confirmation = wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        actions.moveToElement(confirmation).click().perform();
        
        Thread.sleep(1000);
        
        WebElement rtz = wait.until(ExpectedConditions.elementToBeClickable(rtzButton));
        rtz.click();
        
        Thread.sleep(1000);
        
        WebElement yesToRtz = wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        yesToRtz.click();
    }
    
    public void pauseDeal() throws InterruptedException {
        WebElement pause = wait.until(ExpectedConditions.elementToBeClickable(pauseButton));
        actions.moveToElement(pause).click().perform();
        
        Thread.sleep(1000);
        
        WebElement yesPause = wait.until(ExpectedConditions.elementToBeClickable(yesPauseDealButton));
        actions.moveToElement(yesPause).click().perform();
        
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        WebElement pauseDate = wait.until(ExpectedConditions.visibilityOf(pauseDateField));
        pauseDate.sendKeys(formattedDate);
        
        WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmPauseDealButton));
        confirm.click();
    }
    
    public void cloneDeal() throws InterruptedException {
        WebElement clone = wait.until(ExpectedConditions.elementToBeClickable(cloneButton));
        actions.moveToElement(clone).click().perform();
        
        Thread.sleep(1000);
        
        WebElement yesToClone = wait.until(ExpectedConditions.elementToBeClickable(yesCloneButton));
        yesToClone.click();
        
        Thread.sleep(3000);
    }
	
	

}
