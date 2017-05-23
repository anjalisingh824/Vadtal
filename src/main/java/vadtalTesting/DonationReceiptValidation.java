package vadtalTesting;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

public class DonationReceiptValidation {
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
		// ExcelUtils.getTableArray(Constants.FILE_FULL_PATH,
		// Constants.DONATION_RECIEPT);

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

			// passing validation value 1 from excel
			int row3 = 3;
			int col = 19;
			Function.donationReciept(driver, 3);
			Thread.sleep(2000);
			// now checking for the errors after passing the validation value 1
			// first name
			String firstNameError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[3]/div/validation/small/span"))
					.getText();
			if (firstNameError.contains(Constants.VALID_FIRSTN_ERROR)) {
				Log.info("FIRST NAME BETWEEN 2 TO 200 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("FIRST NAME BETWEEN 2 TO 200 CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// last name
			String lastNameError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[4]/div/validation/small/span"))
					.getText();
			if (lastNameError.contains(Constants.LAST_NAME_ERROR)) {
				Log.info("LAST NAME BETWEEN 2 TO 100 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("LAST NAME BETWEEN 2 TO 100 CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// email
			String emailError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[6]/div/validation[1]/small/span"))
					.getText();
			if (emailError.contains(Constants.VALID_EMAILA_ERROR)) {
				Log.info("EMAIL BETWEEN 2 TO 50 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("EMAIL BETWEEN 2 TO 50 CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// Address
			String addressError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[10]/div/validation/small/span"))
					.getText();
			if (addressError.contains(Constants.VALID_ADDRESS_ERROR)) {
				Log.info("EMAIL ADDRESS BETWEEN 2 TO 250 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("EMAIL ADDRESS BETWEEN 2 TO 250 CHARCTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// city
			String cityError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[2]/validation/small/span"))
					.getText();
			if (cityError.contains(Constants.VALID_CITY_ERROR)) {
				Log.info("CITY BETWEEN 2 TO 5O CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("CITY BETWEEN 2 TO 5O CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// rupee
			String rupeeError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[1]/small/span"))
					.getText();
			if (rupeeError.contains(Constants.VALID_RUPEE_ERROR)) {
				Log.info("INVALID AMOUNT ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID AMOUNT ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// quantity
			String quantityError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[2]/small/span"))
					.getText();
			if (quantityError.contains(Constants.VALID_QUANTITY_ERROR)) {
				Log.info("INVALID QUANTITY ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID QUANTITY ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// note
			String noteError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[20]/div/validation/small/span"))
					.getText();
			if (noteError.contains(Constants.VALID_NOTE_ERROR)) {
				Log.info("NOTE BETWEEN 2 TO 300 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			}

			else {
				Log.error("NOTE BETWEEN 2 TO 300 CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}

			// special note
			String specialNError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[23]/div[1]/div/validation/small/span"))
					.getText();
			if (specialNError.contains(Constants.VALID_SPECIALN_ERROR)) {
				Log.info("SPECIAL NOTE BETWEEN 2 TO 100 CHARACTERS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row3, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("SPECIAL NOTE BETWEEN 2 TO 100 CHARACTERS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row3, col, Constants.DONATION_RECIEPT);
			}
			Log.info("--------------------------------------------------");
			Thread.sleep(2000);

			// clearing the fields
			driver.navigate().refresh();
			Thread.sleep(2000);

			// passing validation value 2 from excel
			int row4 = 4;
			Function.donationReciept(driver, 4);

			// now check for the errors after passing the validation values 2

			// mobile number
			String validMobNError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[1]/div[2]/validation/small/span"))
					.getText();
			if (validMobNError.contains(Constants.VALID_MOBILEN_ERROR)) {
				Log.info("ENTER VALIID MOBILE NUMBER ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID MOBILE NUMBER ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}

			// firstname
			String validFNError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[3]/div/validation/small/span"))
					.getText();
			if (validFNError.contains(Constants.VALID_FN_ERROR)) {
				Log.info("ENTER VALID FIRST NAME ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID FIRST NAME ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}

			// lastname
			String validLNError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[4]/div/validation/small/span"))
					.getText();
			if (validLNError.contains(Constants.VALID_LN_ERROR)) {
				Log.info("ENTER VALID LAST NAME ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID LAST NAME ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}

			// email address
			String emailAError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[6]/div/validation/small/span"))
					.getText();
			if (emailAError.contains(Constants.VALID_EA_ERROR)) {
				Log.info("ENTER VALID EMAIL ADDRESS ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID EMAIL ADDRESS ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);

			}

			// city
			String validCityError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[2]/validation/small/span"))
					.getText();
			if (validCityError.contains(Constants.VALID_CT_ERROR)) {
				Log.info("ENTER VALID CITY ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID CITY ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}

			// rupee4
			String rupError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[1]/small/span"))
					.getText();
			if (rupError.contains(Constants.VALID_RUPEE_ERROR)) {
				Log.info("INVALID AMOUNT ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID AMOUNT ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}

			// quantity
			String quanError = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[2]/small/span"))
					.getText();
			if (quanError.contains(Constants.VALID_QUANTITY_ERROR)) {
				Log.info("INVALID QUANTITY ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row4, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID QUANTITY ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row4, col, Constants.DONATION_RECIEPT);
			}
			Log.info("-----------------------------------------");

			// clearing the fields
			driver.navigate().refresh();
			Thread.sleep(2000);

			// passing validation value 3
			int row5 = 5;

			Function.donationReciept(driver, 5);

			// now checking for the errors found after passing values
			// mobile number
			String validMobNError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[1]/div[2]/validation/small/span"))
					.getText();
			if (validMobNError1.contains(Constants.VALID_MOBILEN_ERROR)) {
				Log.info("ENTER VALIID MOBILE NUMBER ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID MOBILE NUMBER ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}

			// firstname
			String validFNError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[3]/div/validation/small/span"))
					.getText();
			if (validFNError1.contains(Constants.VALID_FN_ERROR)) {
				Log.info("ENTER VALID FIRST NAME ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID FIRST NAME ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}

			// lastname
			String validLNError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[4]/div/validation/small/span"))
					.getText();
			if (validLNError1.contains(Constants.VALID_LN_ERROR)) {
				Log.info("ENTER VALID LAST NAME ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID LAST NAME ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}

			// city
			String validCityError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[14]/div[2]/validation/small/span"))
					.getText();
			if (validCityError1.contains(Constants.VALID_CT_ERROR)) {
				Log.info("ENTER VALID CITY ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("ENTER VALID CITY ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}
			// rupee4
			String rupError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[1]/small/span"))
					.getText();
			if (rupError1.contains(Constants.VALID_RUPEE_ERROR)) {
				Log.info("INVALID AMOUNT ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID AMOUNT ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}

			// quantity
			String quanError1 = driver
					.findElement(By
							.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[17]/div/div/div[3]/div/validation[2]/small/span"))
					.getText();
			if (quanError1.contains(Constants.VALID_QUANTITY_ERROR)) {
				Log.info("INVALID QUANTITY ERROR IS PRESENT");
				ExcelUtils.setCellData("PASS", row5, col, Constants.DONATION_RECIEPT);
			} else {
				Log.error("INVALID QUANTITY ERROR IS NOT PRESENT");
				ExcelUtils.setCellData("FAIL", row5, col, Constants.DONATION_RECIEPT);
			}
			Log.info("-----------------------------------------");

		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}