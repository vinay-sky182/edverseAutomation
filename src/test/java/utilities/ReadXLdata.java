package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import base.Base;

public class ReadXLdata extends Base {

	public Object[][] getData(String sheetName) throws IOException {

		fip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\data\\AutoTestData.xlsx");

		try (XSSFWorkbook wb = new XSSFWorkbook(fip)) {
			XSSFSheet xs = wb.getSheet("sheetName");

			int totalRows = xs.getPhysicalNumberOfRows();
			System.out.println("Total number of Rows =" + totalRows);

			int totalColumn = xs.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Total number of Column =" + totalColumn);

			Object tData[][] = new Object[totalRows - 1][totalColumn];

			for (int i = 1; i < totalRows; i++) {
				for (int j = 0; j < 2; j++) {
					tData[i - 1][j] = xs.getRow(i).getCell(j).getStringCellValue().trim();
					System.out.print(tData[i - 1][j] + " ");
				}
				System.out.println();

				/*
				 * Row row = xs.getRow(i);
				 * 
				 * String email = row.getCell(0).getStringCellValue(); String password =
				 * row.getCell(1).getStringCellValue();
				 * 
				 * WebElement userEmail = driver.findElement(By.id("login_email"));
				 * userEmail.clear(); userEmail.sendKeys(email);
				 * 
				 * WebElement userPassword = driver.findElement(By.id("login_password"));
				 * userPassword.clear(); userPassword.sendKeys(password);
				 * 
				 * WebElement loginButton = driver.findElement(By.id("loginsubmit"));
				 * loginButton.click();
				 */
			}
			return tData;
		}

	}

}
