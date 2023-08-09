package web_api.data;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import java.text.SimpleDateFormat;
import web_api.config.ConfigProperties;
public class TestData {

    ConfigProperties config = ConfigFactory.create(ConfigProperties.class, System.getProperties());

    public String menuClothes = "Одежда";
    public String menuRelatedProd = "Сопутствующие товары";
    public String menuPaintings = "Картины";
    public String nameTitleCard = "Mountain fox notebook";
    public String email = config.getLogin();
    public String password = config.getPassword();
    public String back = "my-account";
    public String submitLogin = "1";
    public String authCookieKey = "PrestaShop-5eb24e794c8e2bb6adc3500d9af027ad";
    public String authCookieValue;
    public String id_product = "23";
    public String controller = "cart";
    public String token = "a6de737f97fedf0aa13757c5bfd32778";
    public String id_customization = "0";
    public String qty = "1";
    public String add = "1";
    public String action = "update";
    public String userName = "Dmytry";
    public String invalidEmail = config.getInvalidLogin();
    public String invalidPassword = config.getInvalidPassword();

    public Integer countElements = 11;
    public int RELATEDELEMENTS = 11;

    Faker faker = new Faker();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public String firstName = faker.funnyName().name();
    public String lastName = faker.name().lastName();
    public String createEmail = faker.internet().safeEmailAddress();
    public String passwordCreate = faker.internet().password();

    public String birthDay = sdf.format(faker.date().birthday());

    public String genderId = "1";
    public String psgdpr = "1";
    public String submitCreate = "1";
}
