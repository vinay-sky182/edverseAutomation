package tests;

import java.io.IOException;
import java.time.Duration;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class StandAloneTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//		
		//		WebDriver driver = new ChromeDriver();
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//		driver.get("https://rahulshettyacademy.com/client");



		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.edverse.com/");
		driver.manage().window().maximize();

		driver.findElement(By.cssSelector("button[title='Log in']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#userloginform"))));

		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".btn.already-login"))));

		//		driver.findElement(By.cssSelector(".btn.already-login")).click();

		//		driver.findElement(By.cssSelector("#signup_email")).sendKeys(args)

		try (Workbook wb = new XSSFWorkbook())
		{
			Sheet xs = wb.createSheet("Signup_Data");

			Row headerRow = xs.createRow(0);

			headerRow.createCell(0).setCellValue("Email");
			headerRow.createCell(1).setCellValue("Password");
			headerRow.createCell(2).setCellValue("FirstName");
			headerRow.createCell(3).setCellValue("ConfirmPassword");
			headerRow.createCell(4).setCellValue("LastName");
			headerRow.createCell(5).setCellValue("Organization");
			headerRow.createCell(6).setCellValue("City");
			headerRow.createCell(7).setCellValue("Designation");

			// Generate data for multiple signup forms

			Faker faker = new Faker();
			for (int i = 0; i < 5; i++) 
			{
				// Generate random data for each field
				String email = faker.internet().emailAddress();
				String password = faker.internet().password(5, 5, true, true, true);
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				String organization = faker.company().name();
				String city = faker.address().city();
				String designation = faker.job().position();

				// Fill out the signup form with fake data
				WebElement emailInput = driver.findElement(By.id("signup_email"));
				emailInput.clear();
				emailInput.sendKeys(email);

				WebElement passwordInput = driver.findElement(By.id("signup_password"));
				passwordInput.clear();
				passwordInput.sendKeys(password);

				WebElement firstNameInput = driver.findElement(By.id("signup_f_name"));
				firstNameInput.clear();
				firstNameInput.sendKeys(firstName);

				WebElement confirmPasswordInput = driver.findElement(By.id("signup_confirm_password"));
				confirmPasswordInput.clear();
				confirmPasswordInput.sendKeys(password);

				WebElement lastNameInput = driver.findElement(By.id("signup_l_name"));
				lastNameInput.clear();
				lastNameInput.sendKeys(lastName);

				WebElement organizationInput = driver.findElement(By.id("signup_institute"));
				organizationInput.clear();
				organizationInput.sendKeys(organization);

				WebElement cityInput = driver.findElement(By.id("signup_city"));
				cityInput.clear();
				cityInput.sendKeys(city);

				WebElement designationInput = driver.findElement(By.id("signup_designation"));
				designationInput.clear();
				designationInput.sendKeys(designation);

				WebElement captchaCheckBox = driver.findElement(By.id("recaptcha-anchor"));
				captchaCheckBox.clear();
				captchaCheckBox.click();

				WebElement agree_termCheckBox = driver.findElement(By.id("signup_agree_term"));
				agree_termCheckBox.clear();
				agree_termCheckBox.click();


				// Click on the submit button
				WebElement submitButton = driver.findElement(By.id("signupsubmit"));
				submitButton.click();

				// Fill out the data in the Excel sheet
				Row dataRow = xs.createRow(i + 1);
				dataRow.createCell(0).setCellValue(email);
				dataRow.createCell(1).setCellValue(password);
				dataRow.createCell(2).setCellValue(firstName);
				dataRow.createCell(3).setCellValue(password); // Confirm password same as password
				dataRow.createCell(4).setCellValue(lastName);
				dataRow.createCell(5).setCellValue(organization);
				dataRow.createCell(6).setCellValue(city);
				dataRow.createCell(7).setCellValue(designation);

				// Navigate back to the signup form page for the next signup
				driver.navigate().back();
			}


			// Write the workbook content to a file
			try (FileOutputStream fileOut = new FileOutputStream("signup_form_data.xlsx")) {
				wb.write(fileOut);
				System.out.println("Excel file created successfully!");
			}catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}









}

