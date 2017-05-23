package vadtalTesting;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constants;
import Utils.Function;
import Utils.Log;

public class DonationReceipt {
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
		System.out.println(driver.getTitle());
		Assert.assertEquals("Donation", driver.getTitle());
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

			// press tab to check for the mandatory fields
			// mobile number
			driver.findElement(By.xpath("//*[@id='md-input-3-input']")).click();
			Thread.sleep(2000);
			// donor's id
			driver.findElement(By.xpath("//*[@id='md-input-4-input']")).sendKeys(Keys.TAB);

			// firstname
			driver.findElement(By.id("md-input-5-input")).sendKeys(Keys.TAB);

			// lastname
			driver.findElement(By.id("md-input-6-input")).sendKeys(Keys.TAB);

			// email
			driver.findElement(By.id("md-input-7-input")).sendKeys(Keys.TAB);

			// gender
			driver.findElement(By.xpath("//select")).sendKeys(Keys.TAB);

			// payment type
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[8]/select"))
					.sendKeys(Keys.TAB);

			// address
			driver.findElement(By.id("md-input-8-input")).sendKeys(Keys.TAB);

			// country
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[12]/select"))
					.sendKeys(Keys.TAB);

			// state
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[13]/select"))
					.sendKeys(Keys.TAB);

			// city
			driver.findElement(By.xpath("//div[14]/div/input")).sendKeys(Keys.TAB);

			// area
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[15]/select"))
					.sendKeys(Keys.TAB);

			Thread.sleep(2000);

			// select account
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[1]/select"))
					.sendKeys(Keys.TAB);

			// select prasad
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[2]/select"))
					.sendKeys(Keys.TAB);

			// select rupee
			driver.findElement(By.id("md-input-10-input")).sendKeys(Keys.TAB);

			// select quantity
			driver.findElement(By.xpath("//*[@id='md-input-11-input']")).sendKeys(Keys.TAB);

			// note
			driver.findElement(By.id("md-input-9-input")).sendKeys(Keys.TAB);

			// click on the special note checkbox
			driver.findElement(By
					.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[22]/md-checkbox/label/div"))
					.click();
			Thread.sleep(2000);

			// special report
			driver.findElement(By.id("md-input-13-input")).sendKeys(Keys.TAB);

			// get the mandatory fields error
			// first name
			String firstName = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[3]/div/validation/small/span"))
					.getText();
			if (firstName.contains(Constants.FIRST_NAME_ERROR)) {
				Log.info("FIRST NAME IS MANDATORY ERROR IS PRESENT");
			} else {
				Log.error("FIRST NAME IS MANDATORY ERROR IS NOT PRESENT");
			}
			// last name
			String lastName = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[4]/div/validation/small/span"))
					.getText();

			if (lastName.contains(Constants.LAST_NAME_ERROR)) {
				Log.info("LAST NAME IS MANDATORY ERROR IS PRESENT");
			} else {
				Log.error("LAST NAME IS MANDATORY ERROR IS NOT PRESENT");
			}

			String city = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[2]/validation/small/span"))
					.getText();
			if (city.contains(Constants.CITY_ERROR)) {
				Log.info("CITY IS MANDATORY ERROR IS PRESENT");
			} else {
				Log.error("CITY IS MANDATORY ERROR IS NOT PRESENT");
			}

			String rupeeQuantity = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[1]/small/span"))
					.getText();
			if (rupeeQuantity.contains(Constants.MANDATORY_ERROR)) {
				Log.info("MANDATORY FIELD ERROR IS PRESENT");
			} else {
				Log.error("MANDATORY FIELD ERROR IS NOT PRESENT");
			}

			String specialNoteError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[23]/div[1]/div/validation/small/span"))
					.getText();
			if (specialNoteError.contains(Constants.SPECIAL_REPORT_ERROR)) {
				Log.info("SPECIAL NOTE IS MANDATORY ERROR IS PRESENT");
			} else {
				Log.error("SPECIAL NOTE IS MANDATORY ERROR IS NOT PRESENT");
			}

		}

	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

}
