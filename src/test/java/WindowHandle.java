import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandle {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
	}

	@Test
	public void windowHandleTest() {

		String parentWindowHandle = driver.getWindowHandle();
		System.out.println(parentWindowHandle);

		driver.findElement(By.cssSelector("section#social_block li:nth-of-type(3)")).click();
		driver.findElement(By.cssSelector("section#social_block li:nth-of-type(1)")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> handleList = new ArrayList<String>(handles);

		if (switchToRightWindow("YouTube", handleList)) {
			System.out.println("CHild Page URl" + ":" + driver.getCurrentUrl() + ":" + driver.getTitle());

		}

		Assert.assertTrue(true, "Title of the selected window");
		switchToParentWindow(parentWindowHandle);
		System.out.println("Parent Page URl" + ":" + driver.getCurrentUrl() + ":" + driver.getTitle());

	}

	public boolean switchToRightWindow(String windowTitle, List<String> handleList) {

		for (String e : handleList) {
			String title = driver.switchTo().window(e).getTitle();
			if (title.contains(windowTitle)) {
				System.out.println("Right window found");
				return true;

			}

		}
		return false;
	}

	public void switchToParentWindow(String parentWindowHandle) {

		driver.switchTo().window(parentWindowHandle);
	}

	@AfterMethod

	public void tearDown() {

		driver.quit();

	}

}
