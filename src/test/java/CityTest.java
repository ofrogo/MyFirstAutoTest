import beru.AbstractClass;
import beru.page.Authorization;
import beru.page.Main;
import beru.page.Settings;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CityTest extends AbstractClass {
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
        System.out.println(settingsPage.getDeliveryAddress());
        System.out.println(mainPage.getRegionLabel());
        Assert.assertEquals("Delivery label doesn't equal region label.", settingsPage.getDeliveryAddress(), mainPage.getRegionLabel());
    }
}
