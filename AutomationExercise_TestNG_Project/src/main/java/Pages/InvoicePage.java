package Pages;

import java.io.File;
import java.nio.file.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvoicePage {
    private WebDriver browser;
    private WebDriverWait wait;

    private By downloadInvoiceBtn = By.xpath("//a[text()='Download Invoice']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    private static final String DOWNLOAD_DIR = System.getProperty("user.home") + "/Downloads";

    public InvoicePage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(15));
    }

    public void clickDownloadInvoice() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadInvoiceBtn)).click();
    }

    public boolean isInvoiceDownloaded() {
        // crude check: look for a file named "invoice" in Downloads folder
        try {
            Thread.sleep(3000); // allow time to download
            File dir = new File(DOWNLOAD_DIR);
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().contains("invoice"));
            return files != null && files.length > 0;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }
}
