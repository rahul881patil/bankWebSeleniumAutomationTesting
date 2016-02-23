package loginFunctionality;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class loginEndToEnd {

	private FirefoxDriver driver = null;
	private String url = "http://www.demo.guru99.com/V4/";
	private String username = "mngr30352";
	private String password = "anUpepy";
	private WebElement formLogin = null;
	
	
	@BeforeTest
	public void setApplication(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		formLogin = driver.findElement(By.name("frmLogin"));
	}
	
	@Test
	public void getName(){
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
	}
	
	@Test(priority = 0)
	public void verifyErrorMessage(){
		
		WebElement userName = formLogin.findElement(By.name("uid"));
		WebElement password = formLogin.findElement(By.name("password"));
		
		userName.click(); password.click();
		Assert.assertEquals(driver.findElement(By.id("message23")).getText(), "User-ID must not be blank");
		password.click(); userName.click();
		Assert.assertEquals(driver.findElement(By.id("message18")).getText(), "Password must not be blank");
		
	}
	
	@Test(priority = 1)
	public void checkButton(){
		
		WebElement userId = driver.findElement(By.name("uid"));
		userId.sendKeys("wfvfeve");
		driver.findElement(By.name("btnReset")).click();;
		Assert.assertEquals(userId.getText(), "");

		
	}
	
	@Test(priority = 2)
	public void loginBank(){
		
		formLogin.findElement(By.name("uid")).sendKeys(username);
		formLogin.findElement(By.name("password")).sendKeys(password);
		formLogin.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		driver.navigate().back();
	}
	
	@Test(priority = 3)
	public void loginButton(){
		try{
			driver.findElement(By.name("btnLogin")).click();
		}catch(UnhandledAlertException f ){
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "User or Password is not valid");
			alert.dismiss();
		}
	}

	@AfterTest
	public void closeBrowser(){
		
		driver.close();
		System.exit(0);
	}

}
