package vadtalTesting;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constants;
import Utils.ExcelUtils;
import Utils.Function;
import Utils.Log;

public class DonationReceiptValidation1 {
	private WebDriver driver;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/smart/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get(Constants.BASE_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testVadtal() throws Exception {

		// check for the page title
		Assert.assertTrue(Constants.VERIFY_LOGIN_PAGE_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		Log.info("Login page title matched");

		// call the login method for successful login
		Function.login(driver, 1);
		Thread.sleep(2000);

		// check for the home page title
		Assert.assertTrue(Constants.VERIFY_HOME_PAGE_TITLE.contains(driver.getTitle()));
		Thread.sleep(5000);

		Log.info("Home page title matched");

		// click on Donation mentioned on the home page
		driver.findElement(By.xpath("//md-grid-tile[2]/figure/a/md-card/md-card-title-group/div/md-card-title[2]/h3"))
				.click();
		Thread.sleep(7000);

		// check for the Donation Page title
		Assert.assertEquals(Constants.VERIFY_DONATION_PAGE_TITLE, driver.getTitle());
		Thread.sleep(2000);
		Log.info("Donation page title matched");

		// click on Doantion reciept under Donation module
		driver.findElement(By
				.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-dashboard/div/div[3]/ul/li[1]/a/span"))
				.click();
		Thread.sleep(5000);

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		// iterate through your windows
		while (iterator.hasNext()) {
			String parent = iterator.next();
			String newwindow = iterator.next();
			driver.switchTo().window(newwindow);

			int row = 3;

			// mobile number
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[1]/div[1]/input"))
					.sendKeys(ExcelUtils.getCellData(row, 1, Constants.DONATION_RECIEPT));

			// firstname
			driver.findElement(
					By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'First name')]]"))
					.sendKeys(ExcelUtils.getCellData(row, 2, Constants.DONATION_RECIEPT));

			// lastname
			driver.findElement(
					By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Last name')]]"))
					.sendKeys(ExcelUtils.getCellData(row, 3, Constants.DONATION_RECIEPT));

			// mobile number
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Email')]]"))
					.sendKeys(ExcelUtils.getCellData(row, 4, Constants.DONATION_RECIEPT));

			// gender
			Function.dropDown(driver, row, 5, Constants.DONATION_RECIEPT);

			// payment type
			Function.dropDown(driver, row, 6, Constants.DONATION_RECIEPT);
			
			//address
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Address')]]"))
			.sendKeys(ExcelUtils.getCellData(row, 7, Constants.DONATION_RECIEPT));
			

			// country
			Function.dropDown(driver, row, 8, Constants.DONATION_RECIEPT);

			// state
			Function.dropDown(driver, row, 9, Constants.DONATION_RECIEPT);
			
			// city
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[1]/input"))
					.sendKeys(ExcelUtils.getCellData(row, 10, Constants.DONATION_RECIEPT));
			
			// area
			Function.dropDown(driver, row, 11, Constants.DONATION_RECIEPT);
			Thread.sleep(5000);

			// select account
			Function.dropDown(driver, row, 12, Constants.DONATION_RECIEPT);
			Thread.sleep(5000);

			// select prasad
			Function.dropDown(driver, row, 13, Constants.DONATION_RECIEPT);
			
			//rupees
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Rupees')]]"))
			.sendKeys(ExcelUtils.getCellData(row, 14, Constants.DONATION_RECIEPT));
			
			
			//quantity
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Quantity')]]"))
			.sendKeys(ExcelUtils.getCellData(row, 15, Constants.DONATION_RECIEPT));
			
			
			//note
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Enter note here')]]"))
			.sendKeys(ExcelUtils.getCellData(row, 16, Constants.DONATION_RECIEPT));
			
			
			// click on the special note checkbox
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[22]/md-checkbox/label/div"))
					.click();
			Thread.sleep(2000);
			
			//special note
			driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Enter special note here')]]"))
			.sendKeys(ExcelUtils.getCellData(row, 17, Constants.DONATION_RECIEPT));
			
			
			Thread.sleep(5000);

		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
