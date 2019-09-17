import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBeru {
    private WebDriver webDriver;
    private String url;
    private Account account;

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        url = "https://beru.ru";
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        account = new Account();
        webDriver.get(url);
    }

    @Test
    public void firstAuthorization() {
        Service.safeClick(webDriver, "body > div.main > div:nth-child(2) > div:nth-child(1) > div > " +
                "div > div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2BUQxcqKF7 > div > " +
                "div.Mvy4Zvr556 > div > div > div > a");
        WebElement webElement = webDriver.findElement(By.id("passp-field-login"));
        webElement.sendKeys(account.getEmail());
        Service.safeClick(webDriver, "#root > div > div > div.passp-flex-wrapper > div > div.passp-auth > " +
                "div.passp-auth-content > div:nth-child(2) > div > div > div.passp-login-form > form > " +
                "div.passp-button.passp-sign-in-button > button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button");
        webElement = webDriver.findElement(By.id("passp-field-passwd"));
        webElement.sendKeys(account.getPassword());
        webElement.submit();
        WebElement myDynamicElement = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.xpath("//span[@class='pFhTbV17qj']"));
                    }
                });
        String res = myDynamicElement.getAttribute("textContent");
        if (res.equals("Мой профиль")) {
            System.out.println("Authorization is ok.");
        } else {
            System.out.println("Authorization is not ok.");
        }
    }

    @Test
    public void secondChangeCity() {
        Service.safeClick(webDriver, "body > div.main > div:nth-child(2) > div:nth-child(1) > div > div > " +
                "div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2cfUiVAU6V > div > div > " +
                "div > div > span > span._2XJ6yiRp5w");
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.xpath("//*[@id=\"textfield8070504168\"]"));
                    }
                });
        webElement.clear();
        webElement.sendKeys("Хвалынск");
        webElement.submit();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
