package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Test(description = "Add products to cart and verify")
    public void addProductsToCart() throws InterruptedException {
        homePage = new HomePage(browser);
        productsPage = new ProductsPage(browser);
        cartPage = new CartPage(browser);

        // 3. Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 4. Click 'Products' button
        homePage.clickProducts();

        // 5. Add first product
        productsPage.addProductsToCart(1);

        // 6. Click 'Continue Shopping'
        productsPage.clickContinueShopping();

        // 7. Add second product
        productsPage.addProductsToCart(2);

        // 8. Click 'View Cart'
        productsPage.clickViewCart();

        // 9. Verify both products are added dynamically
        int productCount = cartPage.getNumberOfProduct();
        productCount --;
        Assert.assertEquals(productCount, 2, "Cart does not contain 2 products");
        
        // 10. Print product details
        for (int i = 0; i < productCount; i++) {
            System.out.println("Product " + (i + 1) + ": " + cartPage.getProductName(i));
            System.out.println("Price: " + cartPage.getProductPrice(i));
            System.out.println("Quantity: " + cartPage.getProductQuantities(i));
            System.out.println("Total: " + cartPage.getTotalPrice(i));
        }
    }
}