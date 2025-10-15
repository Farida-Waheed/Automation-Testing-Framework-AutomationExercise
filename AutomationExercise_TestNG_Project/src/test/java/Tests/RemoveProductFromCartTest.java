package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import Pages.CartPage;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveProductFromCartTest extends BaseTest {

    @Test
    public void removeProductFromCart() {
        HomePage home = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);
        CartPage cart = new CartPage(browser);

        // Step 3: Verify Home Page
        Assert.assertTrue(home.isHomePageVisible(), "Home page is NOT visible.");

        // Step 4: Add products to cart (adding first product for this test)
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "All Products page is NOT visible.");
        products.addProductToCart(1);
        products.clickContinueShopping();

        // Step 5: Click 'Cart' button
        cart.clickCart();

        // Step 6: Verify cart page is displayed
        Assert.assertTrue(cart.isCartVisible(), "Cart page is NOT visible.");
        int initialProductCount = cart.getNumberOfProducts();
        Assert.assertTrue(initialProductCount > 0, "No products found in cart.");

        // Step 7: Click 'X' button corresponding to particular product
        // ✅ We can directly locate the delete icon for the first product
        browser.findElement(By.cssSelector("a.cart_quantity_delete")).click();

        
        new WebDriverWait(browser, Duration.ofSeconds(10))
        .until(d -> cart.isCartEmptyMessageVisible());

Assert.assertTrue(cart.isCartEmptyMessageVisible(),
    "Expected 'Cart is empty!' message but it was not found.");


    }
}
