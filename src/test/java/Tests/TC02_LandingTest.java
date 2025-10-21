package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ItestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;

@Listeners({IInvokedMethodListenerClass.class, ItestResultListenerClass.class})
public class TC02_LandingTest {
    private final String USERNAME = getJsonData("ValidLogin", "username");
    private final String PASSWORD = getJsonData("ValidLogin", "password");
    private Set<Cookie> cookies;

    public TC02_LandingTest() throws FileNotFoundException {
    }

    @BeforeClass
    public void Login() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Edge Browser is Opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "Base_Url"));
        LogsUtils.info("Base Url is Redirected Successfully");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver())
                .enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .ClickOnLoginButton();
        cookies = Utility.getAllCookies(getDriver());
        quitDriver();
    }

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Edge Browser is Opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "Base_Url"));
        LogsUtils.info("Base Url is Redirected Successfully");
        Utility.RestoreSession(getDriver(), cookies);
        getDriver().get(DataUtils.getPropertyValue("environment", "Home_Url"));
        getDriver().navigate().refresh();
    }

    @Test
    public void CheckingNumberOfSelectedProductsTC() throws IOException {

        new P02_LandingPage(getDriver())
                .addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void addRandomProductsToCartTC() {

        new P02_LandingPage(getDriver())
                .addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void ClickingOnCartIconTC() throws IOException {

        new P02_LandingPage(getDriver())
                .ClickOnCartIcon();
        Assert.assertTrue(Utility.
                VerifyUrl(getDriver(), DataUtils.getPropertyValue("environment", "Cart_Url")));
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }

    @AfterClass
    public void DeleteSession() {
        cookies.clear();
    }
}
