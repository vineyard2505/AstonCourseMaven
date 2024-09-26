package org.example;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

@Data
public class MtsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cookieButton = By.xpath("//button[@id='cookie-agree']");
    private By blockTitle = By.xpath("//h2[contains(., 'Онлайн пополнение') and contains(., 'без комиссии')]");
    private By logos = By.xpath("//div[@class='pay__partners']//img");
    private By iconsFrame = By.xpath("//div[@class='card-page__card']//img");
    private By link = By.xpath("//div/a[text()='Подробнее о сервисе']");
    private By inputPhone = By.xpath("//div/input[@id='connection-phone']");
    private By inputSum = By.xpath("//div/input[@id='connection-sum']");
    private By inputButton = By.xpath("//form[@id='pay-connection']//button");
    private By block = By.xpath("//div[@class='bepaid-app']");
    private By frameLocator = By.xpath("//iframe[@class='bepaid-iframe']");
    private By sumText = By.xpath("//span[text()='1.00 BYN']");
    private By sumButton = By.xpath("//button[@type='submit']");
    private By phoneFrame = By.xpath("//span[contains(text(),'375297777777')]");
    private By cardLabel = By.xpath("//label[text()='Номер карты']");
    private By dateLabel = By.xpath("//label[text()='Срок действия']");
    private By nameLabel = By.xpath("//label[text()='Имя держателя (как на карте)']");
    private By cvcLabel = By.xpath("//label[text()='CVC']");
    private By connectionPhone = By.id("connection-phone");
    private By connectionSum = By.id("connection-sum");
    private By connectionEmail = By.id("connection-email");
    private By internetPhone = By.id("internet-phone");
    private By internetSum = By.id("internet-sum");
    private By internetEmail = By.id("internet-email");
    private By scoreInstalment = By.id("score-instalment");
    private By instalmentSum = By.id("instalment-sum");
    private By instalmentEmail = By.id("instalment-email");
    private By scoreArrears = By.id("score-arrears");
    private By arrearsSum = By.id("arrears-sum");
    private By arrearsEmail = By.id("arrears-email");

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void acceptCookies() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='cookie-agree']")));
            cookieButton.click();
        } catch (TimeoutException e) {
            System.out.println("Кнопка согласия с куками не появилась");
        }
    }

    public String getBlockTitle() {
        return driver.findElement(blockTitle).getText().replace("\n", " ");
    }

    public List<WebElement> getLogos() {
        return driver.findElements(logos);
    }

    public List<WebElement> getIconsFrame() {
        return driver.findElements(iconsFrame);
    }

    public void clickLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(link)).click();
    }

    public void getPhoneSum(String phone, String sum) {
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputSum).sendKeys(sum);
    }

    public void clickButton() {
        driver.findElement(inputButton).click();
    }

    public void isBlockVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(block));
    }

    public String getPlaceholder(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }

    public String getTextSum(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void switchToFrame(By frameLocator) {
        getPhoneSum("297777777", "1");
        clickButton();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public String getLabel(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}