package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

	String screenshotSubFolderName;

	public void screenShot(String TestCase, WebDriver driver) {

		if (screenshotSubFolderName == null) {

			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			screenshotSubFolderName = ldt.format(dtf);
		}

		TakesScreenshot ts = (TakesScreenshot) driver; // Typecast the driver to make compatible
		File source = ts.getScreenshotAs(OutputType.FILE); // This is the Raw format of file.
		File destFile = new File("./Screenshot/" + screenshotSubFolderName + "/" + TestCase + ".png"); // Convert the
		// Raw format
		// into Human
		// Readable
		// format and
		// save in
		// Screenshot
		// folder with
		// the name.
		try {
			FileUtils.copyFile(source, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
