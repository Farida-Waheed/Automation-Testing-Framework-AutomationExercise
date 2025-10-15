package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage {
    private WebDriver browser;

    // Signup elements
    private By newUserText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.xpath("//input[@data-qa='signup-name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");

    // Login elements
    private By loginText = By.xpath("//h2[text()='Login to your account']");
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By loginError = By.xpath("//p[text()='Your email or password is incorrect!']");
    private By emailExistsError = By.xpath("//p[text()='Email Address already exist!']");

    public SignupLoginPage(WebDriver browser) {
        this.browser = browser;
    }

    // --- Signup methods ---
    public boolean isNewUserVisible() {
        return browser.findElement(newUserText).isDisplayed();
    }

    public void enterSignupDetails(String name, String email) {
        browser.findElement(nameField).sendKeys(name);
        browser.findElement(emailField).sendKeys(email);
    }

    public void clickSignup() {
        browser.findElement(signupBtn).click();
    }

    // --- Login methods ---
    public boolean isLoginVisible() {
        return browser.findElement(loginText).isDisplayed();
    }

    public void enterLoginDetails(String email, String password) {
        browser.findElement(loginEmailField).sendKeys(email);
        browser.findElement(loginPasswordField).sendKeys(password);
    }

    public void clickLogin() {
        browser.findElement(loginBtn).click();
    }
    
    public boolean isLoginErrorVisible() {
    return browser.findElement(loginError).isDisplayed();
}

public boolean isEmailExistsErrorVisible() {
    return browser.findElement(emailExistsError).isDisplayed();
}
}
