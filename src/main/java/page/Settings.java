package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Settings extends AbstractPage{
    private By deliveryAddressLabel = By.cssSelector("div.TyYugfiSCL._1sbUn_k_xX div[data-apiary-widget-name=\"@marketplace/RegionLink\"] span[data-tid=\"52906e8d\"]._3l-uEDOaBN.tdrs43E7Xn._3HJsMt3YC_");

    public Settings(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDeliveryAddress() {
        return webDriver.findElement(deliveryAddressLabel).getAttribute("innerText");
    }


}
