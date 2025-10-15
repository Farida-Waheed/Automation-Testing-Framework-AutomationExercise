package Base;

import FrameWork.Selenium_FrameWork;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Farida Waheed
 */
public class BaseTest {

    protected static Selenium_FrameWork selenium; 
    protected static WebDriver browser;
    private static final String BASE_URL = "https://automationexercise.com";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        selenium = new Selenium_FrameWork();      
        selenium.initializeBrowser();
        browser = selenium.getBrowser();
        selenium.navigateToURL(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            saveScreenshotOnFailure();
        }
    }
    
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        selenium.closeBrowser(); // close the browser after all tests
    }

    /**
     * Capture screenshot and attach it to Allure report on failure
     */
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshotOnFailure() {
        // Access the internal driver through Selenium_FrameWork
        return ((TakesScreenshot) selenium.getBrowser()).getScreenshotAs(OutputType.BYTES);
    }
}
