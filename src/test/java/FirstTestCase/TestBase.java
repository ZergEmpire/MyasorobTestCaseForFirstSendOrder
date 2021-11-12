package FirstTestCase;

import com.codeborne.selenide.*;
import com.codeborne.selenide.conditions.Visible;
import com.codeborne.selenide.junit.TextReport;
import com.google.sitebricks.client.Web;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    WebDriver driver;
    private static final String PHONE_NUMBER_LOGIN = "+79969797537";
    private static final String TEST_NAME = "Test";
    private static final String TEST_EMAIL_ADRESS = "test@test.ru";
    private static final String TEST_PHONE_NUMBER_LOGIN = "+77777777777";
    private static final String ADRESS_STREET = "улица Говорова, Одинцово";
    private static final String ADRESS_HOME = "85";
    private static final String ORDER_COMMENT = "Тест. Не готовить";
    private static final String HOW_MONEY_TO_COURIER = "5000";
    private static final String HOW_MANY_USERS = "3";



    @Before
    public void setUp() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("videoName", "MyasorubTestCaseForFirstSendOrder.mp4");
        capabilities.setCapability("name", "MyasorubTestCaseForFirstSendOrder");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "91.0");

        capabilities.setCapability("moon:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true


        ));
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.17:30901/wd/hub"),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);

    }

    @After
    public void end() {
        closeWebDriver();
    }

    @Rule
    public TextReport report = new TextReport();

    @Step("Открываю сайт")
    public void openURL() {
        open("https://myasoroob.ru/");
    }

    @Step("Выбираю рандомный город. Выбор городов реализован через Drop Down")
    public void restSelect() {
        List<SelenideElement> terminalSwitch = elements(By.xpath("//div[contains(@class, \"desktop\")]/div [@class = \"top-rest-select\"]/ul[@class = \"dropdown\"]/li/a[contains(text(), \"\") and not (text() = \" Благовещенск \") and not (text() = \" Краснодар \")]"));
        int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();
    }

    @Step("Перехожу в рандомный пункт меню для оформления тестового заказа")
    public void mathRandomHead() {
        /* List<SelenideElement> mathRandomHead = elements(By.xpath("//a[@href= \"/menu/frityur\"]")); *//*and not (contains(@href, "/menu/sousy"*//*
        int i = (int) (Math.random() * mathRandomHead.size());
        mathRandomHead.get(i).click(); */
       /* $(By.xpath("//a[@href= \"/menu/frityur\"]")).click();*/
        List<SelenideElement> mathRandomHead = elements(By.xpath("//a[contains(@class, \"scroll-nav_link\") and not (@href = \"/menu/sousy\")]"));
        int i = (int) (Math.random() * mathRandomHead.size());
        mathRandomHead.get(i).click();
    }

    @Step("Добавляем в корзину карточку товара(Картофель Фри)")
    public void pickRandCards() {
       /* $x("//a[@href = \"/product/kartofel-fri\"]").click();
        SelenideElement addToBasket = $x("//a[contains(@class, \"add-to-basket\")]").shouldBe(text("В корзину"));
        addToBasket.click();*/
        List<SelenideElement> clickRandomCards = elements(By.xpath("//div[contains(@class, \"productBox\") and not(contains(@class, \"in-stop-list\")) and not(contains(@class, \"action-wrapper\"))]//button[contains(@class, \"add-to-basket\") or (contains(text(), \"В корзину\"))]"));
        int i = (int) (Math.random() * clickRandomCards.size());
        SelenideElement randCard = $(clickRandomCards.get(i));
        randCard.closest(".item").scrollIntoView(true);
        randCard.click();
    }

    @Step("Переходим в корзину")
    public void goBasket() {
        /*        $(By.xpath("//a[contains(@class, \"btn-basket\")]")).click();*/
        $x("//a[contains(@class, \"btn-basket\")]").click();
    }

    @Step("Выбираем тип доставки самовывоз")
    public void selectDeliveryTypePickUp() {
        SelenideElement pickUp = $x("//label[@class = \"last\"]");
        System.out.println(pickUp);
        pickUp.closest(".address-summary").scrollIntoView(false);
        pickUp.click();
    }


    @Step("Выбираем пункт самовывоза")
    public void selectTerminalForPickUp() {
        List<SelenideElement> options = elements(By.xpath("//select[@id = \"order_terminal-no-shipment\"]/option"));
        int i = (int) (Math.random() * options.size());
        options.get(i).click();
    }

    @Step("Заполняем поля")
    public void fillInFields() {
        $x("//input[@id = \"order_name\"]").scrollTo().setValue(TEST_NAME);
        $x("//input[@id = \"order_phone\"]").scrollTo().setValue(TEST_PHONE_NUMBER_LOGIN);
        $x("//input[@id = \"order_email\"]").scrollTo().setValue(TEST_EMAIL_ADRESS);
        $x("//input[@id = \"order_comment\"]").scrollTo().setValue(ORDER_COMMENT);
        $x("//input[@id = \"order_person-count\"]").scrollTo().setValue(HOW_MANY_USERS);
    }

    @Step("Выбираем способ оплаты")
    public void selectPayType() {
        SelenideElement CHANGE_INPUT = $("#change");
        $(CHANGE_INPUT).closest(".pay-method").scrollIntoView(false).click();
        $(CHANGE_INPUT).setValue(HOW_MONEY_TO_COURIER);
    }

    @Step("Тыкаем на отправку заказа")
    public void sendOrder() {
        $x("//div[@class = \"item-cart-buttons\" ]/button[contains(@class, \"btn\") and not (@id)]").scrollTo().click();
    }
@Step("Ждём перехода в статус принят")
    public void waitForComplete() {
        $x("//span[contains(text(),'Принят') or (contains(text(),'Поступил')) ]").shouldBe(visible);
    }


}


