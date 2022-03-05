package genericLib;

import java.util.Arrays;

import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import scripts.BaseTest;

public class ExtentReportLog extends BaseTest {
	
	Screenshot sc;
	public void extentReporting(ITestResult result) {

		sc = new Screenshot();
		String methodName = result.getMethod().getMethodName();
		if (result.getStatus() == ITestResult.FAILURE) {

			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occured click to see Deatils:"
					+ "</font><b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>\n");

			String path = sc.takeScrrenshot(result.getMethod().getMethodName());
			extentTest.fail("<b><font color=red>" + "Scrrenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			String logText = "<b>Test Method " + methodName + "Failed</b>";
			m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String logText = "<b>Test Method " + methodName + "Successful</b>";
			m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);

		} else if (result.getStatus() == ITestResult.SKIP) {
			String logText = "<b>Test Method " + methodName + "Skipped</b>";
			m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);

		}

		//driver.quit();
	}
}
