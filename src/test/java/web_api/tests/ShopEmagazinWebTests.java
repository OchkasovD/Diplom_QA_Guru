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

    @Test
    @Tags({@Tag("WEB"), @Tag("Regress")})
    @DisplayName("Check main page title 'shop1.emagazin.info'")
    void checkTitleTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Check, title name page contains 'Демо-магазин'", () -> {
            String expectedTitle = "Демо-магазин";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }


    @Test
    @Tags({@Tag("WEB"), @Tag("Regress")})
    @DisplayName("Check navigation panel elements transition")
    void checkMenuElementsTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Check, transition element 'Одежда'", () -> {
            mainPage.navigationMenu(testData.menuClothes);
        });

        step("Check, transition element 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Check, transition element 'Картины'", () -> {
            mainPage.navigationMenu(testData.menuPaintings);
        });
    }

    @Test
    @Tags({@Tag("WEB"), @Tag("Regress")})
    @DisplayName("Check number of goods")
    void checkRelatedProductsElementsTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Transition element 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Check number of goods in 'Сопутствующие товары'", () -> {
            productPage.catalogSize(testData.countElements);
            assertThat(testData.countElements).isEqualTo(testData.RELATEDELEMENTS);
        });
    }

    @Test
    @Tags({@Tag("WEB"), @Tag("Regress")})
    @DisplayName("Check open card product")
    void checkCardProductTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("Transition element 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Check open card product 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
            String expectedTitle = testData.nameTitleCard;
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tags({@Tag("WEB"), @Tag("Regress")})
    @DisplayName("Check add item to cart")
    void checkAddBasketTest() {
        step("Open the main page", ()
                -> open(baseUrl));

        step("transition element 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Open card product 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
        });

        step("Add item to cart", () -> {
            productPage.addToCardProduct();
        });
    }

}

