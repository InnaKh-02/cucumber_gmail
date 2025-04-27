package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;


public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickElement(WebElement element) {
        WebElement el = waitUtils.waitForVisibility(element);
        if (el != null) {
            el.click();
        } else {
            throw new NoSuchElementException("Element not found: " + element);
        }
    }


    public void enterText(WebElement element, String text) {
        waitUtils.waitForVisibility(element).sendKeys(text);
    }
}
