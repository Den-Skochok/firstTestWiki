import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class WikiSecond {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/denys/Desktop/testCursor/chromedriver");
        driver = new ChromeDriver();

    }


    @Test
    public void test() {

        String title = "Wikipedia, the free encyclopedia";
        String errorMessage = "Incorrect username or password entered. Please try again.";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://uk.wikipedia.org/");
        driver.findElement(By.xpath("//a[@href='https://en.wikipedia.org/wiki/']")).click();
        Assert.assertEquals(driver.getTitle(), title);
        //Go to Login page
        driver.findElement(By.xpath("//*[text()='Log in']")).click();
        //Login with wrong password
        driver.findElement(By.id("wpName1")).clear();
        driver.findElement(By.id("wpName1")).sendKeys("Testd Tests");
        driver.findElement(By.id("wpPassword1")).clear();
        driver.findElement(By.id("wpPassword1")).sendKeys("Y9Qaff=?a37?4S1L");
        driver.findElement(By.id("wpLoginAttempt")).click();
        //Check user wrong login
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='mw-message-box-error mw-message-box']")).getText(), errorMessage);

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
