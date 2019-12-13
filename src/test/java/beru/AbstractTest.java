package beru;

import beru.entity.Account;
import beru.service.Service;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractTest {

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
            Service.setWebDriver(webDriver);
            Service.setDriverWait(new WebDriverWait(webDriver, 10));
            account = new Account();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Service.takesScreenshot("failed");
        }
    };

    @After
    public void after(){
        Service.closeBrowser();
    }


}
