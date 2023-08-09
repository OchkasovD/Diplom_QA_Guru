package web_api.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web_api.data.ApiModels;
import web_api.data.TestData;
import web_api.models.RequestModels;
import web_api.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static web_api.specs.Specifications.*;


public class ShopEmagazinUserRegistrationApiTests extends TestBase {
    static TestData testData = new TestData();
    MainPage mainPage = new MainPage();


    @Test
    @Tag("API")
    @Owner("Ochkasov_D")
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
    @Owner("Ochkasov_D")
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
                    .spec(responseSpec200);
        });
    }


}

