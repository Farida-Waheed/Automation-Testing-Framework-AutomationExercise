package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver browser;

    // Locators
    private By viewProductBtn = By.xpath("//a[contains(text(),'View Product')]"); // First product or dynamic if needed
    private By writeReviewHeader = By.xpath("//*[contains(text(),'Write Your Review')]");
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By reviewInput = By.id("review");
    private By submitReviewBtn = By.id("button-review");
    private By successMsg = By.xpath("//span[contains(text(),'Thank you for your review.')]");

    public ProductPage(WebDriver driver) {
        this.browser = driver;
    }

    public void clickViewProduct() {
        browser.findElement(viewProductBtn).click();
    }

    public boolean isWriteReviewVisible() {
    try {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(writeReviewHeader));
        return el.isDisplayed();
    } catch (TimeoutException e) {
        return false;
    }
}


   public void scrollToReviewSection() {
    WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(writeReviewHeader));
    ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", el);
}




    public void writeReview(String name, String email, String review) {
        browser.findElement(nameInput).sendKeys(name);
        browser.findElement(emailInput).sendKeys(email);
        browser.findElement(reviewInput).sendKeys(review);
        browser.findElement(submitReviewBtn).click();
    }

    public boolean isReviewSuccessVisible() {
        return browser.findElement(successMsg).isDisplayed();
    }
}
