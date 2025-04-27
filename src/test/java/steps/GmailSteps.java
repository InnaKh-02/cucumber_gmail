package steps;

import driver.patterns.DriverSingleton;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import model.EmailMessage;
import model.User;
import org.openqa.selenium.WebDriver;
import service.patterns.GmailFacade;

import java.util.Map;

import static org.testng.Assert.*;
@Slf4j
public class GmailSteps {
    protected static WebDriver driver;
    protected GmailFacade gmail;
    private User testUser;
    private EmailMessage testEmail;

    @Given("User opens Gmail login page")
    public void setUpDriver() {
        log.info("Setting up WebDriver and initializing Gmail...");
        driver = DriverSingleton.getDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://mail.google.com/");
        gmail = new GmailFacade(driver);
        log.info("WebDriver setup complete.");
    }

    @Given("the user has the following credentials:")
    public void user_has_credentials(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        testUser = new User(data.get("email"), data.get("password"));
        log.info("User credentials loaded: {}", testUser);
    }

    @Given("the email message details are:")
    public void email_message_details(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        testEmail = new EmailMessage(data.get("recipient"), data.get("subject"), data.get("body"));
        log.info("Email message loaded: {}", testEmail);
    }

    @When("User logs in")
    public void user_logs_in() {
        log.info("Logging in...");
        gmail.login(testUser);
    }

    @Then("User should see account email {string}")
    public void user_should_see_account_email(String expectedEmail) {
        boolean isLoggedIn = gmail.isAccountPresent(expectedEmail);
        log.info("Login check: {}", isLoggedIn);
        assertTrue(isLoggedIn, "Login failed!");
    }

    @When("User creates draft email")
    public void user_creates_draft_email() {
        log.info("Creating draft...");
        gmail.createDraft(testEmail);
    }

    @Then("Draft email should be present")
    public void draft_email_should_be_present() {
        boolean isDraftPresent = gmail.isDraftPresent(testEmail);
        log.info("Draft presence: {}", isDraftPresent);
        assertTrue(isDraftPresent, "Draft email is not present!");
    }

    @Then("Draft email content should match")
    public void draft_email_content_should_match() {
        boolean isDraftCorrect = gmail.isDraftCorrect(testEmail);
        log.info("Draft content match: {}", isDraftCorrect);
        assertTrue(isDraftCorrect, "Draft email content does not match!");
    }

    @When("User sends the draft")
    public void user_sends_the_draft() {
        log.info("Sending draft...");
        gmail.sendDraft(testEmail.getSubject());
    }

    @Then("Draft email should not be present")
    public void draft_email_should_not_be_present() {
        boolean isDraftStillPresent = gmail.isDraftPresent(testEmail);
        log.info("Draft still present after send: {}", isDraftStillPresent);
        assertFalse(isDraftStillPresent, "Draft email still present after sending!");
    }

    @Then("Sent email should be present")
    public void sent_email_should_be_present() {
        boolean isSentPresent = gmail.isSentPresent(testEmail);
        log.info("Sent email presence: {}", isSentPresent);
        assertTrue(isSentPresent, "Sent email not found in Sent folder!");
    }

    @Then("User logs out")
    public void user_logs_out() {
        log.info("Logging out...");
        gmail.logout();
    }

    @After
    public void tearDown() {
        log.info("Quitting WebDriver...");
        DriverSingleton.quitDriver();
        log.info("WebDriver session ended.");
    }
}
