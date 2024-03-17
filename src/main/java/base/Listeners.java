package base;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.ScreenshotUtility;

public class Listeners extends ScreenshotUtility implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		//		screenShot(result.getMethod().getMethodName(), driver);

	}

}
