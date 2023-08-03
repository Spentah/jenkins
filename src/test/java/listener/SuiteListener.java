package listener;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.UUID;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Listener has started...");
//        try {
//            Assert.fail("Fail on start...");
//        } catch (Exception e) {
//            String testUuid = UUID.randomUUID().toString();
//            String stepUuid = UUID.randomUUID().toString();
//            AllureLifecycle lifecycle = Allure.getLifecycle();
//
//            lifecycle.scheduleTestCase(new TestResult().setUuid(testUuid));
//            lifecycle.startTestCase(testUuid);
//            lifecycle.startStep(testUuid, stepUuid, new StepResult().setName("Тесты не стартанули").setStatus(Status.FAILED));
//            attach("e.getMessage().getBytes()".getBytes());
//            lifecycle.stopStep(stepUuid);
//            lifecycle.stopTestCase(testUuid);
//            lifecycle.writeTestCase(testUuid);
//        }

    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Listener has finished");
    }

    private void attach(byte[] file) {
        Allure.getLifecycle().addAttachment("stack-trace.txt", "text/plain", "txt", file);
    }
}
