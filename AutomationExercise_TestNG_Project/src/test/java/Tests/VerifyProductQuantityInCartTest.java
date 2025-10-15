package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import Pages.CartPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyProductQuantityInCartTest extends BaseTest {

    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Test(description = "Test Case 13: Verify Product quantity in Cart")
    public void verifyProductQuantityInCart() throws InterruptedException {

        // Step 3: Verify home page is visible successfully
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(),
                "Home page is not visible!");

        // Step 4: Click 'View Product' for any product on home page
        productsPage = new ProductsPage(browser);
        productsPage.clickFirstProductView();  

        // Step 5: Verify product detail is opened
        Assert.assertTrue(productsPage.isProductDetailVisible(),
                "Product detail page is NOT visible!");

        // Step 6: Increase quantity to 4
        productsPage.setQuantity("4");

        // Step 7: Click 'Add to cart' button
        productsPage.clickAddToCartOnDetail();
        Thread.sleep(2000); // allow modal to appear

        // Step 8: Click 'View Cart' button
        productsPage.clickViewCart();
        
        cartPage = new CartPage(browser);


        // Step 9: Verify quantity using CartPage method
        String quantity = cartPage.getProductQuantity();
Assert.assertEquals(quantity, "4", "Cart quantity is not as expected!");
    }
}
