package beru;

import beru.page.Authorization;
import beru.page.Main;
import org.junit.Assert;
import org.junit.Test;

public class AuthorizationTest extends AbstractTest{
    @Test
    public void firstAuthorization() {
        /*
        Открываем главную страницу
         */
        Main mainPage = new Main();
        /*
        Переходим на страницу авторизации
         */
        Authorization authorizationPage = mainPage.clickOnProfileLabel();
        mainPage = authorizationPage.authorizeAccount(account);
        /*
        Выполняем все необходимые проверки
         */
        Assert.assertEquals("Мой профиль", mainPage.getMyProfileLabel());
        Assert.assertEquals(account.getEmail(), mainPage.checkLoginAfterAuth());
    }
}
