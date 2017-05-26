package Files;
import org.openqa.selenium.*;

public class TwitterPOM {
	private static WebElement element = null;
	
	/*---------GENERIC---------*/
	
	public static WebElement logInBtn(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".js-login"));
		return element;
	}
	
	public static WebElement username(WebDriver driver) {
		element = driver.findElement(By.name("session[username_or_email]"));
		return element;
	}
	
	public static WebElement password(WebDriver driver) {
		element = driver.findElement(By.name("session[password]"));
		return element;
	}
	
	public static WebElement confirmLogin(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='login-dialog-dialog']/div[2]/div[2]/div[2]/form/input[1]"));
		return element;
	}
	
	public static WebElement dropDownMenu(WebDriver driver) {
		element = driver.findElement(By.id("user-dropdown-toggle"));
		return element;
	}
	
	public static WebElement logOut(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='signout-button']/button"));
		return element;
	}
	
	
	
	/*---------TWEET---------*/
	
	public static WebElement tweetBox(WebDriver driver) {
		element = driver.findElement(By.name("tweet"));
		return element;
	}
	
	public static WebElement sendTweet(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='timeline']/div[2]/div/form/div[3]/div[2]/button"));
		return element;
	}
	
	
	
	/*---------ADD PIC---------*/
	
	public static WebElement addPic(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='choose-photo']/a/div"));
		return element;
	}
	
	public static WebElement choosePic(WebDriver driver) {
		element = driver.findElement(By.name("media[]"));
		return element;
	}
	
	public static WebElement confirmPic(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".profile-image-save"));
		return element;
	}
	
	
	
	/*---------REMOVE PIC---------*/
	
	public static WebElement editProfile(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".edit-button"));
		return element;
	}
	
	public static WebElement editPic(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".ProfileAvatarEditing-changeAvatarHelp"));
		return element;
	}
	
	public static WebElement removePic(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='photo-delete-image']/button"));
		return element;
	}
	
	public static WebElement removeYes(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".ok-btn"));
		return element;
	}
	
	public static WebElement saveChanges(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".ProfilePage-saveButton"));
		return element;
	}
	
	
	
	/*---------FOLLOW USER---------*/
	
	public static WebElement searchBox(WebDriver driver) {
		element = driver.findElement(By.id("search-query"));
		return element;
	}
	
	public static WebElement follow(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".follow-text"));
		return element;
	}
	
	
	
	/*---------MESSAGE USER---------*/
	
	public static WebElement messageBox(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".DMButton-icon"));
		return element;
	}
	
	public static WebElement writeMessage(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='tweet-box-dm-conversation']"));
		return element;
	}
	
	
	
	/*---------UNFOLLOW USER---------*/
	
	public static WebElement unFollow(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".following-text"));
		return element;
	}
}
