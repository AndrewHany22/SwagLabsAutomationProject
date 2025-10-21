package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverviewPage {
    private final WebDriver driver;
    private final By SubTotal = By.className("summary_subtotal_label");
    private final By Tax = By.className("summary_tax_label");
    private final By Total = By.className("summary_total_label");
    private final By FinishButton = By.xpath("//div /a[contains(@class,'cart_button')]");

    public P05_OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public float getSubTotal() {
        return Float.parseFloat(Utility.getText(driver, SubTotal).replace("Item total: $", ""));
    }

    public float getTax() {
        return Float.parseFloat(Utility.getText(driver, Tax).replace("Tax: $", ""));
    }

    public float getTotal() {
        LogsUtils.info("Actual Total Price=  " + Utility.getText(driver, Total).replace("Total: $", ""));
        return Float.parseFloat(Utility.getText(driver, Total).replace("Total: $", ""));
    }

    public String CalculateTotalPrice() {
        LogsUtils.info("Calculated Total Price=  " + (getSubTotal() + getTax()));
        return String.valueOf(getSubTotal() + getTax());
    }

    public boolean comparingPrices() {
        return CalculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public P06_FinishingOrderPage ClickOnFinishButton() {
        Utility.ClickOnElement(driver, FinishButton);
        return new P06_FinishingOrderPage(driver);
    }


}
