package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Settings extends AbstractPage{
    private By deliveryAddressLabel = By.cssSelector("div._2jQ6ZbQaFX span._3l-uEDOaBN.tdrs43E7Xn._3HJsMt3YC_._1MLtFZArtE");

    public Settings(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDeliveryAddress() {
        return webDriver.findElement(deliveryAddressLabel).getAttribute("innerText");
    }


}
