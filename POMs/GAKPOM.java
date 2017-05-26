package Files;
import org.openqa.selenium.*;

public class GAKPOM {
	private static WebElement element = null;
	
	public static WebElement logInBtn(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".glyphicon-user"));
		return element;
	}
	
	public static WebElement username(WebDriver driver) {
		element = driver.findElement(By.name("UserName"));
		return element;
	}
	
	public static WebElement password(WebDriver driver) {
		element = driver.findElement(By.name("Password"));
		return element;
	}
	
	public static WebElement confirmLogIn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='loginForm']/form/div[4]/div/input"));
		return element;
	}
	
	public static WebElement searchBox(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".form-control"));
		return element;
	}
}
