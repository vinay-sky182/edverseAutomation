package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	protected WebDriver driver;
	protected Properties prop = new Properties();
	protected FileInputStream fip;

	public void setUp() throws IOException 
	{

		if (driver == null) 
		{
			fip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\utilities\\config.properties");

			prop.load(fip);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();

		} 
		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
		} 
		else 
		{
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	/*
	 * public void tearDown() { driver.close(); }
	 */

}
