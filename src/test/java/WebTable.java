import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTable {
	 static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://classic.freecrm.com/index.html");
		driver.manage().window().maximize();

	}

	@Test
	public void webTableTest()  {
//Login credentials
		driver.findElement(By.cssSelector(".form-control:nth-of-type(1)")).sendKeys("kahlon");
		driver.findElement(By.cssSelector(".form-control:nth-of-type(2)")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@type='submit']")).submit();
		
		//Switch to frame
		driver.switchTo().frame("mainpanel");
		
		//click on contacts under menu bar
		driver.findElement(By.xpath("//*[@id='navmenu']/ul/li[4]/a")).click();

		//call the bewlow methods and pass the 
		// selectContactList("Kritica Sharma");
		selectContactList("Preet kaur");

		System.out.println(selectContactCompanyInfo("Preet kaur"));
		System.out.println(getContactPhone("Kritica Sharma"));
		System.out.println(getContactEmail("Amar Singh"));
		

	}

	public  static void selectContactList(String contactName) {
		String xpath = "//a[text()='" + contactName + "']/parent::td/preceding-sibling::td/input";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}

	public  static String selectContactCompanyInfo(String contactName) {
		String xpath = "//a[text()='" + contactName + "']//parent::td//following-sibling::td/a[@context='company']";
		return driver.findElement(By.xpath(xpath)).getText();

	}

	public static String getContactPhone(String contactName) {
		String xpath = "//a[text()='" + contactName + "']//parent::td//following-sibling::td/span[@context='phone']";
		return driver.findElement(By.xpath(xpath)).getText();

	}

	public static String getContactEmail(String contactName) {
		String xpath = "(//a[text()='" + contactName + "']//parent::td//following-sibling::td/a)[2]";
		return driver.findElement(By.xpath(xpath)).getText();

	}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

}
