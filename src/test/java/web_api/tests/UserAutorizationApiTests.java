package web_api.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import web_api.data.ApiModels;
import web_api.data.TestData;
import web_api.models.RequestModels;
import web_api.pages.AuthorizationPage;
import web_api.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static web_api.specs.Specifications.*;


public class UserAutorizationApiTests extends TestBase {

    static TestData testData = new TestData();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();

    @Test
    @Tag("API")
    @Owner("Ochkasov_D")
    @DisplayName("Test successful user authorization")
    void successfulAuthorizationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API authorization", () -> {
            RequestModels formParam = ApiModels.apiAutorizationRequestModels(testData);
            testData.authCookieValue = given()
                    .spec(requestSpec)
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("back", formParam.getBack())
                    .formParam("submitLogin", formParam.getSubmitLogin())
                    .when()
                    .post("/index.php?controller=authentication&back=my-account")
                    .then()
                    .spec(responseSpec302)
                    .extract()
                    .cookie(testData.authCookieKey);
        });

        step("Browser set cookie", () -> {
            mainPage.openMinContent();
            Cookie authCookie = new Cookie(testData.authCookieKey, testData.authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Check UI authorization", () ->
                authorizationPage.checkAuthorizationUser());
    }

    @Test
    @Tag("API")
    @Owner("Ochkasov_D")
    @DisplayName("Test unsuccessful user authorization")
    void unsuccessfulAuthorizationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API authorization", () -> {
            RequestModels formParam = ApiModels.apiUnsuccessfulAutorizationRequestModels(testData);
            testData.authCookieValue = given()
                    .spec(requestSpec)
                    .formParam("email", formParam.getInvalidEmail())
                    .formParam("password", formParam.getInvalidPassword())
                    .formParam("back", formParam.getBack())
                    .formParam("submitLogin", formParam.getSubmitLogin())
                    .when()
                    .post("/index.php?controller=authentication&back=my-account")
                    .then()
                    .spec(responseSpec200)
                    .extract()
                    .cookie(testData.authCookieKey);
        });

        step("Browser set cookie", () -> {
            mainPage.openMinContent();
            Cookie authCookie = new Cookie(testData.authCookieKey, testData.authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Check UI UnsuccessfulAuthorization", () ->
                authorizationPage.checkUnsuccessfulAuthorizationUser());
    }
}


