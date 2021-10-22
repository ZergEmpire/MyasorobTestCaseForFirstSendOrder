package FirstTestCase;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import static com.codeborne.selenide.Selenide.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class TestClass extends TestBase {

    @Test
    public void userCanSendOrder() {
        openURL();
        restSelect();
        goBasket();
        selectDeliveryTypePickUp();
        selectTerminalForPickUp();
        mathRandomHead();
        pickRandCards();
        goBasket();
        fillInFields();
        selectPayType();
        sendOrder();
        waitForComplete();
    }
}