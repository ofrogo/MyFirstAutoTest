import entity.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.Authorization;
import page.BeautyHygieneCatalog;
import page.ElectricToothbrushCatalog;
import page.Garbage;
import page.Main;
import page.Settings;

import java.util.concurrent.TimeUnit;

public class TestBeru {
    private WebDriver webDriver;
    private String url;
    private Account account;

    @Before
    public void setUp() {
        /*
        Начальные настройки для веб-драйвера
         */
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        webDriver = new ChromeDriver();
        url = "https://beru.ru";
        /*
        Установка неявных ожиданий
         */
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        webDriver.get(url);

        /*
        Инициализация "аккаунта"
         */
        account = new Account();
    }

    @Test
    public void firstAuthorization() {
        /*
        Открываем главную страницу
         */
        Main mainPage = new Main(webDriver);
        /*
        Переходим на страницу авторизации
         */
        Authorization authorizationPage = mainPage.clickOnProfileLabel();
        mainPage = authorizationPage.authorizeAccount(account);
        /*
        Выполняем все необходимые проверки
         */
        Assert.assertEquals("Мой профиль", mainPage.getMyProfileLabel());
        Assert.assertEquals(account.getEmail(), mainPage.checkLoginAfterAuth());
    }

    @Test
    public void secondChangeCity() {
        Main mainPage = new Main(webDriver);
        String city = "Хвалынск";
        Main.ChangeCityWindow changeCityWindow = mainPage.clickOnRegionLabel();
        mainPage = changeCityWindow.changeCity(city);
        Assert.assertTrue(String.format("Region hasn't changed to %s", city), mainPage.checkRegion(city));
        Authorization authorizationPage = mainPage.clickOnProfileLabel();
        mainPage = authorizationPage.authorizeAccount(account);
        Settings settingsPage = mainPage.clickOnMyProfileSetting();
        Assert.assertTrue("Delivery label doesn't equal region label.", settingsPage.getDeliveryAddress().equals(mainPage.getRegionLabel()));
    }

    @Test
    public void thirdElectricMeter() {
        Main mainPage = new Main(webDriver);
        int priceFrom = 999, priceUp = 1999;
        BeautyHygieneCatalog bHCatalogPage = mainPage.goToBeautyAndHygiene();
        ElectricToothbrushCatalog elTCatalogPage = bHCatalogPage.selectElectricToothbrush();
        elTCatalogPage.setPriceFrom(priceFrom);
        elTCatalogPage.setPriceUp(priceUp);
        Assert.assertTrue("Price limit doesn't set.", elTCatalogPage.checkPriceList(priceFrom, priceUp));
        Garbage garbagePage = elTCatalogPage.addPenultToGarbage();
        Assert.assertTrue("Sum prices doesn't equal total price", garbagePage.checkPrice());
        garbagePage.addWhileLess(2999);
        Assert.assertTrue("Sum prices doesn't equal total price", garbagePage.checkPrice());
    }

    @After
    public void tearDown() {
        //webDriver.quit();
    }
}


