package ru.dns.shop.characteric;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.dns.shop.config.DriverConfig;

public class AbstractParentTest {
    protected static DriverConfig.DriverManager driverManager;

    @BeforeClass
    public static void setupParent() {
        driverManager = DriverConfig.getDriverManger();
    }



    @AfterClass
    public static void tearDown() {
        if (driverManager != null) {
            driverManager.getDriver().close();
            driverManager.getDriver().quit();
        }
    }
}
