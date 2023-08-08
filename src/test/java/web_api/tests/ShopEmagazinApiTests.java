package web_api.tests;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import web_api.data.ApiModels;
import web_api.data.TestData;
import web_api.models.RequestModels;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static web_api.specs.Specifications.*;


public class ShopEmagazinApiTests extends TestBase {

    static TestData testData = new TestData();
    @Test
    @Tag("API")
    @DisplayName("Test user authorization")
    void authorizationUserTest() {
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

        step("Check UI authorization", () -> {
            authorizationPage.checkAuthorizationUser();
        });
    }



    @Test
    @Tag("API")
    @DisplayName("Test successful user registration")
    void successRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API registration", () -> {
            RequestModels formParam = ApiModels.apiSuccessfulRegistrationRequestModels(testData);
            given()
                    .spec(requestSpec)
                    .formParam("id_gender", formParam.getIdGender())
                    .formParam("firstname", formParam.getFirstName())
                    .formParam("lastname", formParam.getLastName())
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("birthday", formParam.getBirthDay())
                    .formParam("psgdpr", formParam.getPsgdpr())
                    .formParam("submitCreate", formParam.getSubmitCreate())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpec302);

        });
    }

    @Test
    @Tag("API")
    @DisplayName("Test unsuccessful user registration")
    void failRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API registration", () -> {
            RequestModels formParam = ApiModels.apiUnsuccessfulRegistrationRequestModels(testData);
            given()
                    .spec(requestSpec)
                    .formParam("firstname", formParam.getFirstName())
                    .formParam("lastname", formParam.getLastName())
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("psgdpr", formParam.getPsgdpr())
                    .formParam("submitCreate", formParam.getSubmitCreate())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpecFail200);
        });

    }
}

