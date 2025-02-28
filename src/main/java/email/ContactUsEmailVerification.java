package email;

import org.openqa.selenium.WebDriver;

public class ContactUsEmailVerification extends BaseEmailVerification {

	public ContactUsEmailVerification(WebDriver driver) {
		super(driver);
		
	}


	@Override
	public boolean verifyEmailReceived(String subject, String expectedContent) {
		// TODO Auto-generated method stub
		  boolean isReceived = searchEmail(subject);
	        if (isReceived) {
	            System.out.println("✅ Email received: " + subject);
	        } else {
	            System.out.println("❌ Email not received: " + subject);
	        }
	        return isReceived;
	    }

	    public boolean verifyContactUSCreationEmail(String subject , String expectedContent) {
	        return verifyEmailReceived(subject, expectedContent);
	    }

	}
	
	
	
	
	
	
	
	


