package web_api.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    public void checkAuthorizationUser() {
        open("https://shop1.emagazin.info/index.php?controller=identity");
        //$("a href['https://shop1.emagazin.info/index.php?controller=identity']").shouldHave(text("Информация")).click();
        $(By.name("firstname")).shouldHave(value("Dmytry"));
    }
}
