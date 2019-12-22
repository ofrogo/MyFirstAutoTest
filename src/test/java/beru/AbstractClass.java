package beru;

import beru.entity.Account;
import beru.service.Service;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractClass {

    protected Account account;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\chromedriver.exe");
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get("https://beru.ru");


            EventFiringWebDriver driver = new EventFiringWebDriver(webDriver);

            driver.register(new AbstractWebDriverEventListener() {
                @Override
                public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", webElement);
                }
            });


            Service.setWebDriver(driver);
            Service.setDriverWait(new WebDriverWait(webDriver, 30));
            account = new Account();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Service.takesScreenshot("failed");
        }

        @Override
        protected void finished(Description description) {
            Service.closeBrowser();
        }
    };


}
