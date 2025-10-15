package FrameWork;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Farida Waheed
 */
public class Selenium_FrameWork {

    private WebDriver browser;
    private WebDriverWait explicitWait;
    private final int DEFAULT_TIMEOUT = 10;
    
    public void initializeBrowser() 
    {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        explicitWait = new WebDriverWait(browser,Duration.ofSeconds(DEFAULT_TIMEOUT));
        System.out.println("Farida: Browser Initialized.");
    }
    
    public WebDriver getBrowser() 
    {
        System.out.println("Farida: Browser " + browser);
        return browser;
    }
    
    public void implicitWait(int seconds)
    {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("Farida: Set Implicit Wait to " + seconds + " seconds.");
    }
    
    public void explicitWait(By locator, int timeoutSeconds) 
    {
        new WebDriverWait(browser, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println("Farida: Explicit wait for presence of " + locator);
    }
    
    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) 
    {
        Wait<WebDriver> fluentWait =new FluentWait<>(browser)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .withMessage(timeoutMessage)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        System.out.println("Farida: Fluent wait found element " + locator);
    }
    
    public void navigateToURL(String url) 
    {
        browser.get(url);
        System.out.println("Farida: Navigated to URL: " + url);
    }
    
    public String getPageTitle() 
    {
        String Title = browser.getTitle();
        System.out.println("Farida: Page title is '" + Title + "'");
        return Title;
    }
    
    public String getCurrentURL()
    {
        String CurrentURL = browser.getCurrentUrl();
        System.out.println("Farida: Current URL is '" + CurrentURL + "'");
        return CurrentURL;
    }
    
    public void click(By locator) 
    {
        explicitWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("Farida: Clicked element " + locator);
    }
    
    public void rightClick(By locator)
    {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions action = new Actions(browser);
        action.contextClick(element).perform();
        System.out.println("Farida: Right-clicked on element " + locator);
    }
    
    public void sendKeys(By locator, String text) 
    {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
        System.out.println("Farida: Sent keys to element " + locator);
    }
    
    public String getText(By locator) 
    {
        String Text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        System.out.println("Farida: Got text from element " + locator + ": " + Text);
        return Text;
    }
    
    public void selectDropdownByVisibleText(By locator, String visibleText) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        System.out.println("Farida: Selected dropdown value by visible text: " + visibleText);
    }
    
    public void selectDropdownByValue(By locator, String value) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByValue(value);
        System.out.println("Farida: Selected dropdown value by value: " + value);
    }
    
    public void selectDropdownByIndex(By locator, int index) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByIndex(index);
        System.out.println("Farida: Selected dropdown by index: " + index);
    }
    
    public void dragAndDrop(By sourceLocator, By targetLocator) 
    {
        WebElement element1 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement element2 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        Actions action = new Actions(browser);
        action.dragAndDrop(element1, element2).perform();
        System.out.println("Farida: Dragged element " + sourceLocator + " and dropped on " + targetLocator);
    }
    
    public void checkCheckbox(By locator) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!element.isSelected()) {
            element.click();
            System.out.println("Farida: Checked the checkbox " + locator);
        } else {
            System.out.println("Farida: Checkbox already checked " + locator);
        }
    }
    
    public void uncheckCheckbox(By locator) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (element.isSelected()) {
            element.click();
            System.out.println("Farida: Unchecked the checkbox " + locator);
        } else {
            System.out.println("Farida: Checkbox already unchecked " + locator);
        }
    }
    
    public void selectRadioButton(By locator)
    {
        WebElement element = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!element.isSelected()) {
            element.click();
            System.out.println("Farida: Selected radio button " + locator);
        } else {
            System.out.println("Farida: Radio button already selected " + locator);
        }
    }
    
    public void switchToWindowByTitle(String windowTitle)
    {
        String currentWindow = browser.getWindowHandle();
        Set<String> allWindows = browser.getWindowHandles();

        for (String window : allWindows) {
            browser.switchTo().window(window);
            if (browser.getTitle().equals(windowTitle)) {
                System.out.println("Farida: Switched to window with title: " + windowTitle);
                return;
            }
        }

        browser.switchTo().window(currentWindow);
        System.out.println("Farida: Window with title '" + windowTitle + "' not found. Stayed in original window.");
    }
    
    public void switchToWindowByHandle(String windowHandle) 
    {
        Set<String> allWindows = browser.getWindowHandles();
        if (allWindows.contains(windowHandle)) {
            browser.switchTo().window(windowHandle);
            System.out.println("Farida: Switched to window handle: " + windowHandle);
        } else {
            System.out.println("Farida: Window handle " + windowHandle + " does not exist. No switch performed.");
        }
    }
    
    public void closeCurrentWindow() 
    {
        browser.close();
        System.out.println("Farida: Closed current window.");
    }
    
    public void navigateBack() 
    {
        browser.navigate().back();
        System.out.println("Farida: Navigated back.");
    }
    
    public void navigateForward() 
    {
        browser.navigate().forward();
        System.out.println("Farida: Navigated forward.");
    }
    
    public void refreshPage() 
    {
        browser.navigate().refresh();
        System.out.println("Farida: Page refreshed.");
    }
    
    public void scrollToElement(By locator) 
    {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions action = new Actions(browser);
        action.scrollToElement(element).perform();
        System.out.println("Farida: Scrolled to element " + locator + " using Actions.scrollToElement().");
    }
    
    public void acceptAlert()
    {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("Farida: Alert accepted.");
    }
    
    public void dismissAlert() 
    {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        System.out.println("Farida: Alert dismissed.");
    }
    
    public String getAlertText() 
    {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String Text = alert.getText();
        System.out.println("Farida: Alert text: " + Text);
        return Text;
    }
    
    public void sendTextToAlert(String text) 
    {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        System.out.println("Farida: Sent text to alert and accepted it: " + text);
    }
    
    public void closeBrowser()
    {
        if (browser != null)
        {
            browser.quit();
            System.out.println("Farida: Browser Closed.");
        }
    }
}
