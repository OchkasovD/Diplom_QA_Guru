package web_api.tests;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import web_api.models.RequestModels;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static web_api.specs.Specifications.*;




public class ShopEmagazinApiTests extends TestBase {
    private  TestData testData = new TestData();
    @Test
    @Tag("API")
    @DisplayName("Test user authorization")
    void authorizationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API authorization", () -> {
            RequestModels formParam = new RequestModels();
            formParam.setBack(testData.back);
            formParam.setEmail(testData.email);
            formParam.setPassword(testData.password);
            formParam.setSubmitLogin(testData.submitLogin);
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

        step("Проверка авторизации через UI", () -> {
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
            RequestModels formParam = new RequestModels();
            formParam.setIdGender(testData.genderId);
            formParam.setFirstName(testData.firstName);
            formParam.setLastName(testData.lastName);
            formParam.setEmail(testData.createEmail);
            formParam.setPassword(testData.passwordCreate);
            formParam.setBirthDay(testData.birthDay);
            formParam.setPsgdpr(testData.psgdpr);
            formParam.setSubmitCreate(testData.submitCreate);

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
            RequestModels formParam = new RequestModels();
            formParam.setFirstName(testData.firstName);
            formParam.setLastName(testData.lastName);
            formParam.setEmail(testData.email);
            formParam.setPassword(testData.passwordCreate);
            formParam.setPsgdpr(testData.psgdpr);
            formParam.setSubmitCreate(testData.submitCreate);
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

