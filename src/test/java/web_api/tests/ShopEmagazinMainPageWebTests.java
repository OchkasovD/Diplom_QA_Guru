package web_api.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web_api.data.TestData;
import web_api.pages.CartPage;
import web_api.pages.MainPage;
import web_api.pages.ProductPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopEmagazinMainPageWebTests extends TestBase {
    static TestData testData = new TestData();
    CartPage cartPage = new CartPage();

    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();


    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test main page title 'shop1.emagazin.info'")
    void checkTitleTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Проверяем, что титульная часть содержит текст 'Демо-магазин'", () -> {
            String expectedTitle = "Демо-магазин";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }


    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test navigation panel, elements navigation")
    void checkMenuElementsTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Проверяем, переход по элементу 'Одежда'", () ->
                mainPage.openNavigationMenu(testData.menuClothes));

        step("Проверяем, переход по элементу 'Сопутствующие товары'", () ->
                mainPage.openNavigationMenu(testData.menuRelatedProd));

        step("Проверяем, переход по элементу 'Картины'", () ->
                mainPage.openNavigationMenu(testData.menuPaintings));
    }

    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test number of goods")
    void checkRelatedProductsElementsTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () ->
                mainPage.openNavigationMenu(testData.menuRelatedProd));

        step("Проверка кол-во элементов в каталоге 'Сопутствующие товары'", () -> {
            productPage.catalogSize(testData.countElements);
            assertThat(testData.countElements).isEqualTo(testData.RELATEDELEMENTS);
        });
    }

    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test open card product")
    void checkCardProductTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары", () ->
                mainPage.openNavigationMenu(testData.menuRelatedProd));

        step("Проверка открытия карточка товара 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
            String expectedTitle = testData.nameTitleCard;
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("WEB")
    @Owner("Ochkasov_D")
    @DisplayName("Test add item to cart")
    void checkAddBasketTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () ->
                mainPage.openNavigationMenu(testData.menuRelatedProd));

        step("Открытия карточки товара 'Сопутствующие товары", () ->
                productPage.openDetailCard(testData.nameTitleCard));

        step("Добавление товара в корзину", () ->
                productPage.addToCardProduct());

        step("Проверка наличия товара в корзине'", () ->
                cartPage.checkAddToCartWeb());


    }

}

