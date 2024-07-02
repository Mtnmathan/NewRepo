package projectPackage.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class BasePage {
	
	static WebDriver driver;
	static Map<String, Map<String, String>> testData;

	public BasePage(WebDriver driver, Map<String, Map<String, String>> testData) {
        this.driver = driver;
        this.testData = testData;
    }
	
	public static String getURL(String url) {
		driver.get(url);
		return url;
	}
	
	public static void maximizePage() {
		driver.manage().window().maximize();
	}

	public static void impWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public static void selectUsingExcel(WebElement element, String ExcelValue) throws InterruptedException {
	for (String testCase : testData.keySet()) {
		Map<String, String> data = testData.get(testCase);
		element.click();
		Select s = new Select(element);
		s.selectByValue(ExcelValue);
	}
	}
	
	public static void selectUsingIdOnExcel(WebElement element, int countof) {
		for (String testCase : testData.keySet()) {
			Map<String, String> data = testData.get(testCase);
			element.click();
			Select s = new Select(element);
			s.selectByIndex(countof);
		}
	}
	
	public static void enterUsingExcel(WebElement element, String value) throws InterruptedException {
		for (String testCase : testData.keySet()) {
			Map<String, String> data = testData.get(testCase);
			element.click();
			element.sendKeys(value);
		}
	}
	
	public static void selectUsingJSExecutor(WebElement element, String input) {
		for (String testCase : testData.keySet()) {
		Map<String, String> data = testData.get(testCase);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','data')",element);
		}
	}
	
    public static String addScreenshot(WebDriver driver) {
        String resultFile = "";
        Path currentDirPath = Paths.get("");
        String filename = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()); // Note the capital HH for 24-hour format
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String userDir = currentDirPath.toAbsolutePath().toString();
        try {
            // Ensure the "screenshots" directory exists
            File screenshotsDir = new File(userDir + File.separator + "screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            // Generate destination file path
            String destFilePath = screenshotsDir + File.separator + "IMG_" + filename + ".png";
            File destFile = new File(destFilePath);
            
            // Log the path
            resultFile = destFilePath;
            Reporter.log("[Info] Save Image File Path : " + resultFile, true);
            
            // Copy screenshot file
            FileUtils.copyFile(screenshot, destFile);

            // Add screenshot to ExtentReports
            ExtentReportUtil.getTest().addScreenCaptureFromPath(destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFile;
    }
}
