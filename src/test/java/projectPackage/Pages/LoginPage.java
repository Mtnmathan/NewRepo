package projectPackage.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

import projectPackage.Utilities.Assertions;
import projectPackage.Utilities.BasePage;
import projectPackage.Utilities.ExtentReportUtil;

public class LoginPage {
    static WebDriver driver;

    @FindBy(id = "username")
	static WebElement username;

    @FindBy(id = "password")
    static WebElement password;

    @FindBy(id = "login")
    static WebElement loginBtn;
    
    @FindBy(xpath = "//td[@class='welcome_menu'][contains(text(),'Group')]")
	static WebElement HomePageText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public static void enterUserName(String usernamein) {
        username.sendKeys(usernamein);
    }

    public static void enterPassword(String passInput) {
        password.sendKeys(passInput);
    }

    public static void clickLoginButton() {
        loginBtn.click();
    }
    
    
    public static String getTextIn() {
    	return HomePageText.getText();
    }
    
	public static void loginToBooking() throws InterruptedException {
		ExtentReportUtil.createTest("Login to Booking");
		driver.manage().window().maximize();
//		BasePage.maximizePage();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		BasePage.impWait();
		Assertions act = new Assertions(driver);
		BasePage.getURL("https://adactinhotelapp.com/index.php");
		LoginPage log = new  LoginPage(driver);
		log.enterUserName("DemoBookMTN03");
		log.enterPassword("91A78F");
		log.clickLoginButton();
		String HomePageText = log.getTextIn();
		act.assertTextContains("Welcome to Adactin Group of Hotels", HomePageText, "Successfully logged in", "Unable to login");
//		act.assertTest(true, "Successfully logged in", "Unable to login");

	}
    
}
