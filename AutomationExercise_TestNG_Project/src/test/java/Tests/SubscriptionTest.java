package Tests;

import Base.BaseTest;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubscriptionTest extends BaseTest {

    private HomePage homePage;

    @Test(description = "Verify subscription functionality on home page")
    public void verifySubscription() {
        homePage = new HomePage(browser);

        // 3. Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 4. Scroll to footer
        homePage.scrollToFooter();

        // 5. Verify 'SUBSCRIPTION' text
        Assert.assertTrue(homePage.isSubscriptionHeaderVisible(), "'SUBSCRIPTION' header not visible");

        // 6. Enter email and click subscribe
        String email = "testemail@example.com";
        homePage.enterEmail(email);
        homePage.clickSubscribeButton();

        // 7. Verify success message
        Assert.assertTrue(homePage.isSuccessMessageVisible(), "Success message not visible");
    }
}
