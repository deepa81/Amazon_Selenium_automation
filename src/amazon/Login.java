package amazon;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	
	
	

	public void invokeBrowser(WebDriver driver) {
		try {
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loginApp(WebDriver driver,String url,String userid, String Password){

		
		try {
			
			
			System.out.println(url +"  :  "+userid+"  ::"+Password);
			driver.get(url);
			Thread.sleep(3000);
			driver.findElement(By.id("login.password")).sendKeys(Password);
			driver.findElement(By.id("login.username")).sendKeys(userid);
			driver.findElement(By.id("login.button")).click();
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// TODO Auto-generated method stub

	
	}
public void logout(WebDriver driver){

		
		try {
			Thread.sleep(2000);
			driver.findElement(By.id("headermodule.logoutButton")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// TODO Auto-generated method stub

	
	}




	
	

}
