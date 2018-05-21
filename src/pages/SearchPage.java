package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {
	@FindBy(xpath="//*[@id=\"nav-search\"]/form/div[2]/div/input")
	public WebElement SearchBnt;
	@FindBy(xpath="//*[@id=\"result_0\"]/div/div/div/div[2]/div[1]/div[1]/a/h2")
	public WebElement ProductLink;
	
	@FindBy(id="twotabsearchtextbox")
	public WebElement SearchString;
	
	public void enterSearchValue(String SearchText) {
		SearchString.sendKeys(SearchText);
	}
	public void clickOnSearch() {
		SearchBnt.click();
	}
	public void clickOnProduct() {
		ProductLink.click();
	}
	
	
	
}
