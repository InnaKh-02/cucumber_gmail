package service.patterns;

import model.User;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;

public class AuthService {
    private LoginPage loginPage;
    private MainPage mainPage;

    public AuthService(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
    }

    public void login(User user) {
        loginPage.login(user.getEmail(), user.getPassword());
    }

    public boolean isAccountPresent(String email) {
        return loginPage.isAccountPresent(email);
    }

    public void logout() {
        mainPage.logout();
    }
}