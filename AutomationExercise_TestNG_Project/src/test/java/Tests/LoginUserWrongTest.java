package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

public class LoginUserWrongTest extends BaseTest {

    private HomePage homePage;
    private SignupLoginPage signupLoginPage;
    private AccountCreationPage accountCreationPage;
    private AccountCreatedPage accountCreatedPage;

    // Persistent test account
    private final String registeredEmail = "f.@mail.com";
    private final String registeredPassword = "Pass1234";
    private String uniqueEmail;

    // Invalid credentials for Test Case 3
    private final String invalidEmail = "wrong@mail.com";
    private final String invalidPassword = "WrongPass123";

    // PART ONE – Navigate to Signup Page
    @Test(groups = {"smoke", "loginWrong"}, description = "Open home page and navigate to Signup/Login")
    public void openSignupPage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        homePage.clickSignupLogin();

        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isNewUserVisible(), "New User Signup is not visible");
    }

    // PART TWO – Fill Signup + Account Information
    @Test(groups = {"smoke", "loginWrong"}, dependsOnMethods = {"openSignupPage"}, description = "Fill signup details and create account")
    public void createNewAccount() {
        uniqueEmail = registeredEmail; // Could generate unique email if needed
        signupLoginPage.enterSignupDetails("Farida", uniqueEmail);
        signupLoginPage.clickSignup();

        accountCreationPage = new AccountCreationPage(browser);
        Assert.assertTrue(accountCreationPage.isAccountInfoVisible(), "Account Information not visible");
        accountCreationPage.fillAccountInfo(registeredPassword);
        accountCreationPage.fillAddressInfo();
        accountCreationPage.clickCreateAccount();

        accountCreatedPage = new AccountCreatedPage(browser);
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account Created message not visible");
        accountCreatedPage.clickContinue();
    }

    // PART THREE – Verify Login
    @Test(groups = {"smoke", "loginWrong"}, dependsOnMethods = {"createNewAccount"}, description = "Verify logged in")
    public void verifyAccount() {
        Assert.assertTrue(homePage.isLoggedInAsVisible(), "Logged in as username not visible");
    }

    // PART FOUR – Logout User
    @Test(groups = {"loginWrong"}, dependsOnMethods = {"verifyAccount"}, description = "Logout user")
    public void logoutUser() {
        Assert.assertTrue(homePage.isLogOutVisible(), "Logout button not visible");
        homePage.clickLogout();
        homePage.clickHomeIcon();
    }

    // PART NINE – Login with incorrect credentials 
    @Test(groups = {"loginWrong"}, dependsOnMethods = {"logoutUser"}, description = "Login with incorrect email and password")
    public void loginWithInvalidCredentials() {
        homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isLoginVisible(), "'Login to your account' is not visible");

        signupLoginPage.enterLoginDetails(invalidEmail, invalidPassword);
        signupLoginPage.clickLogin();

        // Verify error message
        Assert.assertTrue(signupLoginPage.isLoginErrorVisible(), "Error 'Your email or password is incorrect!' not visible");
    }

}
