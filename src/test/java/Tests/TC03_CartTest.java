package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ItestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
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
public class TC03_CartTest {
    private final String USERNAME = DataUtils.getJsonData("ValidLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("ValidLogin", "password");

    public TC03_CartTest() throws FileNotFoundException {
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
    public void ComparingPricesTC() throws IOException {
        String totalprice = new P01_LoginPage(getDriver())
                .enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .ClickOnLoginButton()
                .addRandomProducts(3, 6)
                .getTotalPricesOfSelectedProducts();
        new P02_LandingPage(getDriver()).ClickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparingPrices(totalprice));
        // or without creating a string u can declare inside the comparingPrices a new P02_LandingPage(getDriver()).getTotalPricesOfSelectedProducts
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }

}
