package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.ProductsPage;

public class ProductsPageTest extends BaseTest {

    private HomePage homePage;
    private ProductsPage productsPage;

    @Test(description = "Launch browser and verify home page")
    public void openHomePage() {
        homePage = new HomePage(browser);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
    }

    @Test(dependsOnMethods = {"openHomePage"}, description = "Navigate to Products page")
    public void navigateToProductsPage() {
        homePage.clickProducts();
        productsPage = new ProductsPage(browser);
        Assert.assertTrue(productsPage.isAllProductsVisible(), "All Products page not visible");
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");
    }

    @Test(dependsOnMethods = {"navigateToProductsPage"}, description = "Click View Product of first product and verify details")
    public void verifyFirstProductDetails() {
        productsPage.clickFirstProductView();
        Assert.assertTrue(productsPage.isProductDetailVisible(), "Product detail page is not fully visible");
    }
}
