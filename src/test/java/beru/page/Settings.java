package beru.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Settings extends AbstractPage {
    @FindBy(css = "div.TyYugfiSCL._1sbUn_k_xX div[data-apiary-widget-name=\"@marketplace/RegionLink\"] span[data-tid=\"52906e8d\"]._3l-uEDOaBN.tdrs43E7Xn._3HJsMt3YC_")
    private WebElement deliveryAddressLabel;

    public Settings() {
        super();
    }

    public String getDeliveryAddress() {
        return deliveryAddressLabel.getAttribute("innerText");
    }


}
