package web_api.pages;

import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    ElementsCollection topMenuElements = $$("#top-menu .dropdown-item");

    public MainPage openUserLoginPage() {
        $("[href=\"https://shop1.emagazin.info/index.php?controller=my-account\"]").click();
        return this;
    }

    public MainPage openMinContent() {
        open("/img/demo-magazin-logo-1590349645.jpg");
        return this;
    }

    public MainPage openNavigationMenu(String value) {
        topMenuElements.find(text(value)).click();
        return this;
    }


}

