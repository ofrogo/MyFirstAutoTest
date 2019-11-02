package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Garbage extends AbstractPage {
    private By forFreeDeliveryLabel = By.cssSelector("div[data-auto=\"remainder-description\"] span.voCFmXKfcL");
    private By priceForItemsLabel = By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]");
    //private By priceForDeliveryLabel = By.cssSelector("div[data-auto=\"total-delivery\"] span[data-auto=\"value\"]");
    private By totalPriceLabel = By.cssSelector("div[data-auto=\"total-price\"] span._1oBlNqVHPq");
    private By plusItemButton = By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA");
    private By priceForItemsLeftLabel = By.cssSelector("span._1u3j_pk1db._1pTV0mQZJz._37FeBjfnZk._1JLs4_hnVR span[data-tid=\"c3eaad93\"]:not(._3nXvrJWiZ0)");


    Garbage(WebDriver webDriver) {
        super(webDriver);
    }

    private int getPriceFromLabel(By selector) {
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(selector));
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.attributeToBeNotEmpty(webElement, "innerText"));
        String buf = webElement.getAttribute("innerText");
        if (Character.isDigit(buf.charAt(buf.length() - 1))) {
            buf = buf.replaceAll(" ", "");
        } else {
            buf = buf.substring(0, buf.length() - 2).replaceAll(" ", "");
        }
        return Integer.parseInt(buf);
    }

    public String howMuchForFreeDelivery() {
        return webDriver.findElement(forFreeDeliveryLabel).getText();
    }

    public Boolean checkPrice() {
        int priceForItems = getPriceFromLabel(priceForItemsLabel);
        //int priceForDelivery = getPriceFromLabel(priceForDeliveryLabel);
        int totalPrice = getPriceFromLabel(totalPriceLabel);
        return /*priceForDelivery + */priceForItems == totalPrice;
    }

    private void addOneMoreItem() {
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(plusItemButton));
        webElement.click();
    }

    public void addWhileLess(int price) {
        int p = getPriceFromLabel(priceForItemsLeftLabel);
        while (p < price) {
            addOneMoreItem();
            p = getPriceFromLabel(priceForItemsLeftLabel);
        }
    }
}
