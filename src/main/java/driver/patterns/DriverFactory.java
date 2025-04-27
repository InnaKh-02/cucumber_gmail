package driver.patterns;

import driver.patterns.browsers.ChromeDriverImpl;
import driver.patterns.browsers.FirefoxDriverImpl;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static WebDriver createDriver(String browser) {
        BrowserDriver browserDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                browserDriver = new FirefoxDriverImpl();
                break;
            case "chrome":
            default:
                browserDriver = new ChromeDriverImpl();
                break;
        }

        return browserDriver.createDriver();
    }
}
