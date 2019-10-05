package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Garbage extends AbstractPage {
    private By forFreeDeliveryLabel = By.cssSelector("span.voCFmXKfcL");
    private By totalPriceForItemsLabel = By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]");
    private By priceForDeliveryLabel = By.cssSelector("div[data-auto=\"total-delivery\"] span[data-auto=\"value\"]");
    private By totalPriceLabel = By.cssSelector("div[data-auto=\"total-price\"] span._1oBlNqVHPq");

    Garbage(WebDriver webDriver) {
        super(webDriver);
    }

    public String howMuchForFreeDelivery() {
        return webDriver.findElement(forFreeDeliveryLabel).getText();
    }

    public Boolean checkPrice() {
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(totalPriceForItemsLabel));
       (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.attributeToBeNotEmpty(webElement, "innerText"));
        String buf = webElement.getAttribute("innerText");
        int priceForItems = Integer.parseInt(buf.substring(0, buf.length() -2));
        System.out.println(priceForItems);
        return true;
    }

}
