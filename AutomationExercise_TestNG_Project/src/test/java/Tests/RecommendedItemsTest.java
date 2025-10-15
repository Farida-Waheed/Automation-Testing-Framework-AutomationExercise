package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecommendedItemsTest extends BaseTest {

    @Test
    public void addRecommendedItemToCart() {
        HomePage home = new HomePage(browser);
        CartPage cart = new CartPage(browser);

        // Step 3: Scroll to bottom
        home.scrollToFooter();

        // Step 4: Verify recommended items section
        Assert.assertTrue(home.isRecommendedItemsVisible(), 
            "'RECOMMENDED ITEMS' section is not visible!");

        // Step 5: Add to cart from recommended
        home.clickAddToCartOnRecommended();

        // Step 6: Click 'View Cart'
        home.clickViewCartFromRecommended();

        // Step 7: Verify product is displayed in cart
        Assert.assertTrue(cart.getNumberOfProducts() > 0, 
            "No product found in the cart after adding from Recommended Items!");
    }
}
