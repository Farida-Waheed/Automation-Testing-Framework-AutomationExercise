package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddReviewTest extends BaseTest {

    @Test
    public void addReviewOnProduct() {
        HomePage home = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);
        ProductPage product = new ProductPage(browser);

        // Step 3: Click 'Products'
        home.clickProducts();

        // Step 4: Verify ALL PRODUCTS page
        Assert.assertTrue(products.isAllProductsVisible(), "All Products page not visible!");

        // Step 5: Click 'View Product'
        products.clickFirstProductView();
        
        // OPTIONAL: Scroll to review section
        product.scrollToReviewSection();

        // Step 6: Verify 'Write Your Review' is visible
        Assert.assertTrue(product.isWriteReviewVisible(), "'Write Your Review' section not visible!");

        // Step 7: Enter name, email, and review
        product.writeReview("Farida", "farida@example.com", "Great product! Highly recommended.");

        // Step 9: Verify success message
        Assert.assertTrue(product.isReviewSuccessVisible(), "Success message not visible!");
    }
}
