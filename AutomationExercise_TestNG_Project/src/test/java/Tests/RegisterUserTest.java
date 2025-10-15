package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

public class RegisterUserTest extends BaseTest {

    private HomePage homePage;
    private SignupLoginPage signupLoginPage;
    private AccountCreationPage accountCreationPage;
    private AccountCreatedPage accountCreatedPage;
    private String uniqueEmail;

    // PART ONE – Navigate to Signup Page
    @Test(groups = {"smoke", "register"}, description = "Open home page and navigate to Signup/Login")
    public void openSignupPage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        homePage.clickSignupLogin();

        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isNewUserVisible(), "New User Signup is not visible");
    }

    // PART TWO – Fill Signup + Account Information
    @Test(groups = {"smoke", "register"}, dependsOnMethods = {"openSignupPage"}, description = "Fill signup details and create account")
    public void createNewAccount() {
        // Generate a unique email to avoid duplicates
        uniqueEmail = "farida" + System.currentTimeMillis() + "@mail.com";

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
    @Test(groups = {"smoke", "register"}, dependsOnMethods = {"createNewAccount"}, description = "Verify logged in")
    public void verifyAccount() {
        Assert.assertTrue(homePage.isLoggedInAsVisible(), "Logged in as username not visible");
    }

    // PART FOUR – Delete Account
    @Test(groups = {"register", "cleanup"}, dependsOnMethods = {"verifyAccount"}, description = "Delete account")
    public void deleteAccount() {
        homePage.clickDeleteAccount();
        Assert.assertTrue(homePage.isAccountDeletedVisible(), "Account Deleted message not visible");
        homePage.clickContinue();
    }
}
