package driver.patterns.browsers;

import driver.patterns.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverImpl implements BrowserDriver {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new org.openqa.selenium.chrome.ChromeDriver(new ChromeOptions()
                .addArguments("--disable-notifications", "--disable-popup-blocking", "--incognito"));
    }
}
