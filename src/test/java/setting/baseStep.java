package setting;

import org.openqa.selenium.WebDriver;
import utils.driverFactory;

import java.io.PrintWriter;

public abstract class baseStep {
    protected WebDriver driver;
    protected PrintWriter log;

    public baseStep() {
        this.driver = driverFactory.getDriver();
        this.log = driverFactory.getLog();
    }
}