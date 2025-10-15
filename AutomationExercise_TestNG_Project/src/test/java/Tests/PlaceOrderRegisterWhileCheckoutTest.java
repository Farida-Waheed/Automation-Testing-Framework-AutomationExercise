package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderRegisterWhileCheckoutTest extends BaseTest {

    @Test
    public void placeOrderRegisterWhileCheckout() {

        HomePage home = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart = new CartPage(browser);
        CheckoutPage checkout = new CheckoutPage(browser);
        SignupLoginPage signup = new SignupLoginPage(browser);
        AccountCreationPage accountCreation = new AccountCreationPage(browser);
        AccountCreatedPage accountCreated = new AccountCreatedPage(browser);
        PaymentPage payment = new PaymentPage(browser);

        // 3. Verify that home page is visible
        Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");

        // 4. Add products to cart
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "All Products not visible");
        products.addProductToCart(1);
        products.clickContinueShopping();
        products.addProductToCart(2);
        products.clickViewCart();

        // 6. Verify that cart page is displayed
        Assert.assertTrue(cart.isCartVisible(), "Cart page is not visible");

        // 7. Click Proceed To Checkout
        cart.clickProceedToCheckout();

        // 8. Click 'Register / Login' button
        checkout.clickRegisterLogin();

        // 9. Fill all details in Signup and create account
        Assert.assertTrue(signup.isNewUserVisible(), "Signup form not visible");
        String uniqueEmail = "auto" + System.currentTimeMillis() + "@mail.com";
        signup.enterSignupDetails("Farida", uniqueEmail);
        signup.clickSignup();

        Assert.assertTrue(accountCreation.isAccountInfoVisible(), "Account info page not visible");
        accountCreation.fillAccountInfo("Test@1234");
        accountCreation.fillAddressInfo();
        accountCreation.clickCreateAccount();

        // 10. Verify 'ACCOUNT CREATED!' and click 'Continue'
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account Created message not visible");
        accountCreated.clickContinue();

        // 11. Verify 'Logged in as username'
        Assert.assertTrue(home.isLoggedInAsVisible(), "'Logged in as' is not visible");

        // 12. Click 'Cart' button
        cart.clickCart();

        // 13. Click 'Proceed To Checkout' button
        cart.clickProceedToCheckout();

        // 14. Verify Address Details and Review Your Order
        Assert.assertTrue(checkout.isAddressDetailsVisible(), "Address Details not visible");
        Assert.assertTrue(checkout.isReviewOrderVisible(), "Review Order not visible");

        // 15. Enter comment and click 'Place Order'
        checkout.enterOrderComment("Please deliver between 9–11 AM");
        checkout.clickPlaceOrder();

        // 16. Enter payment details and confirm
        payment.enterPaymentInfo("Farida Waheed", "4111111111111111", "123", "12", "2028");
        payment.clickPayAndConfirm();

        // Final Assertion – Order success
        Assert.assertTrue(payment.isOrderSuccessVisible(), "Order success message not visible");
        
        // 19. Click 'Delete Account' button
home.clickDeleteAccount();

// 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
Assert.assertTrue(home.isAccountDeletedVisible(), "Account Deleted message not visible");
home.clickContinue();
    }
}
