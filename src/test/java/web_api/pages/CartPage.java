package web_api.pages;

import web_api.data.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartPage {
    static TestData testData = new TestData();

    public void checkAddToCart() {
        open("/index.php?controller=cart&action=show");
        $("[href=\"https://shop1.emagazin.info/index.php?id_product=23&rewrite=vivomove-style-s-grafitovym-korpusom-i-chyornym-remeshkom&controller=product\"]").click();

    }

    public void checkAddToCartAuthUser() {
        open("/index.php?controller=cart&action=show");
        $("[href=\"https://shop1.emagazin.info/index.php?id_product=23&rewrite=vivomove-style-s-grafitovym-korpusom-i-chyornym-remeshkom&controller=product\"]").click();
        $("[href=\"https://shop1.emagazin.info/index.php?mylogout=\"]").click();
        $("[href=\"https://shop1.emagazin.info/index.php?controller=identity\"]").click();
        $("[name=firstname]").shouldHave(value(testData.userName));
    }

    public void checkAddToCartWeb() {
        $("#header .cart-products-count").shouldHave(text("1"));
        $("[href=\"//shop1.emagazin.info/index.php?controller=cart&action=show\"]").click();
        $("[href=\"https://shop1.emagazin.info/index.php?id_product=16&id_product_attribute=28&rewrite=mountain-fox-notebook&controller=product#/22-paper_type-ruled\"]").click();
    }
}