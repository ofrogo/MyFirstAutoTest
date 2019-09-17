import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("/home/danil/IdeaProjects/MyFirstTest/src/main/resources/config.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://beru.ru");
        webDriver.findElement(new By.ByCssSelector("body > div.main > div:nth-child(2) > div:nth-child(1) > div > div > div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2BUQxcqKF7 > div > div.Mvy4Zvr556 > div > div > div > a")).click();
        WebElement webElement = webDriver.findElement(By.id("passp-field-login"));
        webElement.sendKeys(properties.getProperty("yandex.email"));
        webDriver.findElement(new By.ByCssSelector("#root > div > div > div.passp-flex-wrapper > div > div.passp-auth > div.passp-auth-content > div:nth-child(2) > div > div > div.passp-login-form > form > div.passp-button.passp-sign-in-button > button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
        webElement = webDriver.findElement(By.id("passp-field-passwd"));
        webElement.sendKeys(properties.getProperty("yandex.password"));
        webElement.submit();

    }
}
