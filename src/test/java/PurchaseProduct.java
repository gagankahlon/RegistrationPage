import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.qa.automationpractice.util.ElementUtil;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PurchaseProduct {

	WebDriver driver;
	ElementUtil eleUtil;
	Select select;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		eleUtil = new ElementUtil(driver);

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

	}

	@Test()
	public void purchaseProductTest() throws InterruptedException {

		// Login credentials to login into opencart

		By email = By.id("input-email");
		By password = By.id("input-password");
		By loginBtn = By.xpath("//input[@type='submit']");
		eleUtil.doSendKeys(email, "gagan@gmail.com");
		eleUtil.doSendKeys(password, "test123");
		eleUtil.doClick(loginBtn);
		String title=driver.getTitle();
		System.out.println("Title of the page after logging in:"+title);
		Assert.assertTrue(title.contains(Constants.ACCOUNT_PAGE_TITLE));
	
	
      //After logging in Select Cameras from menu bar
		
	
		By camerasMenu = By.cssSelector("nav#menu div>ul>li:nth-of-type(7) a");
		eleUtil.doClick(camerasMenu);

		By selectProduct = By.cssSelector(
				"#content > div:nth-child(3) > div:nth-child(2) > div > div:nth-child(2) > div.caption > h4 > a");
		eleUtil.doClick(selectProduct);

		/* Selected product description opens and select desired quantity and add to
		 cart*/

		By quantity = By.cssSelector("#input-quantity");
		eleUtil.doClear(quantity);
		eleUtil.doSendKeys(quantity, "2");
		By addToCartBtn = By.cssSelector("div.form-group button");
		eleUtil.doClick(addToCartBtn);

		// Open shopping cart and perform checkout

		By shoppingCartClick = By.cssSelector("div.alert a:nth-of-type(2)");
		eleUtil.doClick(shoppingCartClick);

		By checkOutBtn = By.cssSelector("div.buttons div:nth-of-type(2) a");
		eleUtil.doClick(checkOutBtn);
		

		/* On checkout page a user, select or add new address for billing,delivery and
		 also chose delivery method and payment method. After confirming the order,
		 user got successfully order placed message.*/
		
		By billingDetailsRadioBtn = By
				.cssSelector("#collapse-payment-address > div > form > div:nth-child(3) > label > input[type=radio]");
		eleUtil.doClick(billingDetailsRadioBtn);

		By billingFirstName = By.id("input-payment-firstname");
		eleUtil.doSendKeys(billingFirstName, "gagan");

		By billingLastName = By.id("input-payment-lastname");
		eleUtil.doSendKeys(billingLastName, "kahlon");

		By billingInputAddress = By.id("input-payment-address-1");
		eleUtil.doSendKeys(billingInputAddress, "75 newcastle");

		By billingCity = By.id("input-payment-city");
		eleUtil.doSendKeys(billingCity, "Toronto");

		By billingPostCode = By.id("input-payment-postcode");
		eleUtil.doSendKeys(billingPostCode, "N2R 1T7");

		By countryDropDown = By.id("input-payment-country");
		eleUtil.doDropDownSelectByVisibleText(countryDropDown, "Canada");

		By stateDropDown = By.id("input-payment-zone");
		eleUtil.doDropDownSelectByVisibleText(stateDropDown, "Ontario");

		By billingDetailsContinueBtn = By.id("button-payment-address");
		eleUtil.doClick(billingDetailsContinueBtn);

		By deliveryDetailsRadioBtn = By
				.cssSelector("#collapse-shipping-address > div > form > div:nth-child(1) > label > input[type=radio]");
		eleUtil.doClick(deliveryDetailsRadioBtn);

		By deliveryContinueBtn = By.cssSelector("#button-shipping-address");
		eleUtil.doClick(deliveryContinueBtn);

		By deliveryMethodComment = By.xpath("//textarea[@name='comment']");
		eleUtil.doSendKeys(deliveryMethodComment, "My first order");

		By deliveryMethodContinueBtn = By.xpath("//*[@id=\"button-shipping-method\"]");
		eleUtil.doClick(deliveryMethodContinueBtn);

		By paymentMethodTerms = By
				.cssSelector("#collapse-payment-method > div > div.buttons > div > input[type=checkbox]:nth-child(2)");
		eleUtil.doClick(paymentMethodTerms);

		By paymentMethodContinueBtn = By.cssSelector("#button-payment-method");
		eleUtil.doClick(paymentMethodContinueBtn);

		By confirmOrderBtn = By.cssSelector("#button-confirm");
		eleUtil.doClick(confirmOrderBtn);
		Thread.sleep(2000);
		By orderSuccessfulMessage = By.cssSelector("div#content h1");
		String text = eleUtil.doGetText(orderSuccessfulMessage);
		System.out.println("Order is placed successfully message:"+text);
		Assert.assertTrue(text.contains(Constants.ORDER_PLACED_SUCCESSFULLY_MESSAGE));

	}

	@AfterMethod

	public void tearDown() {

		driver.quit();

	}

}