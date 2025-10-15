package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

public class RegisterExistingEmailTest extends BaseTest {

    private HomePage homePage;
    private SignupLoginPage signupLoginPage;

    // Existing persistent test account
    private final String registeredEmail = "f.@mail.com";
    private final String userName = "Farida";

    @Test(description = "Open home page and navigate to Signup/Login")
    public void openSignupPage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        homePage.clickSignupLogin();

        signupLoginPage = new SignupLoginPage(browser);
        Assert.assertTrue(signupLoginPage.isNewUserVisible(), "New User Signup is not visible");
    }

    @Test(dependsOnMethods = {"openSignupPage"}, description = "Try to register with an existing email")
    public void registerWithExistingEmail() {
        // Enter name and already registered email
        signupLoginPage.enterSignupDetails(userName, registeredEmail);
        signupLoginPage.clickSignup();

        // Verify error message
        Assert.assertTrue(signupLoginPage.isEmailExistsErrorVisible(), 
                "Error 'Email Address already exist!' is not visible");
    }
}
