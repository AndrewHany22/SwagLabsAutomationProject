package Listeners;

import Pages.P02_LandingPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
       /* File logFile = Utility.getLatestFile(LogsUtils.LOGS_PATH);
        try {
            assert logFile != null;
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                LogsUtils.info("Test Case-" + testResult.getName() + " Failed");
                Utility.takeScreenShot(getDriver(), testResult.getName());
                Utility.TakeFullScreenShot(getDriver(), new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
