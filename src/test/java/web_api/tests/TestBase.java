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


public class TestBase {

    static TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void beforeUrl() {
        RestAssured.baseURI = System.getProperty("baseURL", "https://shop1.emagazin.info");
        Configuration.baseUrl = System.getProperty("baseURL", "https://shop1.emagazin.info");
        String[] browserAndVersion = System.getProperty("browser", "chrome:100.0").split(":");
        Configuration.browser = browserAndVersion[0];
        Configuration.browserVersion = browserAndVersion[1];
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = String.format("https://%s:%s@%s",
                System.getProperty("selenoidLogin", "user1"),
                System.getProperty("selenoidPassword", "1234"),
                System.getProperty("selenoidUrl", "https://selenoid.autotests.cloud/wd/hub").replace("https://", "")
        );

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
