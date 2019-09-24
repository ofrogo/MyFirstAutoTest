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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class TestBeru {
    private WebDriver webDriver;
    private String url;
    private Account account;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        url = "https://beru.ru";
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        account = new Account();
        webDriver.get(url);
    }

    @Test
    public void firstAuthorization() {
        Service.authorization(webDriver, account);
        WebElement myDynamicElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.xpath("//span[@class='pFhTbV17qj']")));
        Assert.assertEquals("Мой профиль", myDynamicElement.getAttribute("textContent"));
    }

    @Test
    public void secondChangeCity() throws InterruptedException {
        webDriver.findElement(By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w")).click();
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("input[data-tid=\"37e0ab2d\"]")));
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        webElement.sendKeys("Хвалынск");
        //Thread.sleep(5000); //TODO fix Ракитянка
        webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("li#react-autowhatever-region--item-0")));
        webElement.click();
        webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("button[data-tid='71e1c78d']._4qhIn2-ESi.Pjv3h3YbYr.THqSbzx07u")));
        webElement.click();
        Boolean boolHv = (new WebDriverWait(webDriver, 10))
                .until(textToBe(By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w"), "Хвалынск"));
        Assert.assertTrue(boolHv);
        Service.authorization(webDriver, account);
        webDriver.findElement(By.cssSelector("._1FEpprw_Km")).click();
        webElement = (new WebDriverWait(webDriver, 10))
                .until(presenceOfElementLocated(By.cssSelector("a[href='/my/settings?track=menu']")));
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w"))
                        .getAttribute("textContext"),
                webDriver.findElement(By.cssSelector("body > div.main > div > div:nth-child(1) > div > div > div > " +
                        "div > div > div > div > div > div._1SEkJje5GJ > div._2BUQxcqKF7 > div > div.Mvy4Zvr556 > " +
                        "div > div > div > div > div._2ubPaMe58x._3ZZzYB8tbn.root_arrow_none._1J8-Ybuzc_ > " +
                        "div > div > div.T3jKK6NbAR > div > div._2SFylIV5m5 > div._2I5v9t-gmG > span"))
                        .getAttribute("textContext"));
    }

    @Test
    public void thirdElectricMeter() throws InterruptedException {
        webDriver.findElement(By.cssSelector("button[data-tid='71e1c78d 37df3efd']._2Y-iyAqjAL._4qhIn2-ESi.qAmx3n7Iqk" +
                "._18c2gUxCdP")).click();
        webDriver.findElement(By.cssSelector("a[title='Красота и гигиена']")).click();
        WebElement body = webDriver.findElement(By.cssSelector("body"));
        webDriver.findElement(By.cssSelector("a[href^='/catalog/elektricheskie-zubnye-shchetki']")).click();
        webDriver.findElement(By.cssSelector("input[name='Цена от")).sendKeys("999");
        webDriver.findElement(By.cssSelector("input[name='Цена до")).sendKeys("1999");
        WebElement webElement = webDriver.findElement(By.cssSelector(
                "div[data-bem*='{\"b-zone\":{\"name\":\"snippet-list\"}'].n-snippet-list.n-snippet-list_type_grid" +
                        ".n-snippet-list_size_4.b-zone.b-spy-init.i-bem.b-spy-init_js_inited"));
        new WebDriverWait(webDriver, 10).until(stalenessOf(webDriver.findElement(
                By.cssSelector(".preloadable__preloader.preloadable__preloader_visibility_visible.preloadable__paranja"))));
        //Thread.sleep(3000);
        List<WebElement> prices = webElement.findElements(By.cssSelector("span._1u3j_pk1db._1pTV0mQZJz > " +
                "span[data-tid='c3eaad93']:not(._3nXvrJWiZ0)"));
        for (WebElement price : prices) {
            System.out.println(price.getText());
        }
    }

    @After
    public void tearDown() {
        //webDriver.quit();
    }
}


