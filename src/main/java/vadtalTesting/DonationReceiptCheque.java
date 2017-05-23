package vadtalTesting;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utils.Constants;
import Utils.ExcelUtils;
import Utils.Function;
import Utils.Log;

public class DonationReceiptCheque {
	private WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void setUp(String browserName) throws Exception {
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/home/smart/Downloads/Untitled Folder/geckodriver");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/home/smart/Downloads/chromedriver");
			driver = new ChromeDriver();
		}

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
		Assert.assertEquals("Donation", driver.getTitle());
		Thread.sleep(2000);
		Log.info("Donation page title matched");

		// click on Donation receipt under Donation module
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

			int row7 = 7;
			// mobile number

			driver.findElement(By.xpath("//*[@id='md-input-3-input']"))
					.sendKeys(ExcelUtils.getCellData(row7, 1, Constants.DONATION_RECIEPT));
			Thread.sleep(2000);

			// firstname
			driver.findElement(By.id("md-input-5-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 2, Constants.DONATION_RECIEPT));

			// lastname
			driver.findElement(By.id("md-input-6-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 3, Constants.DONATION_RECIEPT));

			// email
			driver.findElement(By.id("md-input-7-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 4, Constants.DONATION_RECIEPT));

			// gender
			Function.dropDown(driver, row7, 5, Constants.DONATION_RECIEPT);

			// payment type
			Function.dropDown(driver, row7, 6, Constants.DONATION_RECIEPT);

			// address
			driver.findElement(By.id("md-input-8-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 7, Constants.DONATION_RECIEPT));

			// country
			Function.dropDown(driver, row7, 8, Constants.DONATION_RECIEPT);

			// state
			Function.dropDown(driver, row7, 9, Constants.DONATION_RECIEPT);

			// city
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[1]/input"))
					.sendKeys(ExcelUtils.getCellData(row7, 10, Constants.DONATION_RECIEPT));

			// area
			Function.dropDown(driver, row7, 11, Constants.DONATION_RECIEPT);
			Thread.sleep(5000);

			// select account
			Function.dropDown(driver, row7, 12, Constants.DONATION_RECIEPT);
			Thread.sleep(5000);

			// select prasad
			Function.dropDown(driver, row7, 13, Constants.DONATION_RECIEPT);

			// bank name
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div[1]/input"))
					.sendKeys(ExcelUtils.getCellData(row7, 14, Constants.DONATION_RECIEPT));

			// check no
			driver.findElement(By.id("md-input-16-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 15, Constants.DONATION_RECIEPT));
			Thread.sleep(2000);

			// select rupee
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[4]/md-input[1]/div/div[1]/div[2]/input"))
					.sendKeys(ExcelUtils.getCellData(row7, 16, Constants.DONATION_RECIEPT));

			// note
			driver.findElement(By.id("md-input-9-input"))
					.sendKeys(ExcelUtils.getCellData(row7, 17, Constants.DONATION_RECIEPT));
			Thread.sleep(5000);

			// click on the special note checkbox
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[22]/md-checkbox/label/div"))
					.click();
			Thread.sleep(5000);

			// special report
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[23]/div[1]/md-input/div/div[1]/div[2]/input"))
					.sendKeys(ExcelUtils.getCellData(row7, 18, Constants.DONATION_RECIEPT));

			// click on save and print
			driver.findElement(By.linkText("SAVE & PRINT")).click();
			Thread.sleep(2000);

			driver.close();

		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
