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
import web_api.pages.CartPage;
import web_api.pages.MainPage;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static web_api.specs.Specifications.*;


public class ShopEmagazinAddToCartApiTests extends TestBase {

    static TestData testData = new TestData();

    CartPage cartPage = new CartPage();
    MainPage mainPage = new MainPage();


    @Test
    @Tag("API")
    @Owner("Ochkasov_D")
    @DisplayName("Test add to cart Anonymous")
    void addToCartAnonymousTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());
        step("API add to cart", () -> {
            RequestModels formParam = ApiModels.apiAddToCartRequestModels(testData);
            testData.authCookieValue = given()
                    .spec(requestSpec)
                    .queryParam("controller", formParam.getController())
                    .formParam("token", formParam.getToken())
                    .formParam("id_product", formParam.getId_product())
                    .formParam("id_customization", formParam.getId_customization())
                    .formParam("qty", formParam.getQty())
                    .formParam("add", formParam.getAdd())
                    .formParam("action", formParam.getAction())
                    .when()
                    .post("/index.php?controller=cart")
                    .then()
                    .spec(responseSpec200)
                    .extract()
                    .cookie(testData.authCookieKey);
        });

        step("Check UI add to cart", () ->
            cartPage.checkAddToCart());
    }

    @Test
    @Tag("API")
    @Owner("Ochkasov_D")
    @DisplayName("Test add to cart Login")
    void addToCartLoginTest() {
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

        step("API add to cart", () -> {
            RequestModels formParam = ApiModels.apiAddToCartRequestModels(testData);
            testData.authCookieValue = given()
                    .spec(requestSpec)
                    .queryParam("controller", formParam.getController())
                    .formParam("token", formParam.getToken())
                    .formParam("id_product", formParam.getId_product())
                    .formParam("id_customization", formParam.getId_customization())
                    .formParam("qty", formParam.getQty())
                    .formParam("add", formParam.getAdd())
                    .formParam("action", formParam.getAction())
                    .when()
                    .post("/index.php?controller=cart")
                    .then()
                    .spec(responseSpec200)
                    .extract()
                    .cookie(testData.authCookieKey);
        });

        step("Check UI add to cart with authorization", () ->
            cartPage.checkAddToCartAuthUser());

    }
}


