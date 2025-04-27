package service.patterns;

import model.EmailMessage;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SentPage;

public class SentService {
    private MainPage mainPage;
    private SentPage sentPage;

    public SentService(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.sentPage = new SentPage(driver);
    }

    public void sendDraft(String subject) {
        sentPage.sendDraft(subject);
    }

    public boolean isSentPresent(EmailMessage emailMessage) {
        mainPage.openSent();
        return sentPage.isSentPresent(emailMessage.getRecipient(), emailMessage.getSubject(), emailMessage.getBody());
    }
}
