package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ItestResultListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ItestResultListenerClass.class})
public class TC01_LoginTest {
    private final String USERNAME = DataUtils.getJsonData("ValidLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("ValidLogin", "password");


    public TC01_LoginTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Edge Browser is Opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "Base_Url"));
        LogsUtils.info("Base Url is Redirected Successfully");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void ValidLoginTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .ClickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver())
                .assertLoginTc(DataUtils.getPropertyValue("environment", "Home_Url")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }


}
