package web_api.pages;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.openqa.selenium.By;
import web_api.data.TestData;

public class AuthorizationPage {
    static TestData testData = new TestData();

    public void checkAuthorizationUser() {
        open("/index.php?controller=identity");
        $("[name=firstname]").shouldHave(value(testData.userName));
    }

    public void checkUnsuccessfulAuthorizationUser() {
        open("/index.php?controller=authentication&back=identity");
        $("[href=\"https://shop1.emagazin.info/index.php?controller=authentication&create_account=1\"]").shouldHave(text("Нет учётной записи? Создайте её здесь"));

    }

    public AuthorizationPage InputLoginUser() {
                $(By.name("email")).setValue(testData.email);
                $(By.name("password")).setValue(testData.password);
                $(By.id("submit-login")).click();
                return this;
            }
    public AuthorizationPage InputInvalidLoginUser() {
        $(By.name("email")).setValue(testData.invalidEmail);
        $(By.name("password")).setValue(testData.invalidPassword);
        $(By.id("submit-login")).click();
        return this;


    }
}
