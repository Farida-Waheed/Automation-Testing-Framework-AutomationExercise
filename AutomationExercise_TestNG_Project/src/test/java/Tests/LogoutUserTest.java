package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

public class LogoutUserTest extends BaseTest {

    private HomePage homePage;
    private SignupLoginPage signupLoginPage;

    // Existing persistent test account
    private final String registeredEmail = "f.@mail.com";
    private final String registeredPassword = "Pass1234";

    // PART ONE – Navigate to Login Page
    @Test(description = "Open home page and navigate to Login")
    public void openLoginPage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        homePage.clickSignupLogin();
        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isLoginVisible(), "'Login to your account' is not visible");
    }

    // PART TWO – Login with existing account
    @Test(dependsOnMethods = {"openLoginPage"}, description = "Login with correct credentials")
    public void loginUser() {
        signupLoginPage.enterLoginDetails(registeredEmail, registeredPassword);
        signupLoginPage.clickLogin();
    }

    // PART THREE – Verify login
    @Test(dependsOnMethods = {"loginUser"}, description = "Verify user is logged in")
    public void verifyLogin() {
        // DO NOT re-initialize HomePage, reuse the same instance
        Assert.assertTrue(homePage.isLoggedInAsVisible(), "Logged in as username not visible");
    }

    // PART FOUR – Logout
    @Test(dependsOnMethods = {"verifyLogin"}, description = "Logout user and verify navigation to login page")
    public void logoutUser() {
        Assert.assertTrue(homePage.isLogOutVisible(), "Logout button not visible");
        homePage.clickLogout();

        // After logout, navigate to the login section
        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isLoginVisible(), "User is not navigated to login page after logout");
    }
}
