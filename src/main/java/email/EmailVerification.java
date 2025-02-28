package email;

public interface EmailVerification {
    boolean verifyEmailReceived(String subject, String expectedContent);
}