package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final WebDriver driver;
    private final By Username = By.id("user-name");
    private final By Password = By.id("password");
    private final By LoginButton = By.id("login-button");


    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUserName(String usernametext) {
        Utility.SendData(driver, Username, usernametext);
        return this;
    }

    public P01_LoginPage enterPassword(String passwordtext) {
        Utility.SendData(driver, Password, passwordtext);
        return this;
    }

    public P02_LandingPage ClickOnLoginButton() {
        Utility.ClickOnElement(driver, LoginButton);
        return new P02_LandingPage(driver);
    }

    public boolean assertLoginTc(String ExpectedValue) {
        return driver.getCurrentUrl().equals(ExpectedValue);
    }


}
