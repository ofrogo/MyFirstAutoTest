package beru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import beru.service.Service;

import java.util.List;

public class ElectricToothbrushCatalog extends AbstractPage {
    @FindBy(xpath = "//span[@data-auto = 'filter-range-min']//input")
    private WebElement priceFromInput;
    @FindBy(xpath = "//span[@data-auto = 'filter-range-max']//input")
    private WebElement priceUpInput;
    @FindBy(css = ".preloadable__preloader.preloadable__preloader_visibility_visible.preloadable__paranja")
    private WebElement loadCurtain;
    @FindBy(css = "div[data-apiary-widget-name=\"@marketplace/SearchSerp\"]")
    private WebElement productsDiv;
    @FindBy(css = "button._4qhIn2-ESi._3OWdR9kZRH.THqSbzx07u")
    private List<WebElement> inGarbageButtons;
    @FindBy(css = "div[data-apiary-widget-name=\"@marketplace/SearchSerp\"] a[href = '/my/cart']")
    private WebElement toGarbageButton;

    ElectricToothbrushCatalog() {
        super();
    }

    @Step("Установить фильтр \"Цена от\"")
    public void setPriceFrom(int price) {
        priceFromInput.sendKeys(String.valueOf(price));
    }

    @Step("Установить фильтр \"Цена до\"")
    public void setPriceUp(int price) {
        priceUpInput.sendKeys(String.valueOf(price));
    }

    public Boolean checkPriceList(int priceFrom, int priceUp) {
        //Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(loadCurtain)));
        //Service.getDriverWait().until(ExpectedConditions.stalenessOf(loadCurtain));
        WebElement webElement = productsDiv;
        Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(productsDiv)));
        List<WebElement> prices = webElement.findElements(By.cssSelector("span._1u3j_pk1db._1pTV0mQZJz > span[data-tid='c3eaad93']:not(._3nXvrJWiZ0)"));
        for (WebElement price : prices) {
            int p = Integer.parseInt(price.getText().replaceAll(" ", ""));
            if (p < priceFrom || p > priceUp) {
                return false;
            }
        }
        return true;
    }

    @Step("Добавление товара в корзину")
    public Garbage addPenultToGarbage() {
        inGarbageButtons.get(inGarbageButtons.size() - 4).click();
        //Service.getDriverWait().until(ExpectedConditions.attributeToBe(toGarbageButton, "innerText", "В корзине"));
        //Service.getDriverWait().until(ExpectedConditions.visibilityOf(toGarbageButton));
        Service.getDriverWait().until(ExpectedConditions.elementToBeClickable(toGarbageButton));
        Service.getDriverWait().until((ExpectedCondition<Boolean>) driver ->
                toGarbageButton.getAttribute("innerHTML").contains("В корзине"));
        toGarbageButton.click();
        return new Garbage();
    }
}
