package service.patterns;

import model.EmailMessage;
import model.User;
import org.openqa.selenium.WebDriver;

public class GmailFacade {
    private AuthService authService;
    private DraftService draftService;
    private SentService sentService;
    //private MainPage mainPage;

    public GmailFacade(WebDriver driver) {
        this.authService = new AuthService(driver);
        this.draftService = new DraftService(driver);
        this.sentService = new SentService(driver);
        //this.mainPage = new MainPage(driver);
    }

    public void login(User user) {
        authService.login(user);
    }

    public boolean isAccountPresent(String email) {
        return authService.isAccountPresent(email);
    }

    public void logout() {
        authService.logout();
    }

    public void createDraft(EmailMessage email) {
        draftService.createDraft(email);
    }

    public boolean isDraftPresent(EmailMessage email) {
        return draftService.isDraftPresent(email);
    }

    public boolean isDraftCorrect(EmailMessage email) {
        return draftService.isDraftCorrect(email);
    }

    public void sendDraft(String email) {
        sentService.sendDraft(email);
    }

    public boolean isSentPresent(EmailMessage email) {
        return sentService.isSentPresent(email);
    }

}
