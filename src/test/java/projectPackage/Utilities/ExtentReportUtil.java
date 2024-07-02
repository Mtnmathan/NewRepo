package projectPackage.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static void initReport(String reportFilePath) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }

    public static void logInfo(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.info(message);
        } else {
            System.out.println("[DEBUG] ExtentTest is null for thread: " + Thread.currentThread().getId());
        }
    }

    public static void logPass(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.pass(message);
        } else {
            System.out.println("[DEBUG] ExtentTest is null for thread: " + Thread.currentThread().getId());
        }
    }

    public static void logFail(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.fail(message);
        } else {
            System.out.println("[DEBUG] ExtentTest is null for thread: " + Thread.currentThread().getId());
        }
    }

    public static void flushReport() {
        extent.flush();
    }

    public static void addLogger(String log) {
        extent.addTestRunnerOutput(log + "<br/>" + "<br/>");
    }

    public static void addScreenshot(WebDriver driver) throws IOException {
        String screenshotPath = BasePage.addScreenshot(driver);
        try {
            ExtentTest test = getTest();
            if (test != null) {
                test.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                System.out.println("[DEBUG] ExtentTest is null for thread: " + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
