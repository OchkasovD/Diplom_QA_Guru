package web_api.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import web_api.helpers.Attach;
import web_api.pages.AuthorizationPage;
import web_api.pages.MainPage;
import web_api.pages.ProductPage;

import static java.lang.String.format;


public class TestBase {

    static TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();


    @BeforeAll
    static void beforeUrl() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://shop1.emagazin.info");
        Configuration.baseUrl = System.getProperty("baseURL", "https://shop1.emagazin.info");
        Configuration.browser = System.getProperty("browser", "firefox");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
