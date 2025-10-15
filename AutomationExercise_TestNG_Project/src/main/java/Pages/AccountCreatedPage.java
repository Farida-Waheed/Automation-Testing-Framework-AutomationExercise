package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Farida Waheed
 */
public class AccountCreatedPage {
    private WebDriver browser;

    private By accountCreatedMsg = By.xpath("//b[text()='Account Created!']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver browser) {
        this.browser = browser;
    }

    public boolean isAccountCreatedVisible() {
        return browser.findElement(accountCreatedMsg).isDisplayed();
    }

    public void clickContinue() {
        browser.findElement(continueBtn).click();
    }

}
