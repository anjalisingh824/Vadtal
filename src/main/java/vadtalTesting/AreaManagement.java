package vadtalTesting;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constants;
import Utils.Function;
import Utils.Log;

public class AreaManagement {
	private WebDriver driver;

	
	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/smart/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get(Constants.BASE_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testVadtal() throws Exception {

		// check for the page title
		AssertJUnit.assertTrue(Constants.VERIFY_LOGIN_PAGE_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		Log.info("Login page title matched");

		// call the login method for successful login
		Function.login(driver, 1);
		Thread.sleep(2000);

		// check for the home page title
		AssertJUnit.assertTrue(Constants.VERIFY_HOME_PAGE_TITLE.contains(driver.getTitle()));
		Thread.sleep(5000);

		Log.info("Home page title matched");

		// click on Donation mentioned on the home page
		driver.findElement(By.xpath("//md-grid-tile[2]/figure/a/md-card/md-card-title-group/div/md-card-title[2]/h3"))
				.click();
		Thread.sleep(7000);

		// check for the Donation Page title
		// Assert.assertEquals("Donation", driver.getTitle());
		// Thread.sleep(2000);
		// Log.info("Donation page title matched");

		// click on the Area management
		driver.findElement(By
				.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-dashboard/div/div[1]/ul/li[1]/a/span"))
				.click();

		Thread.sleep(2000);

		// click on the add area button
//		driver.findElement(By
//				.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-area-management/div[2]/button"))
//				.click();
//
//		Thread.sleep(2000);

		// add area from the popup window
		Actions action = new Actions(driver);
		// WebElement ele = driver.findElement(By.className("md-input-infix"));
		// action.moveToElement(ele).click().perform();
		// action.moveToElement(ele).sendKeys("absbnd").perform();
		// Thread.sleep(5000);
		//
		// //click on the create button
		//
		// WebElement
		// buttonClick=driver.findElement(By.cssSelector(".modal-save-btn"));
		// action.moveToElement(buttonClick).click().perform();
		//

		

		// now click on the edit button
		driver.findElement(By
				.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-area-management/div[2]/div/table/tbody/tr[1]/td[4]/i"))
				.click();
		Thread.sleep(5000);

		// now pass the edit values
		WebElement ele = driver.findElement(By.className("md-input-infix"));
		action.moveToElement(ele).click().perform();
		Thread.sleep(2000);
		
		//for clearing the existing field.
		
		
		Thread.sleep(2000);
		
		//update the new field or  we can edit it .
		action.moveToElement(ele).sendKeys("newArea").perform();
		Thread.sleep(5000);

		// now click on the update button
		WebElement buttonClick = driver.findElement(By.cssSelector(".modal-save-btn"));
		action.moveToElement(buttonClick).click().perform();
		Thread.sleep(5000);

	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
