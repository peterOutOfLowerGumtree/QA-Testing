package Tests;
// Website seems to change location of xpath elements each time go away then come back a while later to try to load it up with a JUnit test.
//These values are correct at time of writing.

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Files.GAKPOM;

public class Scenario1 {
	static WebDriver driver;

	@Before
	public void loadBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Desktop\\selenium\\SeleniumFiles\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.gak.co.uk");
	}

	
	@Test
	public void testAddToBasket() {
		try {
			// Go to login
			GAKPOM.logInBtn(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Log in
			GAKPOM.username(driver).sendKeys("jwhiteley0@icloud.com");
			GAKPOM.password(driver).sendKeys("Alokpushesback.123");
			GAKPOM.confirmLogIn(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Add item to basket
			GAKPOM.searchBox(driver).sendKeys("Ibanez JEM7V-WH (White)", Keys.ENTER);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pageContentContainer']/div/div[1]/div[2]/div[1]/div[3]/div[3]/div[4]/div/div[2]/a"))).click();
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@AfterClass
	public static void closeAll() {
		System.out.println("TEST COMPLETE");
		driver.quit();
	}
}
