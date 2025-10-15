package Pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Checkout page – Address, Review, and Place Order actions
 */
public class CheckoutPage {

    private WebDriver browser;
    private WebDriverWait wait;

    // ---------- LOCATORS ----------
    private By registerLoginBtn     = By.xpath("//u[text()='Register / Login']");
    private By addressDetailsHeader = By.xpath("//h2[text()='Address Details']");
    private By reviewOrderHeader    = By.xpath("//h2[text()='Review Your Order']");
    private By commentArea          = By.name("message");
    private By placeOrderBtn        = By.xpath("//a[text()='Place Order']");
    
    // ---------- ADDRESS DETAILS ----------
private By deliveryAddressBox = By.id("address_delivery"); 
private By billingAddressBox  = By.id("address_invoice");

    // ---------- CONSTRUCTOR ----------
    public CheckoutPage(WebDriver browser) {
        this.browser = browser;
        this.wait    = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    // ---------- ACTIONS ----------
    public void clickRegisterLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLoginBtn)).click();
    }

    public void enterOrderComment(String comment) {
        WebElement area = wait.until(ExpectedConditions.elementToBeClickable(commentArea));
        area.clear();
        area.sendKeys(comment);
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    // ---------- VERIFICATIONS ----------
    public boolean isAddressDetailsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addressDetailsHeader)).isDisplayed();
    }

    public boolean isReviewOrderVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(reviewOrderHeader)).isDisplayed();
    }
    
    public String getDeliveryAddressText() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddressBox)).getText();
}

public String getBillingAddressText() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressBox)).getText();
}
}
