package web_api.pages;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    public void checkAuthorizationUser() {
        open("/index.php?controller=identity");
        $("[name=firstname]").shouldHave(value("Dmytry"));
    }
}
