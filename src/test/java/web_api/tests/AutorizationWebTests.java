package web_api.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web_api.pages.AuthorizationPage;
import web_api.pages.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class AutorizationWebTests extends TestBase {

    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test successful user authorization")
    void checkUserSuccessfulAuthorizationTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по кнопке 'Войти'", () ->
                mainPage.openUserLoginPage());

        step("Вводим данные пользователя", () ->
                authorizationPage.InputLoginUser());

        step("Проверям успешную авторизацию", () ->
                authorizationPage.checkAuthorizationUser());
    }

    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test unsuccessful user authorization")
    void checkUserUnsuccessfulAuthorizationTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по кнопке 'Войти'", () ->
                mainPage.openUserLoginPage());

        step("Вводим не действительные данные пользователя", () ->
                authorizationPage.InputInvalidLoginUser());

        step("Проверям сообщение об ошибке авторизации", () ->
                authorizationPage.checkUnsuccessfulAuthorizationUser());
    }
}

