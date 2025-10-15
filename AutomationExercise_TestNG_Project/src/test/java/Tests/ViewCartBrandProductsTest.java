package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.ProductsPage;

public class ViewCartBrandProductsTest extends BaseTest {

    @Test
    public void viewAndCartBrandProducts() {
        HomePage home = new HomePage(browser);
        ProductsPage products = new ProductsPage(browser);

        // 1–3: Launch browser & navigate handled by BaseTest, then click Products
        home.clickProducts();
        Assert.assertTrue(products.isAllProductsVisible(), "All Products page is not visible.");

        // 4: Verify that Brands are visible on left side bar
        Assert.assertTrue(products.isBrandsSidebarVisible(), "Brands sidebar is NOT visible.");

        // 5–6: Click first brand and verify page/products
        products.clickBrand(1);
        String firstHeader = products.getBrandHeaderText();
        Assert.assertTrue(firstHeader.toUpperCase().contains("BRAND"),
                "First brand header not displayed or incorrect.");
        Assert.assertTrue(products.isProductsListVisible(),
                "No products displayed for the first brand.");
        System.out.println("First brand header: " + firstHeader);

        // 7–8: Click second brand and verify page/products
        products.clickBrand(2);
        String secondHeader = products.getBrandHeaderText();
        Assert.assertTrue(secondHeader.toUpperCase().contains("BRAND"),
                "Second brand header not displayed or incorrect.");
        Assert.assertTrue(products.isProductsListVisible(),
                "No products displayed for the second brand.");
        System.out.println("Second brand header: " + secondHeader);
    }
}
