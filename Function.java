package Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Function {

	public static void login(WebDriver driver, int row) throws Exception {
		// passing username
		driver.findElement(By.id("md-input-0-input"))
				.sendKeys(ExcelUtils.getCellData(row, 1, Constants.DONATION_RECIEPT));
		// passing password
		driver.findElement(By.id("md-input-1-input"))
				.sendKeys(ExcelUtils.getCellData(row, 2, Constants.DONATION_RECIEPT));

		// click on signin button
		driver.findElement(By
				.xpath("//html/body/app-root/app-signin-forgotpassword/md-sidenav-layout/div[2]/div/div/div/div[2]/div/md-card-content/form/div/div[3]/button"))
				.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}

	public static void dropDown(WebDriver driver, int row, int cellno, String sheetName) throws Exception {
		List<WebElement> elementList = driver.findElements(By.tagName("option"));
		System.out.println(elementList.size());
		String str = ExcelUtils.getCellData(row, cellno, sheetName);
		boolean check = false;

		for (int i = 0; i < elementList.size(); i++) {

			if (elementList.get(i).getText().contains(str)) {
				WebElement elementClick = elementList.get(i);
				Thread.sleep(5000);
				elementClick.click();
				check = true;
				break;
			}

		}
		if (!check) {
			elementList.get(1).click();
		}

	}

	public static void donationReciept(WebDriver driver, int row) throws Exception {
		// mobile number

		driver.findElement(By.xpath("//*[@id='md-input-3-input']"))
				.sendKeys(ExcelUtils.getCellData(row, 1, Constants.DONATION_RECIEPT));
		Thread.sleep(2000);

		// firstname
		driver.findElement(By.id("md-input-5-input"))
				.sendKeys(ExcelUtils.getCellData(row, 2, Constants.DONATION_RECIEPT));

		// lastname
		driver.findElement(By.id("md-input-6-input"))
				.sendKeys(ExcelUtils.getCellData(row, 3, Constants.DONATION_RECIEPT));

		// email
		driver.findElement(By.id("md-input-7-input"))
				.sendKeys(ExcelUtils.getCellData(row, 4, Constants.DONATION_RECIEPT));

		// gender
		Function.dropDown(driver, row, 5, Constants.DONATION_RECIEPT);

		// payment type
		Function.dropDown(driver, row, 6, Constants.DONATION_RECIEPT);

		// address
		driver.findElement(By.id("md-input-8-input"))
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

		// select rupee
		driver.findElement(By.id("md-input-10-input"))
				.sendKeys(ExcelUtils.getCellData(row, 14, Constants.DONATION_RECIEPT));

		// select quantity
		driver.findElement(By.xpath("//*[@id='md-input-11-input']"))
				.sendKeys(ExcelUtils.getCellData(row, 15, Constants.DONATION_RECIEPT));

		// note
		driver.findElement(By.id("md-input-9-input"))
				.sendKeys(ExcelUtils.getCellData(row, 16, Constants.DONATION_RECIEPT));
		Thread.sleep(5000);

		// click on the special note checkbox
		driver.findElement(By
				.xpath("//html/body/app-root/app-home/md-sidenav-layout/div[2]/div/app-donation-home/app-donation-receipt/div[1]/form/div[1]/div[22]/md-checkbox/label/div"))
				.click();
		Thread.sleep(2000);

		// special report
		driver.findElement(By.id("md-input-13-input"))
				.sendKeys(ExcelUtils.getCellData(row, 17, Constants.DONATION_RECIEPT));
		Thread.sleep(2000);
	}
}
