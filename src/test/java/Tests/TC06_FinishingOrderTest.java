package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ItestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P06_FinishingOrderPage;
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
public class TC06_FinishingOrderTest {
    private final String FIRSTNAME = DataUtils.getJsonData("CheckoutForm", "firstname");
    private final String LASTNAME = DataUtils.getJsonData("CheckoutForm", "lastname");
    private final String POSTAL_CODE = DataUtils.getJsonData("CheckoutForm", "postalcode");
    private final String USERNAME = DataUtils.getJsonData("ValidLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("ValidLogin", "password");

    public TC06_FinishingOrderTest() throws FileNotFoundException {
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
    public void FinishOrderTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .ClickOnLoginButton()
                .addRandomProducts(3, 6)
                .ClickOnCartIcon().ClickOnCheckoutButton()
                .FillingInformationForm(FIRSTNAME, LASTNAME, POSTAL_CODE)
                .ClickOnContinueButton()
                .ClickOnFinishButton();

        Assert.assertTrue(new P06_FinishingOrderPage(getDriver()).CheckingTheFinalText());

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }


}
