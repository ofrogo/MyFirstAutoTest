package beru.service;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Service {
    private static WebDriver webDriver;
    private static WebDriverWait driverWait;

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(
                "D:\\Projects\\MyFirstAutoTest\\src\\main\\resources\\config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void setDriverWait(WebDriverWait driverWait) {
        Service.driverWait = driverWait;
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static WebDriverWait getDriverWait() {
        return driverWait;
    }

    public static void closeBrowser() {
        webDriver.close();
    }

    public static void setWebDriver(WebDriver webDriver) {
        Service.webDriver = webDriver;
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] takesScreenshot(String screenshotName) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
