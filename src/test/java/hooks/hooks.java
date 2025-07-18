package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import utils.driverFactory;
import io.cucumber.java.Scenario;

import java.io.PrintWriter;

public class hooks {
    private WebDriver driver;
    private PrintWriter log;

    @Before
    public void setup(Scenario scenario){
        driverFactory.getDriver();
        log = driverFactory.getLog();

        String featureName = scenario.getUri().toString().replace("classpath:features/", "");
        log.println("Feature: " + featureName);
        log.println("Scenario: " + scenario.getName());
        log.flush();
    }

    @After
    public void clearCookies(){
        WebDriver driver = driverFactory.getDriver();
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    @AfterClass
    public static void tearDown(){
        driverFactory.quitDriver();
    }
}
