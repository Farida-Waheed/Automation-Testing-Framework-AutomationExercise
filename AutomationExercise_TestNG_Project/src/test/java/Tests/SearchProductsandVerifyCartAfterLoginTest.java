package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductsandVerifyCartAfterLoginTest extends BaseTest {

    @Test(description = "TC20 – Search Products and Verify Cart After Login")
    public void searchProductsAndVerifyCartAfterLogin() {

        // ----- Page Object Setup -----
        HomePage home         = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart         = new CartPage(browser);
        SignupLoginPage login = new SignupLoginPage(browser);

        // 3. Click on 'Products' button
        home.clickProducts();

        // 4. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(products.isAllProductsVisible(),
                "All Products page not visible!");

        // 5. Enter product name in search input and click search button
        String productName = "dress";
        products.enterSearchQuery(productName);
        products.clickSearchButton();

        // 6. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(products.isSearchedProductsHeaderVisible(),
                "'SEARCHED PRODUCTS' header not visible!");

        // 7. Verify all the products related to search are visible
        Assert.assertFalse(products.getSearchedProductNames().isEmpty(),
                "No products found in search results!");

        // 8. Add those products to cart
        int productCount = products.getSearchedProductNames().size();
        for (int i = 1; i <= productCount; i++) {
            products.addProductToCart(i);
            products.clickContinueShopping();
        }

        // 9. Click 'Cart' button and verify that products are visible in cart
        home.clickCart();
        Assert.assertTrue(cart.getNumberOfProducts() >= productCount,
                "Products not visible in cart BEFORE login!");

        // 10. Click 'Signup / Login' button and submit login details
        home.clickSignupLogin();
        login.enterLoginDetails("f.@mail.com", "Pass1234"); // <-- replace with valid
        login.clickLogin();

        // 11. Again, go to Cart page
        cart.clickCart();

        // 12. Verify that those products are visible in cart after login as well
        Assert.assertTrue(cart.getNumberOfProducts() >= productCount,
                "Products not visible in cart AFTER login!");
    }
}
