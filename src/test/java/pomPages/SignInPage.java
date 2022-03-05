package pomPages;

import org.openqa.selenium.By;

import scripts.BaseTest;

public class SignInPage extends BaseTest {

	By emailText = By.id("in.app.myeventgofer:id/edEmail");

	By passText = By.id("in.app.myeventgofer:id/edPswd");

	By signinlBTN = By.id("in.app.myeventgofer:id/mBtnSignIn");

	By signUpBTN = By.id("in.app.myeventgofer:id/mTxtViewSignUp");

	public void email_TF() {
		driver.findElement(emailText).sendKeys("ashish");
	}

	public void pass_TF() {
		driver.findElement(passText).sendKeys("Shshvdchvjdh");
	}

	public void signIn_BTN() {
		driver.findElement(signinlBTN).click();
	}

	public void signUp_BTN() {
		driver.findElement(signUpBTN).click();
	}

}
