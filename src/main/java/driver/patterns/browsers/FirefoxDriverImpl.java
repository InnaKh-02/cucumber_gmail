package driver.patterns.browsers;

import driver.patterns.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverImpl implements BrowserDriver {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new org.openqa.selenium.firefox.FirefoxDriver(new FirefoxOptions()
                .addArguments("--disable-notifications", "--disable-popup-blocking"));
    }
}
