package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;

public class TestCasesPageTest extends BaseTest {

    private HomePage homePage;

    // PART ONE – Navigate to Home Page
    @Test(description = "Launch browser and verify home page")
    public void openHomePage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
    }

    // PART TWO – Click on Test Cases button
    @Test(dependsOnMethods = {"openHomePage"}, description = "Click Test Cases button")
    public void navigateToTestCasesPage() {
        homePage.clickTestCases(); // You need to implement this method in HomePage
        Assert.assertTrue(homePage.isTestCasesPageVisible(), "Test Cases page not visible");
    }
}
