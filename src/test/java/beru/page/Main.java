package beru.page;

import beru.service.Service;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Main extends AbstractPage {
    @FindBy(css = "div[data-zone-name=\"SubHeader\"] span[data-auto='region-form-opener']")
    private WebElement regionLabel;

    @FindBy(css = "a._3ioN70chUh._1FEpprw_Km._3Uc73lzxcf")
    private WebElement loginToAccountLabel;

    @FindBy(css = "span.pFhTbV17qj")
    private WebElement myProfileLabel;

    @FindBy(css = "button._1FEpprw_Km")
    private WebElement myProfileButton;

    @FindBy(css = "a[href='/my/settings?track=menu']")
    private WebElement settingsButton;

    @FindBy(css = "button._2Y-iyAqjAL._4qhIn2-ESi.qAmx3n7Iqk._18c2gUxCdP")
    private WebElement catalogButton;

    @FindBy(css = "a[title='Красота и гигиена']")
    private WebElement beautyHygieneLabel;

    @FindBy(css = "div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_")
    private WebElement loginLabel;

    public Main() {
        super();
    }

    /*
    Все действия обернуты в методы
     */
    @Step("Переход на окно автризации")
    public Authorization clickOnProfileLabel() {
        loginToAccountLabel.click();  //находим элемент loginToAccountLabel и кликаем на него
        return new Authorization();
    }

    public String checkLoginAfterAuth() {
        moveCursorOnProfile();
        myProfileButton.click();  //находим элемент myProfileButton и кликаем на него
        /*
        Явное ожидание пока loginLabel не станет видимым
         */
        Service.getDriverWait().until(ExpectedConditions.visibilityOf(loginLabel));
        return loginLabel.getText();  //находим элемент loginLabel и возвращаем текст который содержится в элементе
    }

    public String getMyProfileLabel() {
        moveCursorOnProfile();
        /*
        Явное ожидание пока у найденного элемента атрибут innerText (т.е. внутренний текст) перестанет быть пустым
         */
        Service.getDriverWait().until(ExpectedConditions.attributeToBeNotEmpty(myProfileLabel, "innerText"));
        return myProfileLabel.getAttribute("innerText");
    }

    private void moveCursorOnProfile() {
        Service.getDriverWait().until(ExpectedConditions.visibilityOf(myProfileButton));
        while (!loginLabel.isDisplayed())
            new Actions(Service.getWebDriver()).moveToElement(myProfileButton).build().perform();
    }

    @Step("Открываем окно смены города")
    public ChangeCityWindow clickOnRegionLabel() {
        regionLabel.click();
        return new ChangeCityWindow();
    }

    public String getRegionLabel() {
        Service.getDriverWait().until(ExpectedConditions.attributeToBeNotEmpty(regionLabel, "innerText"));
        return regionLabel.getText().split("\\s")[2];
    }

    public Boolean checkRegion(String city) {
        Service.getDriverWait().until(ExpectedConditions.not(ExpectedConditions.stalenessOf(regionLabel)));
        System.out.println(regionLabel.getText().split("\\s")[2]);
        return regionLabel.getText().split("\\s")[2].equals(city);
    }

    @Step("Переход на страницу настроек")
    public Settings clickOnMyProfileSetting() {
        moveCursorOnProfile();
        myProfileButton.click();
        /*
        Пытаемся найти элемент settingsButton в течение 30 секунд
         */
        Service.getDriverWait().until(ExpectedConditions.elementToBeClickable(settingsButton));
        settingsButton.click();
        return new Settings();
    }

    @Step("Открытие каталога \"Красота и гигиена\"")
    public BeautyHygieneCatalog goToBeautyAndHygiene() {
        Service.getDriverWait().until(ExpectedConditions.elementToBeClickable(catalogButton));
        catalogButton.click();
        /*
        Явное ожидание пока webElement не станет кликабельным
         */
        Service.getDriverWait().until(ExpectedConditions.elementToBeClickable(beautyHygieneLabel));
        beautyHygieneLabel.click();
        return new BeautyHygieneCatalog();
    }


    /*
    Подкласс отвечающий за всплывающее окно для смены города
     */
    public class ChangeCityWindow extends AbstractPage {
        @FindBy(css = "div._1U2ErCeoqP  input[data-tid=\"37e0ab2d\"]")
        private WebElement cityInput;
        @FindBy(css = "li#react-autowhatever-region--item-0 div._229JDbp_Z8")
        private WebElement firstInList;
        @FindBy(css = "button[data-tid='71e1c78d']._4qhIn2-ESi.Pjv3h3YbYr.THqSbzx07u")
        private WebElement submitButton;

        private ChangeCityWindow() {
            super();
        }

        @Step("Меняем город")
        public Main changeCity(String city) {
            enterCity(city);
            chooseCityFromList(city);
            submit();
            return new Main();
        }

        private void enterCity(String city) {
            Service.getDriverWait().until((ExpectedConditions.visibilityOf(cityInput)));
            cityInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));  //Очищаем поле ввода
            for (char c : city.toCharArray()) {
                cityInput.sendKeys(String.valueOf(c));
            }
        }

        private void chooseCityFromList(String city) {
            Service.getDriverWait().until(ExpectedConditions.attributeToBe(firstInList, "innerText", city));
            firstInList.click();
        }

        private void submit() {
            Service.getDriverWait().until((ExpectedConditions.visibilityOf(submitButton)));
            submitButton.click();
        }
    }
}
