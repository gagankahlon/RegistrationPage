import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationPage {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
		driver.manage().window().maximize();
	}

	@Test
	public void registrationPageTest() {
		By firstName=By.id("input-firstname");
		By lastName=By.id("input-lastname");
		By email=By.id("input-email");
		By telePhone=By.id("input-telephone");
		By password=By.id("input-password");
		By passwordConfirm=By.id("input-confirm");
		By checkBox=By.cssSelector("div.buttons div input:first-of-type");
		By continueButton=By.cssSelector("div.buttons div input:last-of-type");
		By accountCreationText=By.cssSelector(" div #content h1");
		
		driver.findElement(firstName).sendKeys("gagan");
		driver.findElement(lastName).sendKeys("kahlon");
		driver.findElement(email).sendKeys("gagan@gamil.com");
		driver.findElement(telePhone).sendKeys("9876543210");
		driver.findElement(password).sendKeys("test123");
		driver.findElement(passwordConfirm).sendKeys("test123");
		driver.findElement(checkBox).click();
		driver.findElement(continueButton).click();
		String text=driver.findElement(accountCreationText).getText();
		System.out.println(text);
		
		Assert.assertEquals(text, "Your Account Has Been Created!");
		
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
