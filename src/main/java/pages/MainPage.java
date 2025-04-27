package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '#drafts') and contains(text(), 'Drafts')]")
    private WebElement draftFolder;

    @FindBy(xpath = "//a[text()='Sent']")
    private WebElement sentFolder;

    @FindBy(xpath = "//a[contains(@aria-label, 'Google Account')]")
    private WebElement accountMenuButton;

    @FindBy(className = "sign-out")
    private WebElement signOutButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openDrafts() {
        clickElement(draftFolder);
        waitUtils.waitWithJsExecutor(driver);
    }

    public void openSent() {
        waitUtils.waitWithJsExecutor(driver);
        clickElement(sentFolder);
        waitUtils.waitWithJsExecutor(driver);
    }

    public void logout() {
        driver.get(accountMenuButton.getAttribute("href"));
        clickElement(signOutButton);
    }
}