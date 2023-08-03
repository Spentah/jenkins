package com.example.demo1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import listener.AfterTestListener;
import listener.SuiteListener;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

import static org.testng.Assert.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Listeners(AfterTestListener.class)
public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide(Allure.getLifecycle()));
    }

    @BeforeMethod
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--remote-allow-origins=*")
                .addArguments("--headless") //!!!should be enabled for Jenkins
                .addArguments("--disable-dev-shm-usage") //!!!should be enabled for Jenkins
                .addArguments("--window-size=1920x1080");
//        DriverFactory.setDriver();
        open("https://www.jetbrains.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws FileNotFoundException {
        // Проверка результата теста
        if (result.getStatus() == ITestResult.FAILURE) {
            // Создание скриншота
            String screenshotPath = Screenshots.takeScreenShotAsFile().getPath();
            // Прикрепление скриншота к Allure-отчету
            ScreenShooter.captureSuccessfulTests = true;
            Allure.addAttachment("Скриншот", new FileInputStream(screenshotPath));
        }
    }

    @Test
    public void search() {
                // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        DriverFactory.setDriver();
        open("https://www.jetbrains.com/");

        mainPage.searchButton.click();

        $("[data-test='search-input']").sendKeys("Selenium");
        $("button[data-test='full-search-button']").click();

        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

        assertEquals(Selenide.title(), "All Developerw Tools and Products by JetBrains");
    }

//    @Test
//    public void t() {
//        String testUuid = UUID.randomUUID().toString();
//        String stepUuid = UUID.randomUUID().toString();
//        AllureLifecycle lifecycle = Allure.getLifecycle();
//        lifecycle.scheduleTestCase(new TestResult().setUuid(testUuid));
//        lifecycle.startTestCase(testUuid);
//        lifecycle.startStep(testUuid, stepUuid, new StepResult().setName("Тесты не стартанули").setStatus(Status.FAILED));
//        attach("e.getMessage().getBytes()".getBytes());
//        lifecycle.stopStep(stepUuid);
//        lifecycle.stopTestCase(testUuid);
//        lifecycle.writeTestCase(testUuid);
//
//    }
//
//    private void attach(byte[] file) {
//        Allure.getLifecycle().addAttachment("stack-trace.txt", "text/plain", "txt", file);
//    }

}
