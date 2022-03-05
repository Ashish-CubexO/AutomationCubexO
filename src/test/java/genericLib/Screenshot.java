package genericLib;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import scripts.BaseTest;

public class Screenshot extends BaseTest {


	public String takeScrrenshot(String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("//src//test//resources//photo//Screenshot:");
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
