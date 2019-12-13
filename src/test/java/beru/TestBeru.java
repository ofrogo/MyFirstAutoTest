package beru;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import beru.entity.Account;
import beru.page.*;
import beru.page.Main;
import beru.service.Service;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@RunWith(DataProviderRunner.class)
public class TestBeru {

    private Account account;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\chromedriver.exe");
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get("https://beru.ru");
            Service.setWebDriver(webDriver);
            Service.setDriverWait(new WebDriverWait(webDriver, 10));
            account = new Account();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Service.takesScreenshot("failed");
        }
    };

    @After
    public void after(){
        //Service.closeBrowser();
    }

    @Test
    public void firstAuthorization() {
        /*
        Открываем главную страницу
         */
        Main mainPage = new Main();
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
    @DataProvider(value = {"Хвалынск", "Омск", "Энгельс"})
    public void secondChangeCity(String city) {
        Main mainPage = new Main();
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
        Main mainPage = new Main();
        int priceFrom = 999, priceUp = 1999;
        BeautyHygieneCatalog bHCatalogPage = mainPage.goToBeautyAndHygiene();
        ElectricToothbrushCatalog elTCatalogPage = bHCatalogPage.selectElectricToothbrush();
        elTCatalogPage.setPriceFrom(priceFrom);
        elTCatalogPage.setPriceUp(priceUp);
        Assert.assertTrue("Price limit doesn't set.", elTCatalogPage.checkPriceList(priceFrom, priceUp));
        Garbage garbagePage = elTCatalogPage.addPenultToGarbage();
        Assert.assertTrue("Sum prices doesn't equal total price", garbagePage.checkPrice());
        CheckoutOrderPage orderPage = garbagePage.checkoutOrder();
        orderPage.checkPrice();
        garbagePage = orderPage.backToCart();
        garbagePage.addToothBrushes(2999);
        Assert.assertTrue("Sum prices doesn't equal total price", garbagePage.checkPrice());
    }

}


