package Tests;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Files.RandTweet;
import Files.TwitterPOM;

public class Scenario2 {
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
	public void testTweet() {
		try {
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.logInBtn(driver).click();	//Open login dialogue
			System.out.println("Redirect successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Log in
		try {
			TwitterPOM.username(driver).sendKeys("Alokpushesback");	// Username
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123");	// Password
			TwitterPOM.confirmLogin(driver).click();
			Assert.assertTrue(driver.getTitle().contains("Twitter"));
			System.out.println("Logged in");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Write tweet randomly selected from RandTweet.java
		try {
			RandTweet word = new RandTweet();
			TwitterPOM.tweetBox(driver).sendKeys(word.randTweet((int) Math.floor(Math.random() * 16)));
			TwitterPOM.sendTweet(driver).click();
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Tweet sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@AfterClass
	public static void revertChanges() {
		driver.get("https://twitter.com/Alokpushesback");
		System.out.println("Redirect successful");

		try {
			// Sign out
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Logged out");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
// SuperRobot6