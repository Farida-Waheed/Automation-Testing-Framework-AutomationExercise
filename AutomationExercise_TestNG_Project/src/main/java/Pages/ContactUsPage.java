package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private WebDriver browser;

    private By getInTouchHeader = By.xpath("//h2[text()='Get In Touch']");
    private By nameField = By.name("name");
    private By emailField = By.name("email");
    private By subjectField = By.name("subject");
    private By messageField = By.id("message");
    private By uploadFile = By.name("upload_file");
    private By submitBtn = By.name("submit");
    private By successMsg = By.xpath("//div[@class='status alert alert-success']");
    private By homeBtn = By.xpath("//a[@class='btn btn-success']");

    public ContactUsPage(WebDriver browser) {
        this.browser = browser;
    }

    public boolean isGetInTouchVisible() {
        return browser.findElement(getInTouchHeader).isDisplayed();
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        browser.findElement(nameField).sendKeys(name);
        browser.findElement(emailField).sendKeys(email);
        browser.findElement(subjectField).sendKeys(subject);
        browser.findElement(messageField).sendKeys(message);
    }

    public void uploadFile(String filePath) {
        browser.findElement(uploadFile).sendKeys(filePath);
    }

    public void clickSubmit() {
        browser.findElement(submitBtn).click();
    }

    public void acceptAlert() {
        browser.switchTo().alert().accept();
    }

    public boolean isSuccessMessageVisible() {
        return browser.findElement(successMsg).isDisplayed();
    }

    public void clickHome() {
        browser.findElement(homeBtn).click();
    }
}
