package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver browser;

    // ------------------- ALL PRODUCTS PAGE -------------------
    private By allProductsHeader = By.xpath("//h2[text()='All Products']");
    private By productsList = By.cssSelector("div.col-sm-4"); // list of products
    private By firstProductViewBtn = By.xpath("(//a[text()='View Product'])[1]");

    // Product detail page
    private By productContainer = By.cssSelector("div.product-information");
    private By productName = By.cssSelector("div.product-information h2");
    private By quantityInput = By.id("quantity"); // Quantity input on product detail
    private By addToCartBtnDetail = By.cssSelector("button.cart"); // Add to cart on detail page

    // ------------------- CART RELATED -------------------
    private By addToCartBtnFirst = By.xpath("(//a[text()='Add to cart'])[1]");
    private By addToCartBtnSecond = By.xpath("(//a[text()='Add to cart'])[2]");
    private By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");
    private By viewCartBtn = By.xpath("//u[text()='View Cart']");

    // ------------------- SEARCH FEATURE -------------------
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");
    private By searchedProductsList = By.cssSelector("div.features_items div.col-sm-4");
    
    // ------------------- BRANDS FEATURE -------------------
private By brandsSidebar = By.xpath("//div[@class='brands_products']");  
private By firstBrand = By.xpath("(//div[@class='brands-name']//a)[1]");  
private By brandHeader = By.cssSelector("h2.title.text-center");

    // ------------------- CONSTRUCTOR -------------------
    public ProductsPage(WebDriver browser) {
        this.browser = browser;
    }

    // ------------------- ALL PRODUCTS METHODS -------------------
    public boolean isAllProductsVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeader));
        return browser.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isProductsListVisible() {
        return !browser.findElements(productsList).isEmpty();
    }

    public void clickFirstProductView() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstProductViewBtn));
        ((JavascriptExecutor) browser).executeScript(
                "arguments[0].scrollIntoView(true);", browser.findElement(firstProductViewBtn));
        browser.findElement(firstProductViewBtn).click();
    }

    // ------------------- PRODUCT DETAIL METHODS -------------------
    public boolean isProductDetailVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productContainer));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Product detail not visible: " + e.getMessage());
            return false;
        }
    }

    public String getProductName() {
        return browser.findElement(productName).getText();
    }

    public void setQuantity(String qty) {
        WebElement quantityField = browser.findElement(quantityInput);
        quantityField.clear();
        quantityField.sendKeys(qty);
    }

    public void clickAddToCartOnDetail() {
        browser.findElement(addToCartBtnDetail).click();
    }

    // ------------------- ADD PRODUCT TO CART FROM LIST -------------------
    public void addProductToCart(int productIndex) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        WebElement product = browser.findElement(
                By.xpath("(//div[@class='product-overlay'])[" + productIndex + "]/.."));

        ((JavascriptExecutor) browser).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", product);

        WebElement addToCartBtn = product.findElement(By.xpath(".//a[text()='Add to cart']"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }

    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn));
        browser.findElement(continueShoppingBtn).click();
    }

    public void clickViewCart() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn));
        browser.findElement(viewCartBtn).click();
    }

    // ------------------- SEARCH FEATURE METHODS -------------------
    public void enterSearchQuery(String query) {
        browser.findElement(searchInput).clear();
        browser.findElement(searchInput).sendKeys(query);
    }

    public void clickSearchButton() {
        browser.findElement(searchButton).click();
    }

    public boolean isSearchedProductsHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchedProductsHeader));
            return browser.findElement(searchedProductsHeader).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getSearchedProductNames() {
        List<WebElement> products = browser.findElements(searchedProductsList);
        List<String> names = new ArrayList<>();

        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector("div.productinfo p")).getText();
            names.add(name);
        }

        return names;
    }

    public String getProductNameFromList(int productIndex) {
        return browser.findElement(
                By.xpath("(//div[@class='productinfo']/p)[" + productIndex + "]")).getText();
    }

    public String getProductPriceFromList(int productIndex) {
        return browser.findElement(
                By.xpath("(//div[@class='productinfo']/h2)[" + productIndex + "]")).getText();
    }
    
    public boolean isBrandsSidebarVisible() {
    return !browser.findElements(brandsSidebar).isEmpty()
            && browser.findElement(brandsSidebar).isDisplayed();
}

// Click a brand by its index in the sidebar (1-based index)
public void clickBrand(int index) {
    By brandLink = By.xpath("(//div[@class='brands-name']//a)[" + index + "]");
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(brandLink));
    browser.findElement(brandLink).click();
}


public String getBrandHeaderText() {
    return browser.findElement(brandHeader).getText().trim();
}

// Click a brand by index and wait until the brand header is updated
public String selectBrandAndGetHeader(int index) {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));

    // 1️⃣ Click the brand link by index (1-based)
    By brandLink = By.xpath("(//div[@class='brands-name']//a)[" + index + "]");
    wait.until(ExpectedConditions.elementToBeClickable(brandLink)).click();

    // 2️⃣ Wait for brand header to become visible and return its text
    By brandHeader = By.cssSelector("h2.title.text-center");
    wait.until(ExpectedConditions.visibilityOfElementLocated(brandHeader));
    return browser.findElement(brandHeader).getText().trim();
}

public void addProductsToCart(int productIndex) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        // Locate the product container dynamically
        WebElement product = browser.findElement(By.xpath("(//div[@class='product-overlay'])[" + productIndex + "]/.."));

        // Scroll the product into view
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

        // Find the "Add to cart" button inside this product
        WebElement addToCartBtn = product.findElement(By.xpath(".//a[text()='Add to cart']"));

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

        // Click the button
        addToCartBtn.click();
    }


}
