package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewCategoryProductsTest extends BaseTest {

    @Test
    public void viewCategoryProducts() {

        // 1–2: Launch browser & navigate (BaseTest usually handles browser launch)
        HomePage home = new HomePage(browser);
        home.clickProducts();   // go to Products page

        // 3: Verify that categories are visible on left sidebar
        CategoryPage category = new CategoryPage(browser);
        Assert.assertTrue(category.isCategoriesSidebarVisible(), "Categories sidebar is NOT visible");

        // 4: Click on 'Women' category
        category.clickWomenCategory();

        // 5: Click on any category link under 'Women', e.g. Dress
        category.clickWomenDress();

        // 6: Verify category page header text
        String header = category.getCategoryHeaderText();
        Assert.assertTrue(header.contains("WOMEN"), "Header does not contain WOMEN");
        Assert.assertTrue(header.contains("PRODUCTS"), "Header does not contain PRODUCTS");
        System.out.println("Women category header: " + header);

        // 7: Expand 'Men' category, then click a sub-category
category.clickMenCategory();
category.clickMenTshirts();


        // 8: Verify user is navigated to that category page
        String menHeader = category.getCategoryHeaderText();
        Assert.assertTrue(menHeader.contains("MEN"), "Header does not contain MEN");
        Assert.assertTrue(menHeader.contains("PRODUCTS"), "Header does not contain PRODUCTS");
        System.out.println("Men category header: " + menHeader);
    }
}
