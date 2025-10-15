package Pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {

    private WebDriver browser;
    private WebDriverWait wait;

    // Sidebar
    private By categoriesSidebar = By.xpath("//div[@class='left-sidebar']");

    // Women category (collapsible)
    private By womenCategory = By.xpath("//a[normalize-space()='Women']");

    // Example sub-categories
    private By womenDress = By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]");
    private By menCategory = By.xpath("//a[normalize-space()='Men']");
    private By menTshirts = By.xpath("//div[@id='Men']//a[contains(text(),'Tshirts')]");

    // Page header (e.g. WOMEN - DRESS PRODUCTS)
    private By categoryHeader = By.cssSelector("h2.title.text-center");

    public CategoryPage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public boolean isCategoriesSidebarVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoriesSidebar)).isDisplayed();
    }

    public void clickWomenCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(womenCategory)).click();
    }

    public void clickWomenDress() {
        wait.until(ExpectedConditions.elementToBeClickable(womenDress)).click();
    }

    public String getCategoryHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryHeader)).getText().trim();
    }

    public void clickMenCategory() {
    wait.until(ExpectedConditions.elementToBeClickable(menCategory)).click();
}

public void clickMenTshirts() {
    wait.until(ExpectedConditions.elementToBeClickable(menTshirts)).click();
}
}
