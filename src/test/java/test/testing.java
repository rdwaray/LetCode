package test;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UtilityElement;
import utils.driverFactory;

import java.util.List;

public class testing {
    private static WebDriver driver;
    private static Faker faker;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setupTest() {
        driver = driverFactory.getDriver();
        faker = driverFactory.getFaker();
        wait = driverFactory.getWait();
    }

    @Before
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.get("https://letcode.in/home");
    }

//    @Test
//    public void srapt() {
//        List<WebElement> products = driver.findElements(By.className("card"));
//
//        for (WebElement product : products) {
//            String title = product.findElement(By.cssSelector(".card-header-title")).getText();
//            String price = product.findElement(By.cssSelector(".card-footer-item.button.is-link.is-fullwidth")).getText();
//            System.out.println(title + " - " + price);
//
//        }
//    }
    
}
