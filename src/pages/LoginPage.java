package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.Extentreports.ExtentManager;
import utils.Extentreports.ExtentTestManager;

public class LoginPage {

	@FindBy(xpath="//*[@id=\"nav-signin-tooltip\"]/a/span")
	public WebElement SignInLink;
	@FindBy(id="ap_email")
	public WebElement Login_email;
	@FindBy(id="continue")
	public WebElement Continue_btn;
	@FindBy(id="ap_password")
	public WebElement Login_Password;
	@FindBy(id="signInSubmit")
	public WebElement signinSubmit;
	
	@FindBy(xpath="//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span")
	public WebElement ErrorMessage;
	
	@FindBy(xpath="//*[@id=\"nav-link-accountList\"]/span[1]")
	public WebElement UserName;
	
	@FindBy(id="nav-item-signout-sa")
	public WebElement Logout; 
	
	public void clickLogout() {
		Logout.click();
	}
	
	public void clickOnAccount() {
	//	ExtentTestManager.getTest().setDescription("Login Flow");
		SignInLink.click();
	}
	public void EnterEmailID(String Email_ID) {
		Login_email.sendKeys(Email_ID);
	}
	public void EnterPassword(String Password) {
		Login_Password.sendKeys(Password);
	}
	public void clickContinue() {
		Continue_btn.click();
	}
	public void clickSubmit() {
		signinSubmit.click();
	}
	public String getTextErrorMsg()
	{
		return ErrorMessage.getText();
	}
	public String getTextUserName()
	{
		return UserName.getText();
	}
	public void iLogintoAmazon(String Email, String pass) {
		
	}
	
	
}
