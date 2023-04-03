package ru.dns.shop.characteric;

import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.dns.shop.pageobject.CardSmartphonePage;
import ru.dns.shop.pageobject.SmartphonePage;
import ru.dns.shop.pageobject.MainPage;

import static org.testng.AssertJUnit.assertTrue;

public class SmartphoneTest extends AbstractParentTest {
    private static MainPage mainPage;
    private static SmartphonePage smartphonePage;
    private static CardSmartphonePage cardSmartphonePage;


    @BeforeClass
    public static void setup() {
        mainPage = new MainPage(driverManager.getDriver(), driverManager.getActions(), driverManager.getWait());
        smartphonePage = new SmartphonePage(driverManager.getDriver(), driverManager.getActions(), driverManager.getWait());
        cardSmartphonePage = new CardSmartphonePage(driverManager.getDriver());
    }

    @Test
    @Description("Проверка сортировки и характеристики Samsung")
    public void samsungTest() {
        mainPage
                .open()
                .hoverOnSmartAndPhoto()
                .clickSmartphoneButton();

                assertTrue(mainPage.isOnSmartphonePage());

        String samsung = "samsung";

        smartphonePage
                .moveToBrand(samsung)
                .clickBrandCheckbox(samsung)
                .moveToRamSection()
                .clickRamSection()
                .moveToRam("l6w97")
                .clickRam("l6w97")
                .clickApplyButton();

        assertTrue(smartphonePage.isOnFilterPage(samsung));

        smartphonePage
                .moveToSortRadiobutton()
                .clickSortRadiobutton()
                .clickAscendingRadiobutton();

        assertTrue(smartphonePage.isOnSortPage(samsung));

        smartphonePage
                .clickFirstPhoneCard();

        assertTrue(smartphonePage.isOnSmartphonePage());

        cardSmartphonePage
                .moveToExpandAllButton()
                .clickExpandAllButton()
                .isInBrand("Samsung");

        cardSmartphonePage
                .isInRam("256 гб");

    }

    @Test
    @Description("Проверка сортировки и характеристики Apple")
    public void appleTest() {
        mainPage
                .open()
                .hoverOnSmartAndPhoto()
                .clickSmartphoneButton();

        assertTrue(mainPage.isOnSmartphonePage());

        String apple = "apple";
        smartphonePage
                .moveToBrand(apple)
                .clickBrandCheckbox(apple)
                .moveToInternalMemorySection()
                .clickInternalMemorySection()
                .moveToInternalMemory("8f9i")
                .clickInternalMemory("8f9i")
                .clickApplyButton();

        assertTrue(smartphonePage.isOnFilterPage(apple));

        smartphonePage
                .moveToSortRadiobutton()
                .clickSortRadiobutton()
                .clickAscendingRadiobutton();

        assertTrue(smartphonePage.isOnSortPage(apple));

        smartphonePage
                .clickFirstPhoneCard();

        assertTrue(smartphonePage.isOnSmartphonePage());

        cardSmartphonePage
                .moveToExpandAllButton()
                .clickExpandAllButton()
                .isInBrand("Apple");

        cardSmartphonePage
                .isInInternalMemory("4 гб");

    }
}
