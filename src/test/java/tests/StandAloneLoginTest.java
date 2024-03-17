package tests;

import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneLoginTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.edverse.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("button[title='Log in']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#userloginform"))));
		
		//		driver.findElement(By.id("login_email")).sendKeys("vinay.sky182@ymail.com");
		//		driver.findElement(By.id("login_password")).sendKeys("vi@1994");
		//		driver.findElement(By.id("loginsubmit")).click();
		
		try (FileInputStream fip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\data\\AutoTestData.xlsx"))
		{
			try (XSSFWorkbook wb = new XSSFWorkbook(fip))
			{
				XSSFSheet xs = wb.getSheet("Test Sheet");
				
				int totalRows = xs.getPhysicalNumberOfRows();
				System.out.println("Total number of Rows =" + totalRows);
				
				int totalColumn = xs.getRow(0).getPhysicalNumberOfCells();
				System.out.println("Total number of Column =" + totalColumn);
				
				for (int i = 1; i < totalRows; i++)
				{
					Row row = xs.getRow(i);
					
	                String email = row.getCell(0).getStringCellValue();
	                String password = row.getCell(1).getStringCellValue();
	                
	                WebElement userEmail = driver.findElement(By.id("login_email"));
	                userEmail.clear();
	                userEmail.sendKeys(email);
	                
	                WebElement userPassword = driver.findElement(By.id("login_password"));
	                userPassword.clear();
	                userPassword.sendKeys(password);
	                
	                WebElement loginButton = driver.findElement(By.id("loginsubmit"));
	                loginButton.click();
				}	
			}
		}
		
	}

}
