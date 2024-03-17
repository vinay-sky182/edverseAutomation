package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws IOException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.edverse.com/");
		driver.manage().window().maximize();

		driver.findElement(By.cssSelector("button[title='Log in']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#userloginform"))));

		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("signup_email"))));

		WebElement emailInput = driver.findElement(By.id("signup_email"));
		// emailInput.clear();
		emailInput.sendKeys("ursula_hoeger@yahoo.com");

		WebElement passwordInput = driver.findElement(By.id("signup_password"));
		// passwordInput.clear();
		passwordInput.sendKeys("n5eae");

		WebElement firstNameInput = driver.findElement(By.id("signup_f_name"));
		// firstNameInput.clear();
		firstNameInput.sendKeys("Ursula");

		WebElement confirmPasswordInput = driver.findElement(By.id("signup_confirm_password"));
		// confirmPasswordInput.clear();
		confirmPasswordInput.sendKeys("n5eae");

		WebElement lastNameInput = driver.findElement(By.id("signup_l_name"));
		// lastNameInput.clear();
		lastNameInput.sendKeys("McCullough");

		WebElement organizationInput = driver.findElement(By.id("signup_institute"));
		// organizationInput.clear();
		organizationInput.sendKeys("ABC Inc.");

		WebElement cityInput = driver.findElement(By.id("signup_city"));
		// cityInput.clear();
		cityInput.sendKeys("New York");

		WebElement designationInput = driver.findElement(By.id("signup_designation"));
		// designationInput.clear();
		designationInput.sendKeys("Production Manager");

		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
		driver.findElement(By.id("recaptcha-anchor")).click();
		driver.switchTo().defaultContent();

		driver.findElement(By.id("signupsubmit")).click();

	}

}
