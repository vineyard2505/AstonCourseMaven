import lombok.Data;
import org.example.MtsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Data
public class MtsPageTest {
    public WebDriver driver;
    public MtsPage mtsPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.get("https://www.mts.by/");
        mtsPage = new MtsPage(driver);
        mtsPage.acceptCookies();
    }

    @Test
    @Order(1)
    @DisplayName("Проверка валидного названия блока")
    public void testTitle() {
        assertEquals("Онлайн пополнение без комиссии", mtsPage.getBlockTitle(), "Название не соответствует ожидаемому.");
    }

    @Test
    @Order(2)
    @DisplayName("Проверка логотипов")
    public void testLogo() {
        List<WebElement> logos = mtsPage.getLogos();
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Один из логотипов не отображается.");
            String src = logo.getAttribute("src");
            assertTrue(src.contains("/local/templates/new_design/assets/html/images/pages/index/pay/"), "Источник не соответствует");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Проверка ссылки")
    public void testLink() {
        mtsPage.clickLink();
        String expectedTitle = "Порядок оплаты и безопасность интернет платежей";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Заголовок страницы не соответствует ожидаемому");
    }

    @Test
    @Order(4)
    @DisplayName("Проверка кнопки - Продолжить")
    public void testButton() {
        mtsPage.getPhoneSum("297777777", "1");
        mtsPage.clickButton();
        mtsPage.isBlockVisible();
    }

    @Test
    @Order(5)
    @DisplayName("Проверка плейсхолдеров в блоке - Услуги связи")
    public void testPlaceholderPayConnection() {
        assertEquals("Номер телефона", mtsPage.getPlaceholder(mtsPage.getConnectionPhone()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("Сумма", mtsPage.getPlaceholder(mtsPage.getConnectionSum()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("E-mail для отправки чека", mtsPage.getPlaceholder(mtsPage.getConnectionEmail()), "Плейсхолдер не соответствует ожидаемому");
    }

    @Test
    @Order(6)
    @DisplayName("Проверка плейсхолдеров в блоке - Домашний интернет")
    public void testPlaceholderPayInternet() {
        assertEquals("Номер абонента", mtsPage.getPlaceholder(mtsPage.getInternetPhone()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("Сумма", mtsPage.getPlaceholder(mtsPage.getInternetSum()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("E-mail для отправки чека", mtsPage.getPlaceholder(mtsPage.getInternetEmail()), "Плейсхолдер не соответствует ожидаемому");
    }

    @Test
    @Order(7)
    @DisplayName("Проверка плейсхолдеров в блоке - Рассрочка")
    public void testPlaceholderPayInstalment() {
        assertEquals("Номер счета на 44", mtsPage.getPlaceholder(mtsPage.getScoreInstalment()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("Сумма", mtsPage.getPlaceholder(mtsPage.getInstalmentSum()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("E-mail для отправки чека", mtsPage.getPlaceholder(mtsPage.getInstalmentEmail()), "Плейсхолдер не соответствует ожидаемому");
    }
    @Test
    @Order(8)
    @DisplayName("Проверка плейсхолдеров в блоке - Задолженность")
    public void testPlaceholderPayArrears() {
        assertEquals("Номер счета на 2073", mtsPage.getPlaceholder(mtsPage.getScoreArrears()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("Сумма", mtsPage.getPlaceholder(mtsPage.getArrearsSum()), "Плейсхолдер не соответствует ожидаемому");
        assertEquals("E-mail для отправки чека", mtsPage.getPlaceholder(mtsPage.getArrearsEmail()), "Плейсхолдер не соответствует ожидаемому");
    }

    @Test
    @Order(9)
    @DisplayName("Проверка введеных данных в фрейме")
    public void testDataFrame() {
        mtsPage.switchToFrame(mtsPage.getFrameLocator());
        assertTrue(mtsPage.getTextSum(mtsPage.getSumText()).contains("1.00 BYN"), "Сумма не соответствует");
        assertTrue(mtsPage.getTextSum(mtsPage.getSumButton()).contains("1.00 BYN"), "Сумма не соответствует");
        assertTrue(mtsPage.getTextSum(mtsPage.getPhoneFrame()).contains("375297777777"), "Номер телефона не соответствует");
    }

    @Test
    @Order(10)
    @DisplayName("Проверка надписей в реквизитах")
    public void testLabels() {
        mtsPage.switchToFrame(mtsPage.getFrameLocator());
        assertEquals("Номер карты", mtsPage.getLabel(mtsPage.getCardLabel()), "Надпись не соответствует ожидаемому");
        assertEquals("Срок действия", mtsPage.getLabel(mtsPage.getDateLabel()), "Надпись не соответствует ожидаемому");
        assertEquals("CVC", mtsPage.getLabel(mtsPage.getCvcLabel()), "Надпись не соответствует ожидаемому");
        assertEquals("Имя держателя (как на карте)", mtsPage.getLabel(mtsPage.getNameLabel()), "Надпись не соответствует ожидаемому");
    }

    @Test
    @Order(11)
    @DisplayName("Проверка наличия иконок в фрейме")
    public void testIconsFrame() {
        List<WebElement> icons = mtsPage.getIconsFrame();
        mtsPage.switchToFrame(mtsPage.getFrameLocator());
        for (WebElement icon : icons) {
            assertTrue(icon.isDisplayed(), "Один из логотипов не отображается.");
            String src = icon.getAttribute("src");
            assertTrue(src.contains("assets/images/payment-icons/card-types/"), "Источник не соответствует");
        }
    }

    @AfterEach
    public void setDown() {
        driver.quit();
    }
}
