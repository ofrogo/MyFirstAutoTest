package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main extends AbstractPage {
    private By regionLabel = By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w");
    private By loginToAccountLabel = By.cssSelector("a._3ioN70chUh._1FEpprw_Km._3Uc73lzxcf");
    private By myProfileLabel = By.cssSelector("span.pFhTbV17qj");
    private By myProfileButton = By.cssSelector("button._1FEpprw_Km");
    private By settingsButton = By.cssSelector("a[href='/my/settings?track=menu']");
    private By catalogButton = By.cssSelector("button._2Y-iyAqjAL._4qhIn2-ESi.qAmx3n7Iqk._18c2gUxCdP");
    private By beautyHygieneLabel = By.cssSelector("a[title='Красота и гигиена']");
    private By loginLabel = By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_");

    public Main(WebDriver webDriver) {
        super(webDriver);
    }

    public Authorization clickOnProfileLabel() {
        webDriver.findElement(loginToAccountLabel).click();
        return new Authorization(webDriver);
    }

    public String checkLoginAfterAuth() {
        webDriver.findElement(myProfileButton).click();
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOf(webDriver.findElement(loginLabel)));
        return webDriver.findElement(loginLabel).getText();
    }

    public String getMyProfileLabel() {
        WebElement webElement = webDriver.findElement(myProfileLabel);
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.attributeToBeNotEmpty(webElement, "innerText"));
        return webElement.getAttribute("innerText");
    }

    public ChangeCityWindow clickOnRegionLabel() {
        webDriver.findElement(regionLabel).click();
        return new ChangeCityWindow();
    }

    public String getRegionLabel() {
        WebElement webElement = webDriver.findElement(regionLabel);
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.attributeToBeNotEmpty(webElement, "innerText"));
        return webElement.getAttribute("innerText");
    }

    public Boolean checkRegion(String city) {
        return (new WebDriverWait(webDriver, 10).until(ExpectedConditions.textToBe(regionLabel, city)));
    }

    public Settings clickOnMyProfileSetting() {
        webDriver.findElement(myProfileButton).click();
        WebElement webElement = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(settingsButton));
        webElement.click();
        return new Settings(webDriver);
    }

    public BeautyHygieneCatalog goToBeautyAndHygiene() {
        webDriver.findElement(catalogButton).click();
        webDriver.findElement(beautyHygieneLabel).click();
        return new BeautyHygieneCatalog(webDriver);
    }

    public class ChangeCityWindow {

        private By cityInput = By.cssSelector("div._1U2ErCeoqP  input[data-tid=\"37e0ab2d\"]");
        private By firstInList = By.cssSelector("li#react-autowhatever-region--item-0 div._229JDbp_Z8");
        private By submitButton = By.cssSelector("button[data-tid='71e1c78d']._4qhIn2-ESi.Pjv3h3YbYr.THqSbzx07u");

        private ChangeCityWindow() {
        }

        public Main changeCity(String city) {
            enterCity(city);
            chooseCityFromList(city);
            submit();
            return new Main(webDriver);
        }

        private void enterCity(String city) {
            WebElement webElement = (new WebDriverWait(webDriver, 10))
                    .until((ExpectedCondition<WebElement>) d -> d.findElement(cityInput));
            webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            for (char c : city.toCharArray()) {
                webElement.sendKeys(String.valueOf(c));
            }

        }

        private void chooseCityFromList(String city) {
            (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.attributeToBe(firstInList, "innerText", city));
            webDriver.findElement(firstInList).click();
        }

        private void submit() {
            WebElement webElement = (new WebDriverWait(webDriver, 10))
                    .until((ExpectedCondition<WebElement>) d -> d.findElement(submitButton));
            webElement.click();
        }
    }
}
