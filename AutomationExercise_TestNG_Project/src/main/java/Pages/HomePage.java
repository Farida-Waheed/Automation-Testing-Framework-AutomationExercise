package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Farida Waheed
 */
public class HomePage {
    private WebDriver browser;
    private By signupLoginBtn = By.xpath("//a[@href='/login']"); 
    private By homeBanner = By.xpath("//div[@class='carousel-inner']");
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[@href='/delete_account']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");
    private By accountDeletedMsg = By.xpath("//b[text()='Account Deleted!']");
    private By logoutBtn = By.xpath("//a[@href='/logout']");
    private By homeIcon = By.xpath("//a[@href='/']");
    private By logout = By.xpath("//h2[text()='Login to your account']");
    private By contactUsBtn = By.xpath("//a[@href='/contact_us']");
    private By testCasesBtn = By.xpath("//a[@href='/test_cases']");
    private By testCasesPanel = By.xpath("//*[@id=\"form\"]/div/div[2]/h5/span");
    private By productsBtn = By.xpath("//a[@href='/products']");
    
    // Subscription elements
    private By subscriptionHeader = By.xpath("//h2[text()='Subscription']");
    private By subscriptionInput = By.id("susbscribe_email"); // check actual ID
    private By subscriptionButton = By.id("subscribe");        // check actual ID
    private By successMessage = By.id("success-subscribe");   // check actual ID
    private By cartLink = By.xpath("//a[@href='/view_cart']");
    
    // --- Recommended Items ---
private By recommendedItemsHeader = By.xpath("//h2[text()='recommended items']");
private By firstRecommendedAddToCartBtn = By.xpath("(//div[@id='recommended-item-carousel']//a[text()='Add to cart'])[1]");
private By viewCartBtn = By.xpath("//u[text()='View Cart']");

// Locators
private By scrollUpArrow = By.id("scrollUp"); 
private By heroHeader = By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']");


    public HomePage(WebDriver browser) {
        this.browser = browser;
    }

    public boolean isHomePageVisible() {
        return browser.findElement(homeBanner).isDisplayed();
    }

    public void clickSignupLogin() {
        browser.findElement(signupLoginBtn).click();
    }
    
    public boolean isLoggedInAsVisible() {
        return browser.findElement(loggedInAs).isDisplayed();
    }
    
    public void clickDeleteAccount() {
        browser.findElement(deleteAccountBtn).click();
    }
    
    public void clickLogout() {
        browser.findElement(logoutBtn).click();
    }
    
    public void clickContinue() {
        browser.findElement(continueBtn).click();
    }

    public boolean isAccountDeletedVisible() {
        return browser.findElement(accountDeletedMsg).isDisplayed();
    }
    
    public boolean isLogOutVisible() {
        return browser.findElement(logoutBtn).isDisplayed();
    }

    public void clickHomeIcon() {
        browser.findElement(homeIcon).click();
    }
   
    public void clickContactUs() {
        browser.findElement(contactUsBtn).click();
    }

    public void clickTestCases() {
        browser.findElement(testCasesBtn).click();
    }

    public boolean isTestCasesPageVisible() {
        return browser.findElement(testCasesPanel).isDisplayed();
    }
    
    public void clickProducts() {
    browser.findElement(productsBtn).click();
}
        public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean isSubscriptionHeaderVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionHeader));
            return browser.findElement(subscriptionHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        browser.findElement(subscriptionInput).clear();
        browser.findElement(subscriptionInput).sendKeys(email);
    }

    public void clickSubscribeButton() {
        browser.findElement(subscriptionButton).click();
    }

    public boolean isSuccessMessageVisible() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return browser.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickCart() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
    
    public boolean isRecommendedItemsVisible() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recommendedItemsHeader));
        return browser.findElement(recommendedItemsHeader).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void clickAddToCartOnRecommended() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(firstRecommendedAddToCartBtn)).click();
}

public void clickViewCartFromRecommended() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
}

// Action: click the scroll-up arrow
public void clickScrollUpArrow() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(scrollUpArrow)).click();
}

// Verify hero header text after scroll up
public boolean isHeroHeaderVisible() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heroHeader)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
    // Scroll page up to top without clicking the arrow
public void scrollToTop() {
    JavascriptExecutor js = (JavascriptExecutor) browser;
    js.executeScript("window.scrollTo(0, 0);");
}
}
