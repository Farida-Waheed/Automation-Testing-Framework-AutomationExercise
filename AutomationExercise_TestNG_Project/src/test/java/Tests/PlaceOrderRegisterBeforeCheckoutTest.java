package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderRegisterBeforeCheckoutTest extends BaseTest {

    @Test(description = "Test Case 15: Place Order – Register before Checkout")
    public void placeOrderRegisterBeforeCheckout() {

        // ---------- 1. Home ----------
        HomePage home = new HomePage(browser);
        Assert.assertTrue(home.isHomePageVisible(), "Home page not visible");

        // ---------- 2. Signup/Login ----------
        home.clickSignupLogin();
        SignupLoginPage signup = new SignupLoginPage(browser);
        Assert.assertTrue(signup.isNewUserVisible(), "New User Signup not visible");

        // Dynamic email to avoid duplicates
        String email = "farida" + System.currentTimeMillis() + "@test.com";
        signup.enterSignupDetails("Farida", email);
        signup.clickSignup();

        // ---------- 3. Account Creation ----------
        AccountCreationPage account = new AccountCreationPage(browser);
        Assert.assertTrue(account.isAccountInfoVisible(), "Account info header not visible");
        account.fillAccountInfo("Test@123");
        account.fillAddressInfo();
        account.clickCreateAccount();

        // ---------- 4. Account Created ----------
        AccountCreatedPage created = new AccountCreatedPage(browser);
        Assert.assertTrue(created.isAccountCreatedVisible(), "Account Created message not visible");
        created.clickContinue();

        // Verify Logged in
        Assert.assertTrue(home.isLoggedInAsVisible(), "Logged in as user not visible");

        // ---------- 5. Add Products ----------
        home.clickProducts();
        ProductsPage products = new ProductsPage(browser);
        Assert.assertTrue(products.isAllProductsVisible(), "All Products header not visible");
        products.addProductToCart(1);      // Add first product
        products.clickContinueShopping();
        products.addProductToCart(2);      // Add second product
        products.clickViewCart();

        // ---------- 6. Cart ----------
        CartPage cart = new CartPage(browser);
        Assert.assertTrue(cart.isCartVisible(), "Cart page not visible");
        Assert.assertTrue(cart.getNumberOfProducts() >= 2, "Less than 2 products in cart");
        cart.clickProceedToCheckout();

        // ---------- 7. Checkout ----------
        CheckoutPage checkout = new CheckoutPage(browser);
        Assert.assertTrue(checkout.isAddressDetailsVisible(), "Address Details not visible");
        Assert.assertTrue(checkout.isReviewOrderVisible(), "Review Your Order not visible");
        checkout.enterOrderComment("Please deliver ASAP");
        checkout.clickPlaceOrder();

        // ---------- 8. Payment ----------
        PaymentPage payment = new PaymentPage(browser);
        payment.enterPaymentInfo("Farida Waheed",
                "4111111111111111", "123", "12", "2028");
        payment.clickPayAndConfirm();
        Assert.assertTrue(payment.isOrderSuccessVisible(), "Order success message not visible");
        // 19. Click 'Delete Account' button
home.clickDeleteAccount();

// 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
Assert.assertTrue(home.isAccountDeletedVisible(), "Account Deleted message not visible");
home.clickContinue();
    }
}
