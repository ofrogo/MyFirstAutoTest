package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElectricToothbrushCatalog extends AbstractPage {
//    private By priceFromInput = By.cssSelector("div[data-auto=\"filter-range-glprice\"]._1cFYt5OFuy span[data-auto=\"filter-range-min\"]._23UlVlkdxZ input");
//    private By priceUpInput = By.cssSelector("div[data-auto=\"filter-range-glprice\"]._1cFYt5OFuy span[data-auto=\"filter-range-max\"]._23UlVlkdxZ input");
    private By priceFromInput = By.cssSelector("input[name='Цена от");
    private By priceUpInput = By.cssSelector("input[name='Цена до");
    private By loadCurtain = By.cssSelector(".preloadable__preloader.preloadable__preloader_visibility_visible.preloadable__paranja");
    private By productsDiv = By.cssSelector("div[data-bem*='{\"b-zone\":{\"name\":\"snippet-list\"}'].n-snippet-list.n-snippet-list_type_grid.n-snippet-list_size_4.b-zone.b-spy-init.i-bem.b-spy-init_js_inited");
    private By inGarbageButton = By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH.THqSbzx07u");
    private By toGarbageButton = By.cssSelector("a[data-tid=\"f54fb4c8 ad969e88\"]._3ioN70chUh._3Uc73lzxcf");

    ElectricToothbrushCatalog(WebDriver webDriver) {
        super(webDriver);
    }

    public void setPriceFrom(int price) {
        webDriver.findElement(priceFromInput).sendKeys(String.valueOf(price));
    }

    public void setPriceUp(int price) {
        webDriver.findElement(priceUpInput).sendKeys(String.valueOf(price));
    }

    public Boolean checkPriceList(int priceFrom, int priceUp) {
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(loadCurtain));
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.stalenessOf(webDriver.findElement(loadCurtain)));
        WebElement webElement = webDriver.findElement(productsDiv);
        List<WebElement> prices = webElement.findElements(By.cssSelector("span._1u3j_pk1db._1pTV0mQZJz > span[data-tid='c3eaad93']:not(._3nXvrJWiZ0)"));
        for (WebElement price : prices) {
            int p = Integer.parseInt(price.getText().replaceAll(" ", ""));
            if (p < priceFrom || p > priceUp) {
                return false;
            }
        }
        return true;
    }

    public Garbage addPenultToGarbage() {
        List<WebElement> btns = webDriver.findElements(inGarbageButton);
        btns.get(btns.size() - 2).click();
        (new WebDriverWait(webDriver, 15))
                .until(ExpectedConditions.attributeToBe(toGarbageButton, "innerText", "В корзине"));
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOf(webDriver.findElement(toGarbageButton)));
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(toGarbageButton));
        webDriver.findElement(toGarbageButton).click();
        return new Garbage(webDriver);
    }
}
