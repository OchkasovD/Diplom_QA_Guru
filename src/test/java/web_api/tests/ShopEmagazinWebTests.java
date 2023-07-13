package web_api.tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopEmagazinWebTests extends TestBase {

    @Test
    @DisplayName("Проверка title главной страницы 'shop1.emagazin.info'")
    void checkTitleTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Проверяем, что title название страницы содержит текст 'Демо-магазин'", () -> {
            String expectedTitle = "Демо-магазин";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }


    @Test
    @DisplayName("Проверка перехода по элементам  в навигационной панели")
    void checkMenuElementsTest() {
        step("Открываем главную страницу сайта", ()
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
    @DisplayName("Проверка количества товаров")
    void checkRelatedProductsElementsTest() {
        step("Открываем главную страницу сайта", ()
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
    @DisplayName("Проверка открытия карточка товара")
    void checkCardProductTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
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
    @DisplayName("Проверка добавление товара в корзину")
    void checkAddBasketTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Открытия карточки товара 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
        });

        step("Добавление товара в корзину", () -> {
            productPage.addToCardProduct();
        });
    }

}

