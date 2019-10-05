package page;

import entity.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Authorization {
    private By loginInput = By.cssSelector("input#passp-field-login");
    private By enterButton = By.cssSelector("button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button");
    private By passwordInput = By.cssSelector("input#passp-field-passwd");

    private WebDriver webDriver;

    Authorization(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void enterLogin(String login) {
        WebElement webElement = webDriver.findElement(loginInput);
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        webElement.sendKeys(login);
    }

    private void clickOnEnterButton() {
        webDriver.findElement(enterButton).click();
    }

    private void enterPassword(String password) {
        WebElement webElement = webDriver.findElement(passwordInput);
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        webElement.sendKeys(password);
        webElement.submit();
    }

    public Main authorizeAccount(Account account){
        enterLogin(account.getEmail());
        clickOnEnterButton();
        enterPassword(account.getPassword());
        return new Main(webDriver);
    }
}
