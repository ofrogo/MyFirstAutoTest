package beru.page;

import beru.entity.Account;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import beru.service.Service;

public class Authorization extends AbstractPage {
    @FindBy(css = "input#passp-field-login")
    private WebElement loginInput;

    @FindBy(css = "button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")
    private WebElement enterButton;

    @FindBy(css = "input#passp-field-passwd")
    private WebElement passwordInput;


    Authorization() {
        super();
    }

    private void enterLogin(String login) {
        (new WebDriverWait(Service.getWebDriver(), 30)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(loginInput)));
        loginInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        loginInput.sendKeys(login);
    }

    private void clickOnEnterButton() {
        enterButton.click();
    }

    private void enterPassword(String password) {
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        passwordInput.sendKeys(password);
        passwordInput.submit();
    }

    @Step("Авторизация на сайте")
    public Main authorizeAccount(Account account) {
        enterLogin(account.getEmail());
        clickOnEnterButton();
        enterPassword(account.getPassword());
        return new Main();
    }
}
