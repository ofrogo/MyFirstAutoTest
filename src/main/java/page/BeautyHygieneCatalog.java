package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BeautyHygieneCatalog extends AbstractPage {
    private By electricToothbrushLabel = By.cssSelector("a[href^='/catalog/elektricheskie-zubnye-shchetki']");

    BeautyHygieneCatalog(WebDriver webDriver) {
        super(webDriver);
    }

    public ElectricToothbrushCatalog selectElectricToothbrush() {
        webDriver.findElement(electricToothbrushLabel).click();
        return new ElectricToothbrushCatalog(webDriver);
    }
}
