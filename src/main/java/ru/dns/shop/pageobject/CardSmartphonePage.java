package ru.dns.shop.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardSmartphonePage {

    //Кнопка "Развернуть все"
    private final By expandAllButton = By.xpath("//button[contains(@class, 'button-ui button-ui_white product-characteristics__expand')]");

    //Поле "Samsung" в Характеристике товара
    private final String brandFieldPattern = "//div[@class='product-characteristics__spec-value' and contains(text(),'{}')]";

    private final String paramPattern = "{}";

    //Поле "256" в Характеристике товара
    private final By ramField = By.xpath("//div[@class='product-characteristics__spec-value' and contains(text(),'256')]");

    //Поле "4 ГБ" в Характеристике товара
    private final By InternalMemoryField = By.xpath("//div[@class='product-characteristics__spec product-characteristics__ovh' and contains(text(),'4')]");

    // Драйвер браузера
    private final RemoteWebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public CardSmartphonePage(RemoteWebDriver driver, Actions actions, WebDriverWait wait) {
        this.driver = driver;
        this.actions = actions;
        this.wait = wait;
    }

    public CardSmartphonePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver,30);
    }

    private boolean scrollAndCheckIsPresent(By by, boolean down) {
        var pointsToScroll = down ? 1000 : -1000;
        var totalScrolls = 10;
        var currentScrolls = 0;
        while (totalScrolls > currentScrolls && driver.findElements(by).isEmpty()) {
            ((JavascriptExecutor) driver).executeScript(String.format("window.scrollBy(0, %s)", pointsToScroll));
            currentScrolls++;
        }
        return totalScrolls != currentScrolls;
    }

    public CardSmartphonePage clickExpandAllButton() {
        driver.findElement(expandAllButton).click();
        return this;
    }

    public boolean isInBrand(String expectedBrand) {
        wait.until(dr -> {
            var packageText = dr.findElement(By.xpath(brandFieldPattern.replace(paramPattern, expectedBrand))).getText();
            return packageText.toLowerCase().startsWith(expectedBrand);
        });
        return true;
    }

    public boolean isInRam(String expectedRam) {
        wait.until(dr -> {
            var packageText = dr.findElement(ramField).getText();
            return expectedRam.equals(packageText.toLowerCase().trim());
        });
        return true;
    }

    public boolean isInInternalMemory(String expectedRam) {
        wait.until(dr -> {
            var packageText = dr.findElement(InternalMemoryField).getText();
            return expectedRam.equals(packageText.toLowerCase().trim());
        });
        return true;
    }

    public CardSmartphonePage moveToExpandAllButton() {
        scrollAndCheckIsPresent(expandAllButton, true);
        var section = driver.findElement(expandAllButton);
        actions.moveToElement(section).perform();
        return this;
    }

}

