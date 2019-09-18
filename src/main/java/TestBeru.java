import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
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
        webDriver.findElement(By.cssSelector("body > div.main > div:nth-child(2) > div:nth-child(1) > div > " +
                "div > div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2BUQxcqKF7 > div > " +
                "div.Mvy4Zvr556 > div > div > div > a")).click();
        WebElement webElement = webDriver.findElement(By.id("passp-field-login"));
        webElement.sendKeys(account.getEmail());
        webDriver.findElement(By.cssSelector("#root > div > div > div.passp-flex-wrapper > div > div.passp-auth > " +
                "div.passp-auth-content > div:nth-child(2) > div > div > div.passp-login-form > form > " +
                "div.passp-button.passp-sign-in-button > button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
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
        Assert.assertEquals("Мой профиль", myDynamicElement.getAttribute("textContent"));
    }

    @Test
    public void secondChangeCity() throws InterruptedException {
        webDriver.findElement(By.cssSelector("body > div.main > div:nth-child(2) > div:nth-child(1) > div > div > " +
                "div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2cfUiVAU6V > div > div > " +
                "div > div > span > span._2XJ6yiRp5w")).click();
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.cssSelector("input[data-tid=\"37e0ab2d\"]"));
                    }
                });
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        webElement.sendKeys("Хвалынск");
        webElement = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.cssSelector("#react-autowhatever-region--item-0"));
                    }
                });
        webElement.click();
        webElement = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.cssSelector("._4qhIn2-ESi.Pjv3h3YbYr.THqSbzx07u"));
                    }
                });
        webElement.click();
        Thread.sleep(5000); // TODO change
        String city = webDriver.findElement(By.cssSelector("body > div.main > div:nth-child(2) > div:nth-child(1) > " +
                "div > div > div > div > div > div > div > div > span > div > div._1SEkJje5GJ > div._2cfUiVAU6V > " +
                "div > div > div > div > span > span._2XJ6yiRp5w")).getAttribute("textContent");
        Assert.assertEquals("Хвалынск", city);
    }

    @After
    public void tearDown() {
        //webDriver.quit();
    }
}
