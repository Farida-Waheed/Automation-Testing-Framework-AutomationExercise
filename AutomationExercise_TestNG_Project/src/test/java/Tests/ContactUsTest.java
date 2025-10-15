package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.ContactUsPage;
import Pages.HomePage;

public class ContactUsTest extends BaseTest {

    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @Test(description = "Test Contact Us form submission")
    public void contactUsFormTest() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Navigate to Contact Us
        homePage.clickContactUs(); // You need to add this method in HomePage.java
        contactUsPage = new ContactUsPage(browser);
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "'GET IN TOUCH' is not visible");

        // Fill form and upload file
        contactUsPage.fillContactForm("Farida", "farida@mail.com", "Test Subject", "This is a test message.");
        contactUsPage.uploadFile("D:\\College\\Projects\\AutomationExercise_TestNG_Project\\TestFile.txt"); // Adjust file path

        // Submit and accept alert
        contactUsPage.clickSubmit();
        contactUsPage.acceptAlert();

        // Verify success message
        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message is not visible");

        // Return home and verify
        contactUsPage.clickHome();
        Assert.assertTrue(homePage.isHomePageVisible(), "Not landed on home page");
    }
}
