import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Service {

    static void authorization(WebDriver webDriver, Account account) {
        webDriver.findElement(By.cssSelector("a._3ioN70chUh._1FEpprw_Km._3Uc73lzxcf")).click();
        WebElement webElement = webDriver.findElement(By.id("passp-field-login"));
        webElement.sendKeys(account.getEmail());
        webDriver.findElement(By.cssSelector("#root > div > div > div.passp-flex-wrapper > div > div.passp-auth > " +
                "div.passp-auth-content > div:nth-child(2) > div > div > div.passp-login-form > form > " +
                "div.passp-button.passp-sign-in-button > button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
        webElement = webDriver.findElement(By.id("passp-field-passwd"));
        webElement.sendKeys(account.getPassword());
        webElement.submit();
    }

    static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(
                "/home/danil/IdeaProjects/MyFirstTest/src/main/resources/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
