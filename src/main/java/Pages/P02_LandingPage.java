package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_LandingPage {
    static float TotalPrice = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> SelectedProducts;
    private final WebDriver driver;
    private final By addtoCartButtonForAllTheProducts = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[text() ='Remove']");
    private final By CartIcon = By.className("shopping_cart_link");
    private final By PricesOfSelectedProductsLocator = By.xpath("//button[text()='Remove']/preceding-sibling::div[@class='inventory_item_price']");

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getNumberOfSelectedProductsOnCart() {
        return numberOfProductsOnCartIcon;
    }

    public P02_LandingPage addAllProductsToCart() {
        allProducts = driver.findElements(addtoCartButtonForAllTheProducts); // adding the locator to the list to loop on it
        LogsUtils.info("The Number Of Products " + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) { // looping inside the (allProducts)List to add each item to the cart
            By addtoCartButtonForAllTheProducts = By.xpath("(//button[contains(@class,'btn_inventory')])[" + i + "]");
            Utility.ClickOnElement(driver, addtoCartButtonForAllTheProducts);
        }
        return this;
    }

    public String getNumberOfProductsOnCartIcon() { // Returns the number on the cart icon after selecting a product
        try {
            LogsUtils.info("Number Of Products on Cart : " + Utility.getText(driver, numberOfProductsOnCartIcon));
            return Utility.getText(driver, numberOfProductsOnCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() { // Returns the selected number of products
        try {
            SelectedProducts = driver.findElements(numberOfSelectedProducts);
            LogsUtils.info("Number Of Selected Products : " + SelectedProducts.size());
            return String.valueOf(SelectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public P02_LandingPage addRandomProducts(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> RandomNumbers = Utility
                .generateUniqueNumbers(numberOfProductsNeeded, totalNumberOfProducts);

        for (int random : RandomNumbers) {
            LogsUtils.info("Random Number : " + random);
            By addtoCartButtonForAllTheProducts = By.xpath("(//button[contains(@class,'btn_inventory')])[" + random + "]");
            Utility.ClickOnElement(driver, addtoCartButtonForAllTheProducts);
        }
        return this;
    }

    public boolean comparingNumberOfSelectedProductsWithCart() {
        return getNumberOfProductsOnCartIcon().equals(getNumberOfSelectedProducts());
    }

    public P03_CartPage ClickOnCartIcon() {
        Utility.ClickOnElement(driver, CartIcon);
        return new P03_CartPage(driver);
    }


    public String getTotalPricesOfSelectedProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(PricesOfSelectedProductsLocator);
            for (int i = 1; i < pricesOfSelectedProducts.size(); i++) {
                By element = By.xpath("(//button[text()='Remove']/preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver, element);
                TotalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            LogsUtils.info("Total Price = " + TotalPrice);
            return String.valueOf(TotalPrice);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

}
