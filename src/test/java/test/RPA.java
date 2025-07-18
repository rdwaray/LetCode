package test;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driverFactory;

public class RPA {
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
    public void setUp(){
        driver.manage().deleteAllCookies();
        driver.get("https://letcode.in/forms");
    }

    }

