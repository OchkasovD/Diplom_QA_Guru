package web_api.tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopEmagazinWebTests extends TestBase {
    private  TestData testData = new TestData();
    @Test
    @Tag("WEB")
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
    @DisplayName("Test navigation panel, elements navigation")
    void checkMenuElementsTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Проверяем, переход по элементу 'Одежда'", () -> {
            mainPage.navigationMenu(testData.menuClothes);
        });

        step("Проверяем, переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверяем, переход по элементу 'Картины'", () -> {
            mainPage.navigationMenu(testData.menuPaintings);
        });
    }

    @Test
    @Tag("WEB")
    @DisplayName("Test number of goods")
    void checkRelatedProductsElementsTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка кол-во элементов в каталоге 'Сопутствующие товары'", () -> {
            productPage.catalogSize(testData.countElements);
            assertThat(testData.countElements).isEqualTo(testData.RELATEDELEMENTS);
        });
    }

    @Test
    @Tag("WEB")
    @DisplayName("Test open card product")
    void checkCardProductTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка открытия карточка товара 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
            String expectedTitle = testData.nameTitleCard;
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("WEB")
    @DisplayName("Test add item to cart")
    void checkAddBasketTest() {
        step("Открываем главную страницу", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Открытия карточки товара 'Сопутствующие товары", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
        });

        step("Добавление товара в корзину", () -> {
            productPage.addToCardProduct();
        });
    }

}

