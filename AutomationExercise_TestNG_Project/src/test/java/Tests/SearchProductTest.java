package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SearchProductTest extends BaseTest {

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
    }

    @Test(dependsOnMethods = {"navigateToProductsPage"}, description = "Search for a product and verify results")
    public void searchProduct() {
        String searchQuery = "Dress"; 
        productsPage.enterSearchQuery(searchQuery);
        productsPage.clickSearchButton();

        Assert.assertTrue(productsPage.isSearchedProductsHeaderVisible(),
                "'SEARCHED PRODUCTS' header is not visible");

        List<String> productNames = productsPage.getSearchedProductNames();
        Assert.assertFalse(productNames.isEmpty(), "No products displayed for search");

        // Filter only products that contain the search keyword
        List<String> matchingProducts = productNames.stream()
                .filter(name -> name.toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        Assert.assertFalse(matchingProducts.isEmpty(), "No matching products found for query: " + searchQuery);

        for (String name : matchingProducts) {
            System.out.println("Matching product: " + name);
        }
    }
}
