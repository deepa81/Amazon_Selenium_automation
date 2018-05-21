package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage {

	@FindBy(id="nav-cart-count")
	public WebElement CartCountNo;
	
	@FindBy(xpath="//*[@id=\"activeCartViewForm\"]/div[2]/div/div[4]/div/div[1]/div/div/div[2]/ul/li[1]/span/a/span")
	public WebElement ProductName;
	
	@FindBy(xpath="//*[@id=\"activeCartViewForm\"]/div[2]/div/div[4]/div/div[2]/p/span")
	public WebElement Price;
	
	@FindBy(xpath="a-autoid-0-announce")
	public WebElement count;
	
	
	
	public String getCartCountNo()
	{
		return CartCountNo.getText();
	}
	public void clickCartNo()
	{
		 CartCountNo.click();
	}
	public String getTextProductName()
	{
		return ProductName.getText();
	}
	public String getPrice()
	{
		return Price.getText();
	}
	public String getCount()
	{
		return count.getText();
	}
	
}
