package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderLoginBeforeCheckoutTest extends BaseTest {

    @Test(description = "Test Case 16: Place Order: Login before Checkout")
    public void placeOrderLoginBeforeCheckout() {

        // -------- Page Object Setup --------
        HomePage home = new HomePage(browser);
        SignupLoginPage loginPage = new SignupLoginPage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart = new CartPage(browser);
        CheckoutPage checkout = new CheckoutPage(browser);
        PaymentPage payment = new PaymentPage(browser);

        // ---------- Step 3: Verify home page ----------
        Assert.assertTrue(home.isHomePageVisible(), "Home page not visible!");

        // ---------- Step 4: Click 'Signup / Login' ----------
        home.clickSignupLogin();

        // ---------- Step 5: Fill login credentials ----------
        String email = "f.@mail.com";     // <-- replace with valid user email
        String password = "Pass1234";              // <-- replace with valid password
        loginPage.enterLoginDetails(email, password);
        loginPage.clickLogin();

        // ---------- Step 6: Verify 'Logged in as username' ----------
        Assert.assertTrue(home.isLoggedInAsVisible(), "Logged in username not visible!");

        // ---------- Step 7: Add products to cart ----------
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "All Products page not visible!");
        products.addProductToCart(1);   // Add first product
        products.clickContinueShopping();
        products.addProductToCart(2);   // Add second product
        products.clickViewCart();

        // ---------- Step 9: Verify cart page ----------
        Assert.assertTrue(cart.isCartVisible(), "Cart page is not displayed!");

        // ---------- Step 10: Click Proceed To Checkout ----------
        cart.clickProceedToCheckout();

        // ---------- Step 11: Verify Address Details & Review Order ----------
        Assert.assertTrue(checkout.isAddressDetailsVisible(), "Address Details not visible!");
        Assert.assertTrue(checkout.isReviewOrderVisible(), "Review Your Order not visible!");

        // ---------- Step 12: Enter description & Place Order ----------
        checkout.enterOrderComment("Please deliver between 9AM - 5PM.");
        checkout.clickPlaceOrder();

        // ---------- Step 13: Enter payment details ----------
        payment.enterPaymentInfo(
                "Farida Waheed",
                "4111111111111111",  // Test VISA number
                "123",
                "12",
                "2028"
        );
        payment.clickPayAndConfirm();

        // ---------- Final Verification ----------
        Assert.assertTrue(payment.isOrderSuccessVisible(), "Order success message not visible!");
        
        // 19. Click 'Delete Account' button
home.clickDeleteAccount();

// 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
Assert.assertTrue(home.isAccountDeletedVisible(), "Account Deleted message not visible");
home.clickContinue();
    }
}
