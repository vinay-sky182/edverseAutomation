package tests;

import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import utilities.ScreenshotUtility;
import utilities.ReadXLdata;

public class loginTest extends Base{
	
	@BeforeMethod
	public void open() throws IOException 
	{
		setUp();
		driver.get(prop.getProperty("url")); 
	}
	
	@AfterMethod
	public void closure(ITestResult result) throws IOException 
	{
		
		if (result.getStatus()==ITestResult.FAILURE) {  // ITestResult Interface utility, It is the Dependency injection concept.
			
			ScreenshotUtility su = new ScreenshotUtility();
			su.screenShot(result.getName(), driver);
		}
		driver.quit();
	}
	
	@Test(dataProvider = "drivertest")
	public void login(String uEmail, String uPass) 
	{
		driver.findElement(By.cssSelector("button[title='Log in']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#userloginform"))));
		
        WebElement userEmail = driver.findElement(By.id("login_email"));
        userEmail.sendKeys(uEmail);
        
        WebElement userPassword = driver.findElement(By.id("login_password"));
        userPassword.sendKeys(uPass);
        
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[href*='logout']"))));

        WebElement logoutButton = driver.findElement(By.cssSelector("a[href*='logout']"));


        Assert.assertTrue(logoutButton.isDisplayed());

	}
	
	@DataProvider(name = "drivertest")
	public Object[][] dataSheet() throws IOException {

		ReadXLdata xd = new ReadXLdata();
		Object[][] data = xd.getData("Test Sheet");
		return data;

	}

}
