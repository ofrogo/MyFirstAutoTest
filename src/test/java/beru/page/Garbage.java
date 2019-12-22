package beru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import beru.service.Service;

public class Garbage extends AbstractPage {
    @FindBy(css = "div[data-auto=\"remainder-description\"] span.voCFmXKfcL")
    private WebElement forFreeDeliveryLabel;
    @FindBy(css = "div[data-auto=\"total-items\"] span[data-auto=\"value\"]")
    private WebElement priceForItemsLabel;
    @FindBy(xpath = "//div[contains(@data-auto, 'total-items')]")
    private WebElement priceForDeliveryLabel;
    @FindBy(css = "div[data-auto=\"total-price\"] span._1oBlNqVHPq")
    private WebElement totalPriceLabel;
    @FindBy(xpath = "//span[contains(text(), '+')]/../..")
    private WebElement plusItemButton;
    @FindBy(css = "span._1u3j_pk1db._1pTV0mQZJz._37FeBjfnZk._1JLs4_hnVR span[data-tid=\"c3eaad93\"]:not(._3nXvrJWiZ0)")
    private WebElement priceForItemsLeftLabel;

    @FindBy(xpath = "//div[contains(@data-tid, '95256ff1')]//button")
    private WebElement checkoutOrder;

    Garbage() {
        super();
    }

    public Boolean checkPrice() {
        Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(priceForItemsLabel)));
        Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(totalPriceLabel)));

        return priceForItemsLabel.getText().equals(totalPriceLabel.getText());
    }

    @Step(value = "Увеличиваем количество щеток, пока стоимость не превысит {priceTo}")
    public void addToothBrushes(int priceTo) {
        Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(priceForItemsLabel)));
        String buff = priceForItemsLabel.getText();
        int price = Integer.parseInt(buff.substring(0, buff.length() - 2).replaceAll("\\s+", ""));
        for (int i = price; i <= priceTo; i += price) {
            addToothBrush();
        }
    }

    private void addToothBrush() {
        String prevPrice = priceForDeliveryLabel.findElement(By.xpath(".//span[@data-auto = 'value']")).getText();

        plusItemButton.click();

        Service.getDriverWait().until((ExpectedCondition<Boolean>) driver ->
                !priceForDeliveryLabel.findElement(By.xpath(".//span[@data-auto = 'value']")).getText().equals(prevPrice));
        Service.getDriverWait().until((ExpectedCondition<Boolean>) driver -> !priceForItemsLabel.getText().equals(prevPrice));
    }

    @Step(value = "Переходим к оформлению заказа")
    public CheckoutOrderPage checkoutOrder() {
        Service.getDriverWait().until(ExpectedConditions.or(ExpectedConditions.stalenessOf(checkoutOrder),
                ExpectedConditions.elementToBeClickable(checkoutOrder)));
        checkoutOrder.click();
        return new CheckoutOrderPage();
    }
}
