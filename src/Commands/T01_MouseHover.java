package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T01_MouseHover {

    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
     System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
     driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    driver.get("http://the-internet.herokuapp.com/hovers");

    }

    @Test(priority = 1)
    public void test() throws InterruptedException {
    WebElement FirstUserAvatar = driver.findElements(By.cssSelector("img[alt=\"User Avatar\"]")).get(0);
    Actions action = new Actions(driver);       // you should import org.openqa.selenium.interactions.Actions;

    //Performing the mouse hover action on the target element.
    action.moveToElement(FirstUserAvatar).perform();

    Thread.sleep(2000);
    driver.findElement(By.cssSelector("a[href=\"/users/1\"]")).click();

    // Note: hover element is not enough to open this link http://the-internet.herokuapp.com/users/1
    // that's why you should click after hover the element
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}