package page;

import org.openqa.selenium.WebDriver;

abstract class AbstractPage {
    WebDriver webDriver;

    private AbstractPage() {
    }

    AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
