package driver;

import driver.patterns.DriverSingleton;
import lombok.extern.slf4j.Slf4j;
import model.EmailMessage;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import service.InfoCreator;
import service.patterns.GmailFacade;
import utils.TestListener;

@Slf4j
@Listeners(TestListener.class)
public class MainDriver {

    protected static WebDriver driver;
    protected GmailFacade gmail;
    protected User testUser;
    protected EmailMessage testEmail;

    @BeforeSuite
    public void setUpDriver() {
        log.info("Setting up WebDriver and initializing pages...");
        driver = DriverSingleton.getDriver();
        driver.get("https://www.gmail.com");
        gmail = new GmailFacade(driver);
        testUser = InfoCreator.getInfoUser();
        testEmail = InfoCreator.getInfoMail();
        log.info("WebDriver setup complete.");
    }

    public static WebDriver getDriver() {
        log.info("WebDriver initialized successfully.");
        return DriverSingleton.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        log.info("Logging out and quitting WebDriver...");
        gmail.logout();
        DriverSingleton.quitDriver();
        log.info("WebDriver session ended.");
    }
}
