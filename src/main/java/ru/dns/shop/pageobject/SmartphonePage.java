package ru.dns.shop.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmartphonePage {

    private static final String FILTER_URL_PATTERN = "www.dns-shop.ru/catalog/.*/smartfony/?.*brand={}.*";

    private static final String SORT_URL_PATTERN = "www.dns-shop.ru/catalog/.*/smartfony/?.*order=2&brand={}.*";

    private static final String URL_PARAM = "{}";

    private static final String SMARTPHONE_URL = "https://www.dns-shop.ru/product/.*";


    private static By getXpath(String xpathPattern, String... params) {
        return By.xpath(String.format(xpathPattern, (Object[]) params));
    }

    //Раздел "Объем встроенной памяти"
  private final By ramSection = By.xpath("//div[@data-id='f[9a9]']");  // //span[contains(text(),'Объем встроенной памяти (ГБ)')]

    // Раздел "Объем оперативной памяти"
    private final By internalMemorySection = By.xpath("//div[@data-id='f[9a8]']");

    //Кнопка "Применить"
    private final By applyButton = By.cssSelector(".button-ui.button-ui_brand.left-filters__button");
  //  private final By applyButton = By.xpath("//button[@data-role='filters-submit' and not(@disabled)]");

    //Сортировка (по умолчанию: "Сначала недорогие")
    private final By sortRadiobutton = By.xpath("//span[contains(text(),'Сначала недорогие')]");

    //Сортировка "Сначала дорогие"
    private final By ascendingRadiobutton = By.xpath("//span[starts-with(text(),'Сначала дорогие')]");

    //Первая карточка товара после сортировки
    private final By firstPhoneCard = By.xpath("//div[@class='catalog-products view-simple'][1]/div[1]/a");

    // Драйвер браузера
    private final RemoteWebDriver driver;

    private final WebDriverWait wait;

    private final Actions actions;

    public SmartphonePage(RemoteWebDriver driver, Actions actions, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }


    /**
     * Scroll till element in view
     *
     * @param by   element to find
     * @param down scroll down if TRUE, scroll up if FALSE
     * @return TRUE if element is present, FALSE otherwise
     */
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

    public SmartphonePage clickBrandCheckbox(String brand) {
        var brandCheckbox = getXpath("//*[@value='%s']/..", brand);
        driver.findElement(brandCheckbox).click();
        return this;
    }

    public SmartphonePage clickRam(String ramValue) {
        var ram = getXpath("//*[@value='%s']/..", ramValue);
        scrollAndCheckIsPresent(ram, true);
        driver.findElement(ram).click();
        return this;
    }

    public SmartphonePage clickInternalMemory(String ramValue) {
        var ram = getXpath("//*[@value='%s']/..", ramValue);
        scrollAndCheckIsPresent(ram, true);
        driver.findElement(ram).click();
        return this;
    }

    public SmartphonePage clickRamCheckbox(String ramValue) {
        var ramCheckbox = getXpath("//*[@value='%s']/..", ramValue);
        scrollAndCheckIsPresent(ramCheckbox, true);
        driver.findElement(ramCheckbox).click();
        return this;
    }

    public SmartphonePage clickApplyButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(applyButton));
        driver.findElement(applyButton).click();
        return this;
    }

    public SmartphonePage clickSortRadiobutton() {
        driver.findElement(sortRadiobutton).click();
        return this;
    }

    public SmartphonePage clickAscendingRadiobutton() {
        driver.findElement(ascendingRadiobutton).click();
        return this;
    }

    public SmartphonePage clickFirstPhoneCard() {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstPhoneCard));
        driver.findElement(firstPhoneCard).click();
        return this;
    }

    public SmartphonePage moveToBrand(String brand) {
        var brandCheckbox = getXpath("//*[@value='%s']", brand);
        scrollAndCheckIsPresent(brandCheckbox, true);

        var brandElement = driver.findElement(brandCheckbox);
        actions.moveToElement(brandElement).perform();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandElement);
        return this;
    }


    public SmartphonePage moveToRam(String ramValue) {
        var ram = getXpath("//*[@value='%s']", ramValue);
        scrollAndCheckIsPresent(ram, true);
        var ramElement = driver.findElement(ram);
        actions.moveToElement(ramElement).perform();
//        wait.until(ExpectedConditions.visibilityOf(ramElement));

        return this;
    }

    public SmartphonePage moveToInternalMemory(String ramValue) {
        var ram = getXpath("//*[@value='%s']", ramValue);
        scrollAndCheckIsPresent(ram, true);
        var ramElement = driver.findElement(ram);
        actions.moveToElement(ramElement).perform();
//        wait.until(ExpectedConditions.visibilityOf(ramElement));

        return this;
    }

    public SmartphonePage moveToRamSection() {
        scrollAndCheckIsPresent(ramSection, true);
        var section = driver.findElement(ramSection);
        actions.moveToElement(section).perform();
        return this;
    }

    public SmartphonePage moveToInternalMemorySection() {
        scrollAndCheckIsPresent(internalMemorySection, true);
        var section = driver.findElement(internalMemorySection);
        actions.moveToElement(section).perform();
        return this;
    }

    public SmartphonePage moveToSortRadiobutton() {
        scrollAndCheckIsPresent(sortRadiobutton, false);
        var section = driver.findElement(sortRadiobutton);
        actions.moveToElement(section).perform();
        return this;
    }

    public SmartphonePage clickRamSection() {
        driver.findElement(ramSection).click();
        return this;
    }

    public SmartphonePage clickInternalMemorySection() {
        driver.findElement(internalMemorySection).click();
        return this;
    }

    public SmartphonePage moveToApplyButton() {
        var apps = driver.findElement(applyButton);
        actions.moveToElement(apps).perform();
        return this;
    }

    public boolean isOnFilterPage(String param) {
        wait.until(ExpectedConditions.urlMatches(FILTER_URL_PATTERN.replace(URL_PARAM, param)));
        return true;
    }

    public boolean isOnSortPage(String param) {
        wait.until(ExpectedConditions.urlMatches(SORT_URL_PATTERN.replace(URL_PARAM, param)));
        return true;
    }

    public boolean isOnSmartphonePage() {
        wait.until(ExpectedConditions.urlMatches(SMARTPHONE_URL));
        return true;
    }
}


