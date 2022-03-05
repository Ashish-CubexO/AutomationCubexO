package scripts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericLib.ExtentReportLog;
import genericLib.Screenshot;
import genericLib.WebDriverUtilites;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	public static AppiumDriver<AndroidElement> driver;
	public URL url;
	public WebDriverUtilites utilites = new WebDriverUtilites();

	public DesiredCapabilities dc;
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public Markup m;

	@BeforeTest
	public void openApp() throws MalformedURLException, InterruptedException {

		dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		dc.setCapability(CapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.app.myeventgofer.permissions.RunTimePrmsn");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.app.myeventgofer");
		dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
		dc.setCapability(MobileCapabilityType.APP,
				"//Users//apple//eclipse-workspace//myeventgogerApp//src//test//resources//apps//Android//myeventgofer.apk");
		url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<>(url, dc);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		System.out.println("App opening Successfull");
		Thread.sleep(10000);

	}

	@BeforeSuite
	public void reportSetup() {

		spark = new ExtentSparkReporter("src/test/resources/htmlReport/ExtentReport/extent.html");

		spark.config().setEncoding("utf-8");
		spark.config().setDocumentTitle("Automation Reports");
		spark.config().setReportName("Automation Test Results");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(spark);
		System.out.println("Prepraing Extent Report....");
	}

	@AfterSuite
	public void reportTearDown() {

		extent.flush();
		System.out.println("Extent Report is Genrated--->>");
		driver.close();
		System.out.println("Closing the Driver!!");
	}

	@AfterTest(enabled = false)
	public void closeApp(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		if (result.getStatus() == ITestResult.FAILURE) {

			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occured click to see Deatils:"
					+ "</font><b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>\n");

			String path = takeScrrenshot(result.getMethod().getMethodName());
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

		// driver.quit();
	}

	public String takeScrrenshot(String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("/src/test/resources/photo/Screenshot:/");
		new File(directory).mkdir();
		String path = directory + fileName;

		try {
			File screenshot = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("******************************************");
			System.out.println("Screenshot stored at: " + path);
			System.out.println("******************************************");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(",", "_" + ".png");
		return fileName;

	}

}
