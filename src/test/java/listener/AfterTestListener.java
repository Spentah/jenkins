package listener;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AfterTestListener implements ITestListener {

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] makeScreenshotOnFailure() {
        return Screenshots.takeScreenShotAsFile().toString().getBytes();
//        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("MAKING SCREENSHOT...");
//        String screenshotPath = Screenshots.takeScreenShotAsFile().getPath();
////            // Прикрепление скриншота к Allure-отчету
////        ScreenShooter.captureSuccessfulTests = true;
//        try {
//            Allure.addAttachment("Скриншот", new FileInputStream(screenshotPath));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Allure.addAttachment(result.getName(), new ByteArrayInputStream(makeScreenshotOnFailure()));
//        makeScreenshotOnFailure();
    }
}
