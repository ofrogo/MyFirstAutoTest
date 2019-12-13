package beru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import beru.service.Service;

public class BeautyHygieneCatalog extends AbstractPage {
    @FindBy(css = "a[href^='/catalog/elektricheskie-zubnye-shchetki']")
    private WebElement electricToothbrushLabel;

    BeautyHygieneCatalog() {
        super();
    }

    @Step("Открытие каталога \"Электрические зубные щетки\"")
    public ElectricToothbrushCatalog selectElectricToothbrush() {
        (new WebDriverWait(Service.getWebDriver(), 30)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(electricToothbrushLabel)));
        electricToothbrushLabel.click();
        return new ElectricToothbrushCatalog();
    }
}
