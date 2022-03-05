package pomPages;

import org.openqa.selenium.By;

import scripts.BaseTest;

public class SignUpPage extends BaseTest {

	By fristName = By.id("in.app.myeventgofer:id/editText1");

	By lastName = By.id("in.app.myeventgofer:id/edLatsName");

	By userEmail = By.id("in.app.myeventgofer:id/edEmail");

	By userPhoneNo = By.id("in.app.myeventgofer:id/edttxtphonenumber");

	By userCity = By.id("in.app.myeventgofer:id/edCity");

	By userZipCode = By.id("in.app.myeventgofer:id/edZipcode");

	By passwordTF = By.id("in.app.myeventgofer:id/edPswd");

	By chechBOX = By.id("in.app.myeventgofer:id/checkboxcondn");

	By signUP_BTN = By.id("in.app.myeventgofer:id/mBtnSignUp");

	public void firstName_TF() {
		driver.findElement(fristName).sendKeys("rudra");

	}

	public void lastName_TF() {
		driver.findElement(lastName).sendKeys("roy");
	}

	public void userEmail_TF() {
		driver.findElement(userEmail).sendKeys("rudraray57@gmail.com");

	}

	public void userPhoneNo_TF() {
		driver.findElement(userPhoneNo).sendKeys("8602119284");

	}

	public void userCity_TF() {
		driver.findElement(userCity).sendKeys("Indore");

	}

	public void userZipCode_TF() {
		driver.findElement(userZipCode).sendKeys("452020");

	}

	public void password_TF() {
		driver.findElement(passwordTF).sendKeys("ashish1234");
	}

	public void checkBox() {
		driver.findElement(chechBOX).click();
	}

	public void signUp_BTN() {
		driver.findElement(signUP_BTN).click();
	}

}
