package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {

    private static String SCREENSHOT_PATH = "TestOutputs/ScreenShots/";

    /**
     * Utility function for finding , clicking on elements after waiting to be clickable
     *
     * @param driver
     * @param locator
     */


    //TODO: function to click on elements
    public static void ClickOnElement(WebDriver driver, By locator) {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //TODO: function to send data
    public static void SendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    //TODO: function to get text
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //TODO: General Wait Function
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //TODO: Function to Scroll
    public static void Scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView();", findWebElement(driver, locator));
    }

    //TODO: function to convert From By to WebElement
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //TODO: function to select items from dropdown
    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }


    //TODO: TimeStamp function to get a unique name with a different format
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }


    //TODO: Taking ScreenShots
    public static void takeScreenShot(WebDriver driver, String ScreenShotname) throws IOException {
        File ScreenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File ScreenDest = new File(SCREENSHOT_PATH + ScreenShotname + "-" + getTimeStamp() + ".png");
        FileUtils.copyFile(ScreenSrc, ScreenDest);
        Allure.addAttachment(ScreenShotname, Files.newInputStream(Path.of(ScreenDest.getPath())));

    }

    //TODO: Taking a Full Page ScreenShot
    public static void TakeFullScreenShot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOT_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

    }

    //TODO: Function to generate Random Numbers
    public static int generateRandomNumbers(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    //TODO: Function to generate Unique Random Numbers (Not Repeated)
    public static Set<Integer> generateUniqueNumbers(int NumberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < NumberOfProductsNeeded) {
            int randomNumber = generateRandomNumbers(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    //TODO: function to check if the url returns the expected URL
    public static boolean VerifyUrl(WebDriver driver, String expectedUrl) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
            return true;
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
    }

    //TODO: function to arrange in descending order (Get latest File)
    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    //TODO: function to get cookies
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    //TODO: function to restore session
    public static void RestoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }
}
