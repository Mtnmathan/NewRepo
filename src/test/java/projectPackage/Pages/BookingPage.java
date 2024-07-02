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

public class BookingPage extends BasePage {
	static WebDriver driver;
	static Map<String, Map<String, String>> testData;

	@FindBy(id = "location")
	static WebElement locationField;

	@FindBy(id = "hotels")
	static WebElement HotelsSelect;

	@FindBy(id = "room_type")
	static WebElement RoomType;

	@FindBy(id = "room_nos")
	static WebElement roomCount;
	
	@FindBy(id = "datepick_in")
	static WebElement CheckinDate;
	
	@FindBy(id = "datepick_out")
	static WebElement CheckoutDate;
	
	@FindBy(id = "adult_room")
	static WebElement AdultPerPage;
	
	@FindBy(id = "child_room")
	static WebElement ChildPerPage;

	@FindBy(id = "Submit")
	static WebElement searchBtn;
	
	@FindBy(xpath = "//strong[contains(text(),'Total Price (excl. GST) ')]")
	static WebElement totalPriceText;
	
	@FindBy(id = "continue")
	static WebElement contBtn;
	
	@FindBy(id = "radiobutton_0")
	static WebElement radioBtn;
	
	@FindBy(id = "total_price_0")
	static WebElement totalAmount;

	public BookingPage(WebDriver driver, Map<String, Map<String, String>> testData) {
        super(driver, testData);
        this.driver = driver;
        this.testData = testData;
        PageFactory.initElements(driver, this);
    }
	
	public static void selectLocationDif(String loc) throws InterruptedException {
		BasePage.selectUsingExcel(locationField, loc);
	}

	public static void selectHotelNameDif(String hotelName) throws InterruptedException {
		BasePage.selectUsingExcel(HotelsSelect, hotelName);
	}
	
	public static void selectRoomTypeDif(String roomType) throws InterruptedException {
		BasePage.selectUsingExcel(RoomType, roomType);
	}
	
	public static void enterCheckInDateDif(String invalue) throws InterruptedException {
		CheckinDate.clear();
		BasePage.enterUsingExcel(CheckinDate, invalue);
	}
	
	public static void enterCheckOutDateDif(String outvalue) throws InterruptedException {
		CheckoutDate.clear();
		BasePage.enterUsingExcel(CheckoutDate, outvalue);
	}
		
	public static void enterRoomCountDif(int room) {
		BasePage.selectUsingIdOnExcel(roomCount, room);
	}
	
	public static void enterAdultCountDif(int room) {
		BasePage.selectUsingIdOnExcel(AdultPerPage, room);
	}
	
	public static void enterChildCountDif(int room) {
		BasePage.selectUsingIdOnExcel(ChildPerPage, room);
	}
		
	public static void clickSubmitButton() {
		searchBtn.click();
	}
	
	public static String returnPageText() {
		String resultText = totalPriceText.getText();
		return resultText;
	}
	
	public static void totalAmount() {
		String price = totalAmount.getText();
		addScreenshot(driver);
	}
	
	
	public static void bookingMethod() throws InterruptedException, NumberFormatException{
		for (String testCase : testData.keySet()) {
            ExtentReportUtil.createTest(testCase);
            Map<String, String> data = testData.get(testCase);
        ExtentReportUtil.logInfo("Entering Location: " + data.get("Location"));
        selectLocationDif(data.get("Location"));

        ExtentReportUtil.logInfo("Selecting Hotel-Name: " + data.get("Hotels"));
        selectHotelNameDif(data.get("Hotels"));

        ExtentReportUtil.logInfo("Selecting Room Type: " + data.get("RoomType"));
        selectRoomTypeDif(data.get("RoomType"));

        ExtentReportUtil.logInfo("Selecting Room Count: " + data.get("NumberOfRooms"));
        enterRoomCountDif(Integer.parseInt(data.get("NumberOfRooms")));

        ExtentReportUtil.logInfo("Selecting Checkin Date: " + data.get("CheckInDate"));
        enterCheckInDateDif(data.get("CheckInDate").toString());

        ExtentReportUtil.logInfo("Selecting Checkout Date: " + data.get("CheckOutDate"));
        enterCheckOutDateDif(data.get("CheckOutDate").toString());

        ExtentReportUtil.logInfo("Selecting Adults Per Room count: " + data.get("AdultsPerRoom"));
        enterAdultCountDif(Integer.parseInt(data.get("AdultsPerRoom")));

        ExtentReportUtil.logInfo("Selecting Children Per Room count: " + data.get("ChildrenPerRoom"));
        enterChildCountDif(Integer.parseInt(data.get("ChildrenPerRoom")));

        ExtentReportUtil.logInfo("Clicking search button");
        clickSubmitButton();
        
        String returnPageText = BookingPage.returnPageText();
        
        Assertions act = new Assertions(driver);
        act.assertTextContains("Total Price (excl. GST) ", returnPageText, "Fetched Booking Summary", "Not Fetched Bookin Summary");
        
        BookingPage.totalAmount();
        
        radioBtn.click();
        contBtn.click();
		}
	}
}
