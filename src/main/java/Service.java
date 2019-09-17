import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.sun.activation.registries.LogSupport.log;

public class Service {

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(
                "/home/danil/IdeaProjects/MyFirstTest/src/main/resources/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void safeClick(WebDriver webDriver, String elementLocator) {
        WebElement webElement = webDriver.findElement(By.cssSelector(elementLocator));
        if (webElement != null) {
            webElement.click();
        } else {
            System.out.println("Element: " + elementLocator + ", is not available on page - "
                    + webDriver.getCurrentUrl());
        }
    }
}
