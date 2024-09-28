import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class BaseTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        try {
            driver.get("https://www.mts.by");
            WebElement acceptButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
                System.out.println("cookie есть");
            } else {
                System.out.println("cookie не отображается");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    @Test
    public void nameBlockTest(){
        WebElement blockName = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        String expectedTitle = "Онлайн пополнение\n" + "без комиссии";
        assertEquals(blockName.getText(), expectedTitle);
    }

    @Test
    public void checkLogoTest(){
        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']"));
        assertFalse(logos.isEmpty());
    }

    @Test
    public void checkReferenceTest(){
        WebElement moreInfoService = driver.findElement(By.xpath("//div[@class='pay__wrapper']/a"));
        moreInfoService.click();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL не правильный!");
    }

    @Test
    public void checkButtonTest(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement phoneNumber = driver.findElement(By.id("connection-phone"));
        WebElement sum = driver.findElement(By.id("connection-sum"));
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']/button"));
        phoneNumber.sendKeys("297777777");
        sum.sendKeys("100");
        continueButton.click();
    }

    @AfterEach
    public void closeSite(){
        if (driver != null) {
            driver.quit();
        }
    }
}
