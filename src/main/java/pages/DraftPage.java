package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class DraftPage extends BasePage {

    @FindBy(xpath = "//div[text()='Compose']")
    private WebElement composeButton;

    @FindBy(xpath = "//input[contains(@aria-label, 'To')]")
    private WebElement toField;

    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    @FindBy(xpath = "//*[contains(@role,'textbox')]")
    private WebElement bodyField;

    @FindBy(css = "div.ae4.UI")
    private List<WebElement> visibleDrafts;

    @FindBy(xpath = "//img[@aria-label='Save & close']")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//div[contains(@class, 'oL aDm az9')]")
    private WebElement recipientDisplayField;

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public void createDraft(String recipient, String subject, String body) {
        clickElement(composeButton);
        waitUtils.waitWithJsExecutor(driver);
        enterText(toField, recipient);
        enterText(subjectField, subject);
        enterText(bodyField, body);
        waitUtils.waitWithJsExecutor(driver);
        clickElement(saveAndCloseButton);
    }

    public boolean isDraftCorrect(String recipient, String subject, String body) {
        waitUtils.waitForVisibility(visibleDrafts);
        for (WebElement e : visibleDrafts) {
            if (e.isDisplayed() && e.getText().contains(subject)) {
                e.click();
                waitUtils.waitWithJsExecutor(driver);
                boolean isRecipientCorrect = recipientDisplayField.getText().equals(recipient);
                boolean isBodyCorrect = bodyField.getText().equals(body);
                return isRecipientCorrect && isBodyCorrect;
            }
        }
        return false;
    }

    public boolean isDraftPresent(String recipient, String subject, String body) {
        try {
            waitUtils.waitForVisibility(visibleDrafts);

            for (WebElement draft : visibleDrafts) {
                waitUtils.waitForVisibility(draft);

                if (draft.isDisplayed() && draft.getText().contains(subject)) {
                    draft.click();
                    waitUtils.waitWithJsExecutor(driver);

                    if (recipientDisplayField.getText().equals(recipient)
                            && bodyField.getText().equals(body)) {
                        clickElement(saveAndCloseButton);
                        return true;
                    }
                    clickElement(saveAndCloseButton);
                }
            }
        } catch (TimeoutException e) {
            return false;
        }
        return false;
    }
}