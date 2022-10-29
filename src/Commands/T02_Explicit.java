package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class T02_Explicit {

    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
     System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
     driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    driver.get("https://demo.nopcommerce.com/");

    }

    @Test(priority = 1)
    public void test() throws InterruptedException {
    WebElement AddToCart = driver.findElements(By.cssSelector("button[class=\"button-2 product-box-add-to-cart-button\"]")).get(2);
    AddToCart.click();

    WebElement successNotification = driver.findElement(By.cssSelector("div[class=\"bar-notification success\"] span[title=\"Close\"]"));
        successNotification.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement shoppingCart = driver.findElement(By.cssSelector("a[href=\"/cart\"][class=\"ico-cart\"]"));
    wait.until(ExpectedConditions.elementToBeClickable(shoppingCart)).click();

// If you have not use explicit wait and click directly on shoppingCart, you will get below error
// org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <a href="/cart" class="ico-cart">...</a> is not clickable at point (1047, 22). Other element would receive the click: <p class="content">...</p>
// that's because successNotification (green line) block the vision on shoppingCart tab and this successNotification takes around 2 seconds or more to disappear
// so you use explicit wait to tell the driver to wait until shoppingCart to be clickable which means to wait until successNotification it disappears

    Thread.sleep(2000);
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}