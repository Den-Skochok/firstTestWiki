import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class WikiFirst {

    WebDriver driver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/denys/Desktop/testCursor/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.manage().window().maximize();
        String title = "Wikipedia, the free encyclopedia";
        String logOut = "Log out";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://uk.wikipedia.org/");
        driver.findElement(By.xpath("//a[@href='https://en.wikipedia.org/wiki/']")).click();
        Assert.assertEquals(driver.getTitle(), title);
        //Go to Login page
        driver.findElement(By.xpath("//*[text()='Log in']")).click();
        //Login
        driver.findElement(By.id("wpName1")).clear();
        driver.findElement(By.id("wpName1")).sendKeys("Testd Tests");
        driver.findElement(By.id("wpPassword1")).clear();
        driver.findElement(By.id("wpPassword1")).sendKeys("Y9Qaff=?a37?4SL");
        driver.findElement(By.id("wpLoginAttempt")).click();
        //Check user login
        Assert.assertEquals(driver.findElement(By.xpath("//*[text()='Log out']")).getText(), logOut);
        Assert.assertEquals(driver.getTitle(), title);
        //Search
        driver.findElement(By.xpath("//*[@name='search']")).sendKeys("Ukraine");
        driver.findElement(By.id("searchButton")).click();
        //Check search result
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='firstHeading']//span")).getText(), "Ukraine");
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }
}
