package Tests;

import Base.BaseTest;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollUpDownTest extends BaseTest {

    @Test
    public void verifyScrollUpAndDownFunctionality() {
        HomePage home = new HomePage(browser);

        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(home.isHomePageVisible(), "Home page not visible!");

        // Step 4: Scroll down page to bottom
        home.scrollToFooter();

        // Step 5: Verify 'SUBSCRIPTION' is visible
        Assert.assertTrue(home.isSubscriptionHeaderVisible(), "Subscription section not visible at bottom!");

        // Step 6: Click on arrow at bottom right side to move upward
        home.clickScrollUpArrow();

        // Step 7: Verify that page is scrolled up and header text is visible
        Assert.assertTrue(home.isHeroHeaderVisible(), 
                "Hero header text 'Full-Fledged practice website for Automation Engineers' not visible!");
    }
}
