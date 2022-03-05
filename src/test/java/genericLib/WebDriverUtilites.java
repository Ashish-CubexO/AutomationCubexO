package genericLib;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WebDriverUtilites {

	public void dropDown(MobileElement ele,String text) {
		Select s=new Select(ele);
		s.selectByVisibleText(text);
	}

	public void mouseHover(AppiumDriver<WebElement> driver,WebElement ele) {
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
	}
	public void doubleClick(AppiumDriver<WebElement> driver,MobileElement ele) {
		Actions a=new Actions(driver);
		a.doubleClick(ele).perform();
	}
	public void rightClick(AppiumDriver<WebElement> driver,MobileElement ele) {
		Actions a=new Actions(driver);
		a.contextClick(ele).perform();
	}
	public void dragAndDrop(AppiumDriver<WebElement> driver,MobileElement source,MobileElement target) {
		Actions a=new Actions(driver);
		a.dragAndDrop(source, target);
	}

	public void switchToframe(AppiumDriver<WebElement> driver) {
		driver.switchTo().frame(0);

	}

	public void switchBackToframe(AppiumDriver<WebElement> driver) {
		driver.switchTo().defaultContent();

	}

	public void alertPopup(AppiumDriver<WebElement> driver) {
		driver.switchTo().alert().accept();
	}

	public void navBack(AppiumDriver<WebElement> driver) {
		driver.navigate().back();
	}
	public void navForword(AppiumDriver<WebElement> driver) {
		driver.navigate().forward();
	}
	public void navRefresh(AppiumDriver<WebElement> driver) {
		driver.navigate().refresh();
	}

	public void switchTabs(AppiumDriver<WebElement> driver) {
		Set<String> child = driver.getWindowHandles();
		for (String b : child) {
			driver.switchTo().window(b);
		}
	}

	public void scrollBar(AppiumDriver<WebElement> driver,int x,int y) {
		JavascriptExecutor js=driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
}
