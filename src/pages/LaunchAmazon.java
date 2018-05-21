package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.Extentreports.ExtentTestManager;

public class LaunchAmazon {

	
	@FindBy(id="nav-logo")
	public WebElement Logo;
	@FindBy(xpath="//*[@id=\\\"a-page\\\"]/header")
	public WebElement Header;
	
	public String getTextLogo() {
	//	ExtentTestManager.getTest().setDescription("Launch");
		return Logo.getText();
	}
}
