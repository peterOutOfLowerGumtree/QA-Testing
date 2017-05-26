package Tests;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Files.RandTweet;
import Files.TwitterPOM;

public class Scenario4 {
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
	public void testFollowAndMessage() {
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
			TwitterPOM.username(driver).sendKeys("Alokpushesback");
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123");
			TwitterPOM.confirmLogin(driver).click();
			Assert.assertTrue(driver.getTitle().contains("Twitter"));
			System.out.println("Logged in");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot log in");
		}

		// Follow user
		try {
			TwitterPOM.searchBox(driver).sendKeys("SuperRobot6");
			TimeUnit.SECONDS.sleep(4); // Sometimes temperamental - doesn't
										// always give drop-down options in
										// search bar & causes JUnit test
										// failure
			TwitterPOM.searchBox(driver).sendKeys(Keys.DOWN, Keys.RETURN);
			TimeUnit.SECONDS.sleep(5);
			TwitterPOM.follow(driver).click();
			System.out.println("Followed user");
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot follow user");
		}

		// Message user
		try {
			RandTweet word = new RandTweet();
			TwitterPOM.messageBox(driver).click();
			TimeUnit.SECONDS.sleep(5);
			TwitterPOM.writeMessage(driver).sendKeys(word.randTweet((int) Math.floor(Math.random()*16)), Keys.ENTER);
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Message sent");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot message user");
		}
	}

	@AfterClass
	public static void revertChanges() {
		// Unfollow user - will fail if 'follow' failed
		try {
			TwitterPOM.searchBox(driver).sendKeys("SuperRobot6");
			TimeUnit.SECONDS.sleep(4); // Sometimes temperamental - doesn't
										// always give drop-down options in
										// search bar & causes JUnit test
										// failure
			TwitterPOM.searchBox(driver).sendKeys(Keys.DOWN, Keys.RETURN);
			TimeUnit.SECONDS.sleep(5);
			TwitterPOM.unFollow(driver).click();
			System.out.println("Unfollowed user");
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot unfollow user");
		}

		// Sign out
		try {
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Logged out");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Cannot log out");
		}
		driver.quit();
	}
}
