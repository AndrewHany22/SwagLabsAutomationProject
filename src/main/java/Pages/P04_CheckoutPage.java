package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckoutPage {
    private final WebDriver driver;
    private final By FirstName = By.id("first-name");
    private final By LastName = By.id("last-name");
    private final By PostalCode = By.id("postal-code");
    private final By ContinueButton = By.xpath("//div /input[contains(@class,'cart_button')]");


    public P04_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckoutPage FillingInformationForm(String fname, String lname, String zip) {
        Utility.SendData(driver, FirstName, fname);
        LogsUtils.info("First Name " + fname + " Sent Successfully");
        Utility.SendData(driver, LastName, lname);
        LogsUtils.info("Last Name " + lname + " Sent Successfully");
        Utility.SendData(driver, PostalCode, zip);
        LogsUtils.info("Postal Code " + zip + " Sent Successfully");

        return this;
    }

    public P05_OverviewPage ClickOnContinueButton() {
        Utility.ClickOnElement(driver, ContinueButton);
        return new P05_OverviewPage(driver);
    }


}
