package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartSubscriptionTest extends BaseTest {

    private HomePage homePage;
    private CartPage cartPage;

    @Test(description = "Verify subscription functionality on Cart page")
    public void verifySubscriptionOnCartPage() {
        homePage = new HomePage(browser);
        cartPage = new CartPage(browser);

        // 3. Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 4. Click Cart button
        cartPage.clickCart();

        // 5. Scroll to footer
        cartPage.scrollToFooter();

        // 6. Verify 'SUBSCRIPTION' text
        Assert.assertTrue(cartPage.isSubscriptionHeaderVisible(), "'SUBSCRIPTION' header not visible");

        // 7. Enter email and click subscribe
        String email = "testemail@example.com";
        cartPage.enterEmail(email);
        cartPage.clickSubscribeButton();

        // 8. Verify success message
        Assert.assertTrue(cartPage.isSuccessMessageVisible(), "Success message not visible");
    }
}
