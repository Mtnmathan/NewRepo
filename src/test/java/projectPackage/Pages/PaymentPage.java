package projectPackage.Pages;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import projectPackage.Utilities.Assertions;
import projectPackage.Utilities.BasePage;
import projectPackage.Utilities.ExtentReportUtil;

public class PaymentPage extends BasePage{
	static WebDriver driver;
	static Map<String, Map<String, String>> testData;
	
	
	@FindBy(xpath = "//*[@id='first_name']")
	static WebElement firstName;
	
	@FindBy(id = "last_name")
	static WebElement lastName;
	
	@FindBy(id = "address")
	static WebElement billingAddress;
	
	@FindBy(id = "cc_num")
	static WebElement cardNumber;
	
	@FindBy(id = "cc_type")
	static WebElement cardType;
	
	@FindBy(id = "cc_exp_month")
	static WebElement expMonth;
	
	@FindBy(id = "cc_exp_year")
	static WebElement expYear;
	
	@FindBy(id = "cc_cvv")
	static WebElement cvvNum;
	
	@FindBy(id = "book_now")
	static WebElement booknowBtn;
	
	@FindBy(xpath = "//td[contains(text(),'Book A Hotel ')]")
	static WebElement bookHotelText;
	
	@FindBy(id = "order_no")
	static WebElement orderNumber;

	public PaymentPage(WebDriver driver, Map<String, Map<String, String>> testData) {
		super(driver, testData);
		this.driver=driver;
		this.testData=testData;
		PageFactory.initElements(driver, this);
	}
	
	public static void enterFirstName(String fname) throws InterruptedException {
		firstName.click();
		BasePage.enterUsingExcel(firstName, fname);
	}
	
	public static void enterLastName(String lname) throws InterruptedException {
		BasePage.enterUsingExcel(lastName, lname);
	}
	
	public static void enterAddress(String address) throws InterruptedException {
		BasePage.enterUsingExcel(billingAddress, address);
	}
	
	public static void enterCardNumber(String cnum) throws InterruptedException {
		BasePage.enterUsingExcel(cardNumber, cnum);
	}
	
	public static void selectCardType(String type) throws InterruptedException {
		BasePage.selectUsingExcel(cardType, type);
	}
	
	public static void selectExpiryMonth(String month) throws InterruptedException {
		BasePage.selectUsingExcel(expMonth, month);
	}
	
	public static void selectExpiryYear(String year) throws InterruptedException {
		BasePage.selectUsingExcel(expYear, year);
	}
	
	public static void enterCvvNumber(String cvvnumb) throws InterruptedException {
		BasePage.enterUsingExcel(cvvNum, cvvnumb);
	}
	
	public static String bookHotelText() {
		return bookHotelText.getText();
	}
	
	public static void fetchOrderNumber() {
		String attvalue = orderNumber.getAttribute("innerText");
		System.out.println("The Order Number of the order is "+attvalue);
	}
	
	public static void paymentMethod() throws InterruptedException, NumberFormatException{
		
//		Assertions act = new Assertions(driver);
//		String resultText = bookHotelText.getText();
//		act.assertTextContains("Book A Hotel ", resultText, "Fetched Booking Summary", "Not Fetched Bookin Summary");
		
		
		for (String testCase : testData.keySet()) {
            ExtentReportUtil.createTest(testCase);
            Map<String, String> data = testData.get(testCase);
            
//            ExtentReportUtil.logInfo("Entering First Name: " + data.get("FirstName"));
//            enterFirstName(data.get("FirstName"));
//            
//            ExtentReportUtil.logInfo("Entering Last Name: " + data.get("LastName"));
//            enterLastName(data.get("LastName"));
//            
//            ExtentReportUtil.logInfo("Entering Address: " + data.get("Address"));
//            enterAddress(data.get("Address"));
//            
//            ExtentReportUtil.logInfo("Entering Card Number: " + data.get("CardNumber"));
//            enterCardNumber(data.get("CardNumber"));
            
            ExtentReportUtil.logInfo("Selecting Card Type: " + data.get("CardType"));
            selectCardType(data.get("CardType"));
            
            ExtentReportUtil.logInfo("Selecting Expiry Month: " + data.get("ExpMonth"));
            selectExpiryMonth(data.get("ExpMonth"));
            
            ExtentReportUtil.logInfo("Selecting Expiry Year: " + data.get("ExpYear"));
            selectExpiryYear(data.get("ExpYear"));
            
            ExtentReportUtil.logInfo("Entering cvv: " + data.get("CvvNumber"));
            enterCvvNumber(data.get("CvvNumber"));
            
            booknowBtn.click();
            fetchOrderNumber();
 
		}
	}
	

}
