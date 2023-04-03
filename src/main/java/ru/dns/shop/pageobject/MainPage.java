package ru.dns.shop.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static final String MAIN_URL = "https://www.dns-shop.ru/";
    private static final String SMARTPHONE_URL = "https://www.dns-shop.ru/catalog/17a8a01d16404e77/smartfony/";

    // Раздел "Смартфоны и фототехника"
    private final By smartAndPhoneChapter = By.cssSelector("div.catalog-menu__root-item:nth-child(3)");  // div.catalog-menu__root-item:nth-child(3) > a:nth-child(1)

    //Кнопка "Смартфоны"
    private final By smartphoneButton = By.xpath("//span[contains(text(),'Смартфоны')]");

    // Драйвер браузера
    private final RemoteWebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public MainPage(RemoteWebDriver driver, Actions actions, WebDriverWait wait) {
        this.driver = driver;
        this.actions = actions;
        this.wait = wait;
    }

    public MainPage open() {
        driver.get(MAIN_URL);
        return this;
    }

    public MainPage clickSmartphoneButton() {
        driver.findElement(smartphoneButton).click();
        return this;
    }


    public boolean isOnSmartphonePage() {
        wait.until(ExpectedConditions.urlMatches(SMARTPHONE_URL));
        return true;
    }

    public MainPage hoverOnSmartAndPhoto() {
        var apps = driver.findElement(smartAndPhoneChapter);
        actions.moveToElement(apps).perform();
        return this;
    }
}
