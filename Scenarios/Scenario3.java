package Tests;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Files.TwitterPOM;

public class Scenario3 {
	static WebDriver driver;

	@Before
	public void loadBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Desktop\\selenium\\SeleniumFiles\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://twitter.com");
		System.out.println("Website found");
	}

	
	@Test
	public void testAddProfilePhoto() {
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Loading...");
			TwitterPOM.logInBtn(driver).click();
			System.out.println("Redirect successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Log in
		try {
			TwitterPOM.username(driver).sendKeys("QACinema");
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123");
			TwitterPOM.confirmLogin(driver).click();
			Assert.assertTrue(driver.getTitle().contains("Twitter"));
			System.out.println("Logged in");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Add profile photo - will fail if there is already a profile photo
		try {
			TwitterPOM.addPic(driver).click();
			TwitterPOM.choosePic(driver).sendKeys("C:\\Users\\Administrator\\workspace\\JMWProject\\realFace.png");
			System.out.println("Selecting photo...");
			TimeUnit.SECONDS.sleep(3);
			TwitterPOM.confirmPic(driver).click();
			System.out.println("Uploading photo...");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Photo uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public static void revertChanges() {
		driver.get("https://twitter.com/QACinema");
		System.out.println("Redirect successful");
		
		// Remove profile photo - will only work if there is a profile photo
		try {
			TwitterPOM.editProfile(driver).click();
			TwitterPOM.editPic(driver).click();
			TwitterPOM.removePic(driver).click();
			TwitterPOM.removeYes(driver).click();
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.saveChanges(driver).click();
			System.out.println("Photo removed");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Sign out
		try {
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Logged out");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
