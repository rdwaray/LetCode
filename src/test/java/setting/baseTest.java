package setting;

import utils.driverFactory;

public class baseTest {
    public void setUp() {
        driverFactory.getDriver();
    }

    public void tearDown() {
        if (driverFactory.getDriver() != null) {
            driverFactory.quitDriver();
        }
    }
}
