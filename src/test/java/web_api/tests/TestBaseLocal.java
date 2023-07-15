package web_api.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import web_api.config.CredentialsConfig;
import web_api.helpers.Attach;
import web_api.pages.AuthorizationPage;
import web_api.pages.MainPage;
import web_api.pages.ProductPage;

import java.util.Map;

import static java.lang.String.format;


public class TestBaseLocal {

    static TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    String login = "locat@testmail.ru",
            password = "99990000";
    @BeforeAll
    static void beforeUrl() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://shop1.emagazin.info");
        Configuration.baseUrl = System.getProperty("baseURL", "https://shop1.emagazin.info");
        Configuration.browser = System.getProperty("browser", "firefox");
        }



    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
