import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
WebDriver driver;

	
	
	@BeforeMethod
	public void setUp() {
	System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://automationpractice.com/index.php");
	driver.manage().window().maximize();
	}
	
	@Test
   public void test1() {
	String titleOfThePage=driver.getTitle();
	System.out.println("Title of the page:"+ titleOfThePage);
	   
	   
	   

   }	
	@AfterMethod
   public void tearDown() {
	
	     driver.quit();
	   

   }	
   
}
