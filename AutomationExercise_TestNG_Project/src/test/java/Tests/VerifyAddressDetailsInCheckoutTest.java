package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAddressDetailsInCheckoutTest extends BaseTest {

    @Test
    public void verifyAddressDetails() {
        HomePage home = new HomePage(browser);
        SignupLoginPage signup = new SignupLoginPage(browser);
        AccountCreationPage accountCreation = new AccountCreationPage(browser);
        AccountCreatedPage accountCreated = new AccountCreatedPage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart = new CartPage(browser);
        CheckoutPage checkout = new CheckoutPage(browser);

        // Step 3: Verify home page
        Assert.assertTrue(home.isHomePageVisible(), "Home page not visible!");

        // Step 4: Click 'Signup / Login'
        home.clickSignupLogin();
        Assert.assertTrue(signup.isNewUserVisible(), "Signup form not visible!");

        // Step 5: Fill details for Signup
        String email = "farida" + System.currentTimeMillis() + "@test.com";
        signup.enterSignupDetails("Farida", email);
        signup.clickSignup();

        accountCreation.fillAccountInfo("password123");
        accountCreation.fillAddressInfo();
        accountCreation.clickCreateAccount();

        // Step 6: Verify 'ACCOUNT CREATED!' and click Continue
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account created message missing!");
        accountCreated.clickContinue();

        // Step 7: Verify 'Logged in as'
        Assert.assertTrue(home.isLoggedInAsVisible(), "Logged in username not visible!");

        // Step 8: Add products to cart
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "Products page not visible!");
        products.addProductToCart(1);
        products.clickContinueShopping();
        products.addProductToCart(2);
        products.clickViewCart();

        // Step 9–10: Verify cart page displayed
        Assert.assertTrue(cart.getNumberOfProducts() > 0, "No products in cart!");

        // Step 11: Click Proceed to Checkout
        cart.clickProceedToCheckout();
        Assert.assertTrue(checkout.isAddressDetailsVisible(), "Address details not visible!");

        // Step 12–13: Verify Delivery & Billing address = registration info
        String deliveryAddress = checkout.getDeliveryAddressText();
        String billingAddress = checkout.getBillingAddressText();

        Assert.assertTrue(deliveryAddress.contains("Farida") && deliveryAddress.contains("Toronto"),
                "Delivery address incorrect! Found: " + deliveryAddress);

        Assert.assertTrue(billingAddress.contains("Farida") && billingAddress.contains("Toronto"),
                "Billing address incorrect! Found: " + billingAddress);

        // Step 14: Delete account
        home.clickDeleteAccount();

        // Step 15: Verify 'ACCOUNT DELETED!' and click Continue
        Assert.assertTrue(home.isAccountDeletedVisible(), "Account Deleted message not visible!");
        home.clickContinue();
    }
}
