package beru;

import beru.page.*;
import org.junit.Assert;
import org.junit.Test;

public class BrushesTest extends AbstractTest {
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
