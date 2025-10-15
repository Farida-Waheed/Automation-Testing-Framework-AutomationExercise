package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DownloadInvoiceTest extends BaseTest {

    @Test
    public void downloadInvoiceAfterPurchase() {
        HomePage home = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart = new CartPage(browser);
        CheckoutPage checkout = new CheckoutPage(browser);
        SignupLoginPage signup = new SignupLoginPage(browser);
        AccountCreationPage accountCreation = new AccountCreationPage(browser);
        AccountCreatedPage accountCreated = new AccountCreatedPage(browser);
        PaymentPage payment = new PaymentPage(browser);
        InvoicePage invoice = new InvoicePage(browser); // 🔹 new helper page

        // Step 3: Verify home page
        Assert.assertTrue(home.isHomePageVisible(), "Home page not visible!");

        // Step 4: Add products to cart
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "Products page not visible!");
        products.addProductToCart(1);
        products.clickContinueShopping();
        products.addProductToCart(2);

        // Step 5: Click 'Cart' button
        home.clickCart();

        // Step 6: Verify cart page
        Assert.assertTrue(cart.getNumberOfProducts() > 0, "Cart page empty!");

        // Step 7: Proceed to checkout
        cart.clickProceedToCheckout();

        // Step 8: Click Register/Login
        checkout.clickRegisterLogin();
        Assert.assertTrue(signup.isNewUserVisible(), "Signup form not visible!");

        // Step 9: Fill signup details
        String email = "farida" + System.currentTimeMillis() + "@test.com";
        signup.enterSignupDetails("Farida", email);
        signup.clickSignup();

        accountCreation.fillAccountInfo("password123");
        accountCreation.fillAddressInfo();
        accountCreation.clickCreateAccount();

        // Step 10: Verify account created
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account created msg missing!");
        accountCreated.clickContinue();

        // Step 11: Verify logged in
        Assert.assertTrue(home.isLoggedInAsVisible(), "Logged in username missing!");

        // Step 12: Go back to Cart
        home.clickCart();

        // Step 13: Proceed to checkout again
        cart.clickProceedToCheckout();

        // Step 14: Verify address & review sections
        Assert.assertTrue(checkout.isAddressDetailsVisible(), "Address details missing!");
        Assert.assertTrue(checkout.isReviewOrderVisible(), "Review order missing!");

        // Step 15: Enter comment and place order
        checkout.enterOrderComment("Please deliver ASAP");
        checkout.clickPlaceOrder();

        // Step 16: Enter payment details
        payment.enterPaymentInfo("Farida Waheed", "4111111111111111", "123", "12", "2026");

        // Step 17: Pay and confirm
        payment.clickPayAndConfirm();

        // Step 18: Verify order success
        Assert.assertTrue(payment.isOrderSuccessVisible(), "Order success message not visible!");

        // Step 19: Download invoice
        invoice.clickDownloadInvoice();
        Assert.assertTrue(invoice.isInvoiceDownloaded(), "Invoice not downloaded!");

        // Step 20: Continue
        invoice.clickContinue();

        // Step 21: Delete account
        home.clickDeleteAccount();

        // Step 22: Verify deleted
        Assert.assertTrue(home.isAccountDeletedVisible(), "Account deleted message not visible!");
        home.clickContinue();
    }
}
