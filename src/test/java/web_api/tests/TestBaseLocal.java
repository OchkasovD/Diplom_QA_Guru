package web_api.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseLocal {

    String login = "loca@testmail.ru",
            password = "qatest";

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://shop1.emagazin.info";
        RestAssured.baseURI = "https://shop1.emagazin.info";
        Configuration.browser = System.getProperty("browser", "firefox");
    }
}
