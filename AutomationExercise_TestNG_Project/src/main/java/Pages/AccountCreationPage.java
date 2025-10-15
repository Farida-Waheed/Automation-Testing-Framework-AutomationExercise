package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Farida Waheed
 */
public class AccountCreationPage {
    private WebDriver browser;

    private By accountInfoHeader = By.xpath("//b[text()='Enter Account Information']");
    private By titleMr = By.id("id_gender1");
    private By password = By.id("password");
    private By days = By.id("days");
    private By months = By.id("months");
    private By years = By.id("years");
    private By newsletter = By.id("newsletter");
    private By offers = By.id("optin");

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public AccountCreationPage(WebDriver browser) {
        this.browser = browser;
    }

    public boolean isAccountInfoVisible() {
        return browser.findElement(accountInfoHeader).isDisplayed();
    }

    public void fillAccountInfo(String pass) {
        browser.findElement(titleMr).click();
        browser.findElement(password).sendKeys(pass);
        new Select(browser.findElement(days)).selectByValue("10");
        new Select(browser.findElement(months)).selectByValue("6");
        new Select(browser.findElement(years)).selectByValue("1995");
        browser.findElement(newsletter).click();
        browser.findElement(offers).click();
    }

    public void fillAddressInfo() {
        browser.findElement(firstName).sendKeys("Farida");
        browser.findElement(lastName).sendKeys("Waheed");
        browser.findElement(company).sendKeys("OpenAI");
        browser.findElement(address1).sendKeys("123 Test Street");
        browser.findElement(address2).sendKeys("Apt 4");
        new Select(browser.findElement(country)).selectByVisibleText("Canada");
        browser.findElement(state).sendKeys("Ontario");
        browser.findElement(city).sendKeys("Toronto");
        browser.findElement(zipcode).sendKeys("M5H2N2");
        browser.findElement(mobile).sendKeys("1234567890");
    }

    public void clickCreateAccount() {
        browser.findElement(createAccountBtn).click();
    }
}
