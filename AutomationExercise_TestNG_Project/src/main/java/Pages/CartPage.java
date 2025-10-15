package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver browser;
    private WebDriverWait wait;

    // ------------------- CART NAVIGATION -------------------
    private By cartBtn = By.xpath("//a[@href='/view_cart']"); // Header Cart button
    private By proceedToCheckoutBtn = By.xpath("//a[text()='Proceed To Checkout']");

    // ------------------- CART PRODUCTS -------------------
    private By cartItems = By.cssSelector("#cart_info_table tbody tr"); // Each cart row
    private By cartItem = By.cssSelector("#cart_items tr");
    private By productName = By.cssSelector("td.cart_description h4 a");
    private By productPrice = By.cssSelector("td.cart_price p");
    private By productQuantityInput = By.cssSelector("td.cart_quantity input"); // Correct for <input>
    private By productQuantityButton = By.cssSelector("td.cart_quantity > button");
    private By totalPrice = By.cssSelector("td.cart_total p");
    private By productQuantity = By.cssSelector("td.cart_quantity button");


    // ------------------- CART SUBSCRIPTION -------------------
    private By subscriptionHeader = By.xpath("//h2[text()='Subscription']");
    private By subscriptionInput = By.id("susbscribe_email");  // Check actual ID in UI
    private By subscriptionButton = By.id("subscribe");         // Check actual ID in UI
    private By successMessage = By.id("success-subscribe");     // Check actual ID in UI
    
    private By emptyCartMsg = By.xpath("//*[contains(text(),'Cart is empty')]");

    // ------------------- CONSTRUCTOR -------------------
    public CartPage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    // ------------------- CART NAVIGATION -------------------
    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }

    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }

    // ------------------- CART VERIFICATION -------------------
    /** Returns number of products currently in cart table */
    public int getNumberOfProduct() {
        return browser.findElements(cartItem).size();
    }

    public int getNumberOfProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems));
        return browser.findElements(cartItems).size();
    }

    public String getProductName(int index) {
        return browser.findElements(productName).get(index).getText().trim();
    }

    public String getProductPrice(int index) {
        return browser.findElements(productPrice).get(index).getText().trim();
    }

    /** Returns product quantity value (input’s current value) */
    public String getProductQuantity(int index) {
        return browser.findElements(productQuantityInput).get(index).getAttribute("value").trim();
    }
    
    public String getProductQuantity() {
        return browser.findElement(productQuantityButton).getText().trim();
    }
    
    public String getProductQuantities(int index) {
        return browser.findElements(productQuantity).get(index).getAttribute("value");
    }



    public String getTotalPrice(int index) {
        return browser.findElements(totalPrice).get(index).getText().trim();
    }

    /** Verify product with name exists in cart (case-insensitive) */
    public boolean isProductInCart(String product) {
        List<WebElement> products = browser.findElements(productName);
        return products.stream()
                       .map(e -> e.getText().trim().toLowerCase())
                       .anyMatch(name -> name.contains(product.toLowerCase()));
    }

    // ------------------- SUBSCRIPTION METHODS -------------------
    public boolean isSubscriptionHeaderVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionHeader));
            return browser.findElement(subscriptionHeader).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(subscriptionInput));
        input.clear();
        input.sendKeys(email);
    }

    public void clickSubscribeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionButton)).click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return browser.findElement(successMessage).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ------------------- UTILITY -------------------
    /** Scrolls to bottom of cart page to reveal footer/subscription */
    public void scrollToFooter() {
        ((JavascriptExecutor) browser)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    

public boolean isCartVisible() {
    return browser.getTitle().contains("Checkout") ||
           browser.findElement(By.id("cart_items")).isDisplayed();
}



public boolean isCartEmptyMessageVisible() {
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMsg));
        return browser.findElement(emptyCartMsg).isDisplayed();
    } catch (TimeoutException e) {
        return false;
    }
}

}
