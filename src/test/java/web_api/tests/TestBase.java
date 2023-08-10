package web_api.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import web_api.config.RemoteConfig;
import web_api.helpers.Attach;

import java.util.Map;


public class TestBase {
    static RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class, System.getProperties());
    static boolean isRemote = Boolean.getBoolean("isRemote");

    @BeforeAll
    static void beforeUrl() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://shop1.emagazin.info");
        Configuration.baseUrl = System.getProperty("baseURL", "https://shop1.emagazin.info");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        Configuration.pageLoadStrategy = "eager";

        if (remoteConfig.remoteURL() != null && remoteConfig.passwordRemote() != null && remoteConfig.loginRemote() != null) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub", remoteConfig.loginRemote(), remoteConfig.passwordRemote(), remoteConfig.remoteURL());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        if (isRemote) {
            Attach.addVideo();
        }
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
    }
}