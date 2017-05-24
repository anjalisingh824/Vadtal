package vadtalTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constants;
import Utils.ExcelUtils;
import Utils.Function;
import Utils.Log;

public class AddUserValidation {
	
	private WebDriver driver;
	@BeforeTest
	
	public void setUp()
	{
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
		
		//click on the add user page
		driver.findElement(By.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-appboard/div/md-card/md-card-content/md-grid-list/div/md-grid-tile[1]/figure/a/md-card/md-card-title-group/div/md-card-title[2]/h3")).click();
		Thread.sleep(5000);
		
		//now check for the add user page title
		
		Assert.assertEquals(Constants.VERIFY_USER_PAGE,driver.getTitle());
		Thread.sleep(5000);
		
		Log.info("User page title matched");
		
		//click on the button to add user
		driver.findElement(By.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-user/app-user-list/div[2]/button/span/md-icon")).click();
	    Thread.sleep(2000);
	    
	    //check for the mandatory fields
	    
	    //firstname
	    driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'First name')]]")).sendKeys(Keys.TAB);
		
	    //lastname
	    driver.findElement(By.xpath("//input[@type='text'][following-sibling::label[contains(text(),'Last name')]]")).sendKeys(Keys.TAB);
	    

	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
