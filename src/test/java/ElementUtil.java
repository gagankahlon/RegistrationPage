import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;
	
	

	public ElementUtil(WebDriver driver) {
		this.driver=driver;	
		
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}
	public void doClear(By locator) {
		getElement(locator).clear();
	}
	
	
	public void doSendKeys(By locator,String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
		
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	
	}
}
