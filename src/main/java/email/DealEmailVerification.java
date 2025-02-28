package email;

import org.openqa.selenium.WebDriver;

public class DealEmailVerification extends BaseEmailVerification {

    public DealEmailVerification(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean verifyEmailReceived(String subject, String expectedContent) {
        boolean isReceived = searchEmail(subject);
        if (isReceived) {
            System.out.println("✅ Email received: " + subject);
        } else {
            System.out.println("❌ Email not received: " + subject);
        }
        return isReceived;
    }

    public boolean verifyDealCreationEmail(String subject , String expectedContent) {
        return verifyEmailReceived(subject, expectedContent);
    }

    public boolean verifyDealApprovalEmail() {
        return verifyEmailReceived("Your Deal is Approved", "Your deal has been successfully approved.");
    }
}
