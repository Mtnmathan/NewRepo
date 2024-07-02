package projectPackage.Utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Assertions extends BasePage {
    WebDriver driver;

    public Assertions(WebDriver driver) {
        super(driver, testData);
        this.driver = driver;
    }

    public void assertTest(boolean condition, String successMessage, String failureMessage) {
        try {
            if (condition) {
                System.out.println("[Info] Assertion passed: " + successMessage);
                ExtentReportUtil.getTest().pass(successMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
            } else {
                System.out.println("[Info] Assertion failed: " + failureMessage);
                ExtentReportUtil.getTest().fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(failureMessage);
            }
        } catch (Exception ex) {
            try {
                ExtentReportUtil.getTest().fail(failureMessage);
                ExtentReportUtil.addLogger(failureMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void assertTestEquals(String expected, String actual, String successMessage, String failureMessage) {
        try {
            if (expected.equalsIgnoreCase(actual)) {
                SoftAssert sa = new SoftAssert();
                sa.assertEquals(actual, expected, successMessage);
                System.out.println("[Info] Assertion passed - " + successMessage);
                ExtentReportUtil.getTest().pass(successMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
            } else {
                System.out.println("[Info] Assertion failed -  - " + failureMessage);
                ExtentReportUtil.getTest().fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(failureMessage);
            }
        } catch (Exception ex) {
            try {
                ExtentReportUtil.getTest().fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(failureMessage);
            } catch (Exception e) {
                ExtentReportUtil.addLogger(e.getLocalizedMessage());
            }
        }
    }

    public void assertTextContains(String expected, String actual, String successMessage, String failureMessage) {
        try {
            if (actual.trim().equalsIgnoreCase(expected.trim())) {
                SoftAssert sa = new SoftAssert();
                sa.assertTrue(true, successMessage);
                System.out.println("[Info] Assertion passed - " + successMessage);
                ExtentReportUtil.getTest().pass(successMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
            } else {
            	System.out.println("[Info] Assertion failed - " + failureMessage);
                ExtentReportUtil.getTest().fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(failureMessage);
            }
        } catch (Exception ex) {
            try {
            	System.out.println("[Info] Assertion failed - " + failureMessage);
                ExtentReportUtil.getTest().fail(failureMessage, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(failureMessage);
            } catch (Exception e) {
                ExtentReportUtil.addLogger(e.getLocalizedMessage());
            }
        }
    }

    public void assertTestNotEquals(String expected, String actual, String message) {
        try {
            SoftAssert sa = new SoftAssert();
            sa.assertNotEquals(actual, expected, message);
            ExtentReportUtil.getTest().pass(message, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
        } catch (Exception ex) {
            try {
                ExtentReportUtil.getTest().fail(message, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshot(driver)).build());
                ExtentReportUtil.addLogger(message);
            } catch (Exception e) {
                ExtentReportUtil.addLogger(e.getLocalizedMessage());
            }
        }
    }
}
