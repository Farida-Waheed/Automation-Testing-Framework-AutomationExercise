package Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Payment page – handles card entry and confirmation
 */
public class PaymentPage {

    private WebDriver browser;
    private WebDriverWait wait;

    // ---------- LOCATORS ----------
    private By nameOnCard    = By.name("name_on_card");
    private By cardNumber    = By.name("card_number");
    private By cvc           = By.name("cvc");
    private By expiryMonth   = By.name("expiry_month");
    private By expiryYear    = By.name("expiry_year");
    private By payAndConfirm = By.id("submit");
    private By orderSuccessMsg    = By.xpath("//*[contains(.,'order') and contains(.,'successfully')]");
    private By adClose = By.cssSelector("div.grippy-host");

    // ---------- CONSTRUCTOR ----------
    public PaymentPage(WebDriver browser) {
        this.browser = browser;
        this.wait    = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    // ---------- ACTIONS ----------
    public void enterPaymentInfo(String name, String number, String cvcCode,
                                 String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(nameOnCard)).sendKeys(name);
        browser.findElement(cardNumber).sendKeys(number);
        browser.findElement(cvc).sendKeys(cvcCode);
        browser.findElement(expiryMonth).sendKeys(month);
        browser.findElement(expiryYear).sendKeys(year);
    }

    public void clickPayAndConfirm() {
        // 🔹 Step 1: Hide any overlay if present
        List<WebElement> overlays = browser.findElements(adClose);
        if (!overlays.isEmpty()) {
            ((JavascriptExecutor) browser)
                .executeScript("arguments[0].style.display='none';", overlays.get(0));
        }

        // 🔹 Step 2: Scroll button into view
        WebElement payBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(payAndConfirm));
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", payBtn);

        // 🔹 Step 3: Click when clickable
        wait.until(ExpectedConditions.elementToBeClickable(payBtn)).click();
    }

    // ---------- VERIFICATIONS ----------
    public boolean isOrderSuccessVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMsg));
        return true;
    } catch (TimeoutException e) {
        return false;
    }
    }
}
