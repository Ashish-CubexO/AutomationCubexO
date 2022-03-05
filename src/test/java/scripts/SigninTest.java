package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import pomPages.SignInPage;
import pomPages.SignUpPage;

public class SigninTest extends BaseTest {

	@Test
	public void tc0() {
		SignInPage sp = new SignInPage();

		sp.email_TF();
		sp.pass_TF();
		sp.signIn_BTN();
		sp.signUp_BTN();

	}

	@Test(priority = 1)
	public void tc1() {

		// user Registration
		SignUpPage sUp = new SignUpPage();

		sUp.firstName_TF();
		sUp.lastName_TF();
		sUp.userEmail_TF();
		sUp.userPhoneNo_TF();
		sUp.userCity_TF();
		sUp.userZipCode_TF();
		sUp.password_TF();
		sUp.checkBox();
		sUp.signUp_BTN();

	}

}
