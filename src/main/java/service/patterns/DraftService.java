package service.patterns;

import model.EmailMessage;
import org.openqa.selenium.WebDriver;
import pages.DraftPage;
import pages.MainPage;

public class DraftService {
    private MainPage mainPage;
    private DraftPage draftPage;

    public DraftService(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.draftPage = new DraftPage(driver);
    }

    public void createDraft(EmailMessage emailMessage) {
        draftPage.createDraft(emailMessage.getRecipient(), emailMessage.getSubject(), emailMessage.getBody());
    }

    public boolean isDraftPresent(EmailMessage emailMessage) {
        mainPage.openDrafts();
        return draftPage.isDraftPresent(emailMessage.getRecipient(), emailMessage.getSubject(), emailMessage.getBody());
    }

    public boolean isDraftCorrect(EmailMessage emailMessage) {
        return draftPage.isDraftCorrect(emailMessage.getRecipient(), emailMessage.getSubject(), emailMessage.getBody());
    }
}