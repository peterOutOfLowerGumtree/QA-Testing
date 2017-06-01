// Works with Extent Reports version 2.41.2 
package ExtentReporting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Files.GAKPOM;
import Files.RandTweet;
import Files.TwitterPOM;

public class ExtentLog {
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;

	@BeforeClass
	public void setup() {
		report = new ExtentReports("C:\\Users\\Administrator\\workspace\\JMWProject\\automationreport.html", true);
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Desktop\\selenium\\SeleniumFiles\\Selenium\\chromedriver.exe");
	}
	
	@Test(priority = 1, enabled = true)
	public void verifyGAK() {
		driver = new ChromeDriver();
		driver.get("https://www.gak.co.uk");
		test = report.startTest("Scenario 1: Verify add to basket via GAK");
		test.log(LogStatus.INFO, "Browser started");
		
		try {
			GAKPOM.logInBtn(driver).click();
			GAKPOM.username(driver).sendKeys("jwhiteley0@icloud.com");
			GAKPOM.password(driver).sendKeys("Alokpushesback.123");
			test.log(LogStatus.INFO, "User Info Entered");
			GAKPOM.confirmLogIn(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Boolean loggedIn = driver.getPageSource().contains("Account Details");
		System.out.println(loggedIn);
		if (loggedIn) {
			test.log(LogStatus.PASS, "Log In Successful");
		} else {
			test.log(LogStatus.FAIL, "Log In Unsuccessful");
		}
		
		try {
			GAKPOM.searchBox(driver).sendKeys("Ibanez JEM7V-WH (White)", Keys.ENTER);
			test.log(LogStatus.INFO, "Searched for Ibanez JEM7V-WH (White)");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pageContentContainer']/div/div[1]/div[2]/div[1]/div[3]/div[3]/div[4]/div/div[2]/a"))).click();
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Boolean inBasket = driver.getPageSource().contains("Please click on your preferred delivery date");
		System.out.println(inBasket);
		if (inBasket) {
			test.log(LogStatus.PASS, "Add to Basket Successful");
		} else {
			test.log(LogStatus.FAIL, "Add to Basket Unsuccessful");
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}

	@Test(priority = 2, enabled = true)
	public void verifyTweet() {
		driver = new ChromeDriver();
		test = report.startTest("Scenario 2: Verify log in and tweet");
		test.log(LogStatus.INFO, "Browser started");

		try {
			driver.get("https://twitter.com");
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.logInBtn(driver).click(); // Open login dialogue
			TwitterPOM.username(driver).sendKeys("QACinema"); // Username
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123"); // Password
			test.log(LogStatus.INFO, "User Info Entered");
			TwitterPOM.confirmLogin(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Twitter")) {
			test.log(LogStatus.PASS, "Log In Successful");
		} else {
			test.log(LogStatus.FAIL, "Log In Unsuccessful");
		}
		
		test.log(LogStatus.INFO, "Checked for Identical Tweet (Twitter only allows unique Tweets)");
		Boolean tweetTime = driver.getPageSource().contains("Hello world");	// If there is already a tweet of the same name, it will fail
		System.out.println(tweetTime);										// because Twitter doesn't allow multiple identical tweets. If the
		if (!tweetTime) {													// tweet is unique, it will succeed.
			test.log(LogStatus.PASS, "Tweet Sent");
		} else {
			test.log(LogStatus.FAIL, "Tweet Not Sent");
		}
		
		try {
			TwitterPOM.tweetBox(driver).sendKeys("Hello world");
			TwitterPOM.sendTweet(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Download the free Twitter app | Twitter")) {
			test.log(LogStatus.PASS, "Log Out Successful");
		} else {
			test.log(LogStatus.FAIL, "Log Out Unsuccessful");
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyUploadPicture() {
		driver = new ChromeDriver();
		test = report.startTest("Scenario 3: Verify log in and upload profile picture");
		test.log(LogStatus.INFO, "Browser started");

		try {
			driver.get("https://twitter.com");
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.logInBtn(driver).click(); // Open login dialogue
			TwitterPOM.username(driver).sendKeys("QACinema"); // Username
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123"); // Password
			test.log(LogStatus.INFO, "User Info Entered");
			TwitterPOM.confirmLogin(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Twitter")) {
			test.log(LogStatus.PASS, "Log In Successful");
		} else {
			test.log(LogStatus.FAIL, "Log In Unsuccessful");
		}

		try {
			TwitterPOM.addPic(driver).click();
			TwitterPOM.choosePic(driver).sendKeys("C:\\Users\\Administrator\\workspace\\JMWProject\\realFace.png");
			test.log(LogStatus.INFO, "Photo Chosen");
			TimeUnit.SECONDS.sleep(3);
			TwitterPOM.confirmPic(driver).click();
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Boolean photoSuccess = driver.getPageSource().contains("Your profile photo was published successfully.");
		System.out.println(photoSuccess);
		if(photoSuccess) {
			test.log(LogStatus.PASS, "Photo Upload Successful");
		} else {
			test.log(LogStatus.FAIL, "Photo Upload Unsuccessful");
		}
		
		driver.get("https://twitter.com/QACinema");
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
		
		Boolean photoRemoved = driver.getPageSource().contains("Your profile has been saved.");
		System.out.println(photoRemoved);
		if(photoRemoved) {
			test.log(LogStatus.PASS, "Photo Removal Successful");
		} else {
			test.log(LogStatus.FAIL, "Photo Removal Unsuccessful");
		}

		try {
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Download the free Twitter app | Twitter")) {
			test.log(LogStatus.PASS, "Log Out Successful");
		} else {
			test.log(LogStatus.FAIL, "Log Out Unsuccessful");
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}
	
	@Test(priority = 4, enabled = true)
	public void verifySendMessage() {
		driver = new ChromeDriver();
		test = report.startTest("Scenario 4: Follow a user, send them a message, unfollow them");
		test.log(LogStatus.INFO, "Browser started");

		try {
			driver.get("https://twitter.com");
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.logInBtn(driver).click(); // Open login dialogue
			TwitterPOM.username(driver).sendKeys("QACinema"); // Username
			TwitterPOM.password(driver).sendKeys("Alokpushesback.123"); // Password
			test.log(LogStatus.INFO, "User Info Entered");
			TwitterPOM.confirmLogin(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Twitter")) {
			test.log(LogStatus.PASS, "Log In Successful");
		} else {
			test.log(LogStatus.FAIL, "Log In Unsuccessful");
		}

		try {
			TwitterPOM.searchBox(driver).sendKeys("SuperRobot6");
			TimeUnit.SECONDS.sleep(4); // Sometimes temperamental - doesn't
										// always give drop-down options in
										// search bar & causes JUnit test
										// failure
			TwitterPOM.searchBox(driver).sendKeys(Keys.DOWN, Keys.RETURN);
			TimeUnit.SECONDS.sleep(5);
			test.log(LogStatus.INFO, "Searched for User");
			TwitterPOM.follow(driver).click();
			System.out.println("Followed user");
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot follow user");
		}
		
		Boolean follow = driver.getPageSource().contains("Following");
		System.out.println(follow);
		if(follow) {
			test.log(LogStatus.PASS, "Follow User Successful");
		} else {
			test.log(LogStatus.FAIL, "Follow User Unsuccessful");
		}
		
		try {
			RandTweet word = new RandTweet();
			TwitterPOM.messageBox(driver).click();
			TimeUnit.SECONDS.sleep(5);
			TwitterPOM.writeMessage(driver).sendKeys(word.randTweet((int) Math.floor(Math.random()*16)), Keys.ENTER);
			test.log(LogStatus.INFO, "Message Written");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Message sent");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot message user");
		}
		
		Boolean sent = driver.getPageSource().contains("now");
		System.out.println(sent);
		if(sent) {
			test.log(LogStatus.PASS, "Send Message Successful");
		} else {
			test.log(LogStatus.FAIL, "Send Message Unsuccessful");
		}
		
		try {
			TwitterPOM.searchBox(driver).sendKeys("SuperRobot6");
			TimeUnit.SECONDS.sleep(4); // Sometimes temperamental - doesn't
										// always give drop-down options in
										// search bar & causes JUnit test
										// failure
			TwitterPOM.searchBox(driver).sendKeys(Keys.DOWN, Keys.RETURN);
			test.log(LogStatus.INFO, "Searched for User");
			TimeUnit.SECONDS.sleep(5);
			TwitterPOM.unFollow(driver).click();
			System.out.println("Unfollowed user");
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot unfollow user");
		}
		
		if(TwitterPOM.follow(driver).getText().trim().equals("Follow")) {
			test.log(LogStatus.PASS, "Unfollow User Successful");
		} else {
			test.log(LogStatus.FAIL, "Unfollow User Unsuccessful");
		}

		try {
			TimeUnit.SECONDS.sleep(2);
			TwitterPOM.dropDownMenu(driver).click();
			TwitterPOM.logOut(driver).click();
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		title = driver.getTitle().trim();
		System.out.println(title);
		if (title.equals("Download the free Twitter app | Twitter")) {
			test.log(LogStatus.PASS, "Log Out");
		} else {
			test.log(LogStatus.FAIL, "Log Out");
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
