package beru.page;

import org.openqa.selenium.support.PageFactory;
import beru.service.Service;

public abstract class AbstractPage {
    public AbstractPage() {
        PageFactory.initElements(Service.getWebDriver(), this);
    }
}
