package ru.dns.shop.config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class DriverConfig {
    public static ChromeOptions CHROME_OPTION;

    public static final String CHROME = "chrome";

    public static final String DRIVER_PATH = "webdriver.chrome.driver";
    // for local usage
    public static final String CHROME_DRIVER_PATH = "src/test/resources/chromedriver";


    static {
        CHROME_OPTION = new ChromeOptions();
        // CHROME_OPTION.addArguments("--headless");
    }

    public static DriverManager getDriverManger() {
        var driver = getDriver(CHROME);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new DriverManager(driver);
    }

    public static RemoteWebDriver getDriver(String browser) {
        if (CHROME.equalsIgnoreCase(browser)) {
            if (!isWebDriverPropertySet()) {
                System.setProperty(DRIVER_PATH, CHROME_DRIVER_PATH);
            }
            return new ChromeDriver(CHROME_OPTION);
        } else {
            throw new IllegalArgumentException("Browser not found: " + browser);
        }
    }

    private static boolean isWebDriverPropertySet() {
        return System.getProperty(DRIVER_PATH) != null;
    }

    public static class DriverManager {

        public DriverManager(RemoteWebDriver driver) {
            this.driver = driver;
            this.actions = new Actions(driver);
            this.wait = new WebDriverWait(driver,10);
        }

        private final RemoteWebDriver driver;
        private final Actions actions;
        private final WebDriverWait wait;

        public RemoteWebDriver getDriver() {
            return driver;
        }

        public Actions getActions() {
            return actions;
        }

        public WebDriverWait getWait() {
            return wait;
        }

        public JavascriptExecutor getJsExecutor() {
            return driver;
        }

        public void toUrl(String url) {
            driver.navigate().to(url);
        }

    }


}
