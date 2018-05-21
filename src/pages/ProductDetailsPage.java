package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage {

	
	
	@FindBy(id="productTitle")
	public WebElement ProductTitle;
	
	@FindBy(id="priceblock_ourprice")
	public WebElement ProductPrice;
	
	@FindBy(id="quantity")
	public WebElement Quantity;
	
	@FindBy(id="add-to-cart-button")
	public WebElement AddToCartBtn;
	
	public String getProductText() {
		return ProductTitle.getText();
	}
	public String getProductPrice() {
		return ProductPrice.getText();
	}
	
	public void clickQuantity() {
		 Quantity.click();
	}
	public void sendQuantity(String quantity) {
		Quantity.sendKeys(quantity);	;
	}
	
	public void clickAddToCart() {
		AddToCartBtn.click();
	}
}
