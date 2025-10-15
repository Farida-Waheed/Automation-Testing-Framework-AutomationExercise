package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

public class LoginUserTest extends BaseTest {

    private HomePage homePage;
    private SignupLoginPage signupLoginPage;
    private AccountCreationPage accountCreationPage;
    private AccountCreatedPage accountCreatedPage;

    // Persistent test account
    private final String registeredEmail = "f.@mail.com";
    private final String registeredPassword = "Pass1234";
    private String uniqueEmail;

    // PART ONE – Navigate to Signup Page
    @Test(groups = {"smoke","login"}, description = "Open home page and navigate to Signup/Login")
    public void openSignupPage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        homePage.clickSignupLogin();

        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isNewUserVisible(), "New User Signup is not visible");
    }

    // PART TWO – Fill Signup + Account Information
    @Test(groups = {"smoke","login"}, dependsOnMethods = {"openSignupPage"}, description = "Fill signup details and create account")
    public void createNewAccount() {
        uniqueEmail = "f.@mail.com"; // Could generate unique email if needed
        signupLoginPage.enterSignupDetails("Farida", uniqueEmail);
        signupLoginPage.clickSignup();

        accountCreationPage = new AccountCreationPage(browser);
        Assert.assertTrue(accountCreationPage.isAccountInfoVisible(), "Account Information not visible");
        accountCreationPage.fillAccountInfo("Pass1234");
        accountCreationPage.fillAddressInfo();
        accountCreationPage.clickCreateAccount();

        accountCreatedPage = new AccountCreatedPage(browser);
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account Created message not visible");
        accountCreatedPage.clickContinue();
    }

    // PART THREE – Verify Login
    @Test(groups = {"smoke", "login"}, dependsOnMethods = {"createNewAccount"}, description = "Verify logged in")
    public void verifyAccount() {
        Assert.assertTrue(homePage.isLoggedInAsVisible(), "Logged in as username not visible");
    }

    // PART FOUR – Logout User
    @Test(groups = {"login"}, dependsOnMethods = {"verifyAccount"}, description = "Logout user and verify navigation to login page")
    public void logoutUser() {
        Assert.assertTrue(homePage.isLogOutVisible(), "Logout button not visible");
        homePage.clickLogout();
        homePage.clickHomeIcon();
    }

    // PART FIVE – Navigate to Login Page
    @Test(groups = {"login"}, dependsOnMethods = {"logoutUser"}, description = "Open home page and navigate to Login")
    public void openLoginPage() {
        homePage = new HomePage(browser); // Re-initialize in case page reloads
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        homePage.clickSignupLogin();

        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isLoginVisible(), "'Login to your account' is not visible");
    }

    // PART SIX – Fill Login Details and Submit
    @Test(groups = {"login"}, dependsOnMethods = {"openLoginPage"}, description = "Enter login credentials and submit")
    public void loginUser() {
        signupLoginPage.enterLoginDetails(registeredEmail, registeredPassword);
        signupLoginPage.clickLogin();
    }

    // PART SEVEN – Verify Login
    @Test(groups = {"login"}, dependsOnMethods = {"loginUser"}, description = "Verify logged in")
    public void verify2Account() {
        homePage = new HomePage(browser); // Re-initialize in case page reloads
        Assert.assertTrue(homePage.isLoggedInAsVisible(), "Logged in as username not visible");
    }

    // PART EIGHT – Delete Account
    @Test(groups = {"login", "cleanup"}, dependsOnMethods = {"verify2Account"}, description = "Delete account")
    public void deleteAccount() {
        homePage.clickDeleteAccount();
        Assert.assertTrue(homePage.isAccountDeletedVisible(), "Account Deleted message not visible");
        homePage.clickContinue();
    }
}
