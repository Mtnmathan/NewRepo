package projectPackage.myProject;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import projectPackage.Pages.BookingPage;
import projectPackage.Pages.LoginPage;
import projectPackage.Pages.PaymentPage;
import projectPackage.Utilities.*;

import java.time.Duration;
import java.util.Map;

public class BookingTest {
    static WebDriver driver;
    Map<String, Map<String, String>> testData;

    @BeforeSuite
    public void setUp() {
        ExtentReportUtil.initReport("extent.html");
        testData = DataReader.readExcelData("D:\\Assignment parabank\\BuildFrame\\src\\main\\java\\TestData\\Test data doublerow.xlsx");
    }

    @BeforeMethod
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void bookHotelRoom() throws InterruptedException {
        LoginPage homePage = new LoginPage(driver);
        BookingPage bookingPage = new BookingPage(driver, testData);
        PaymentPage paymentPage = new PaymentPage(driver, testData);
        homePage.loginToBooking();
        BookingPage.bookingMethod();
//        paymentPage.paymentMethod();
}


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void cleanUp() {
        ExtentReportUtil.flushReport();
    }
}
