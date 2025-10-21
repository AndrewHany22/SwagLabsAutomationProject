package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P06_FinishingOrderPage {
    private final WebDriver driver;
    private final By FinalText = By.className("complete-header");

    public P06_FinishingOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean CheckingTheFinalText() {
        return Utility.findWebElement(driver, FinalText).isDisplayed();
    }

}
