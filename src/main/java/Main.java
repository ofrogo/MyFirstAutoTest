import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://beru.ru");
        webDriver.findElement(new By.ByXPath("/html/body/div[1]/div[2]/div[1]/div/div/div/div/div/div" +
                "/div/div/span/div/div[3]/div[1]/div/div[3]/div/div/div")).click();
        WebElement webElement = webDriver.findElement(By.id("passp-field-login"));
        webElement.sendKeys("DaniilShat@yandex.ru"); //TODO login to config file
        webDriver.findElement(new By.ByCssSelector("#root > div > div > div.passp-flex-wrapper > div > div.passp-auth > div.passp-auth-content > div:nth-child(2) > div > div > div.passp-login-form > form > div.passp-button.passp-sign-in-button > button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
        webElement = webDriver.findElement(By.id("passp-field-passwd"));
        webElement.sendKeys("111111111111"); //TODO password to config file
        webElement.submit();

    }
}
