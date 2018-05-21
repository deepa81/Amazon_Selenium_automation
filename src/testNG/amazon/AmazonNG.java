package testNG.amazon;

import org.testng.annotations.Test;

import amazon.AmazonBean;
import amazon.AmazonLogin;
import amazon.Login;
import pages.CartPage;
import pages.LaunchAmazon;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.Extentreports.ExtentManager;
import utils.Extentreports.ExtentTestManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

public class AmazonNG {
	protected WebDriver driver;
	static AmazonLogin login = new AmazonLogin();
	static Object[][] dataSet = null;
	int count = 1;

	@DataProvider(name = "amazon")
	public static Object[][] getData() {
		// ExtentTestManager.getTest().setDescription("Testing");
		System.out.println("inside fileread");
		try {

			ArrayList<AmazonBean> bean = login.fileRead();

			if (bean != null) {
				dataSet = new Object[bean.size()][2];
				for (int i = 0; i < bean.size(); i++) {
					System.out.println("int i ::" + i);
					dataSet[i][1] = bean.get(i);
					dataSet[i][0] = bean.get(i).getScenario();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSet;
	}

	@BeforeTest
	public void beforeTest() {

		System.out.println("Inside invoke Browser");
		try {
			Login login = new Login();
			System.setProperty("webdriver.chrome.driver",
					"D:\\Deepa\\Selenium\\trial\\ExtentReports\\chromedriver_win32 (3)\\chromedriver.exe");
			driver = new ChromeDriver();
			login.invokeBrowser(driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "amazon", description = "1.Launch www.amazon.com page and validate page header")
	public void t1LaunchAmazonPage(String Scenario, AmazonBean bean) {

		try {
			System.out.println("Inside 1");
			bean.setStatus("FAIL");
			login.launch(driver, bean);
			LaunchAmazon Lp = PageFactory.initElements(driver, LaunchAmazon.class);
			System.out.println("Logo Text"+Lp.getTextLogo());
			Assert.assertEquals(Lp.getTextLogo(),bean.getExpected1());
			bean.setActual1(Lp.getTextLogo());
			
			Assert.assertNotNull(Lp.Header);
		
			bean.setStatus("PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			login.writeFile(bean.getSlNo(), bean);
		System.out.println("Status::" + bean.getStatus());
		System.out.println("Exiting 1");
		}
	}

	@Test(dataProvider = "amazon", description = "2.Click on login link and Provide incorrect username and validate error.")
	public void t2LoginIncorrectUsername(String Scenario, AmazonBean bean) {
		System.out.println("Inside 2");
		bean.setStatus("FAIL");
		try {
			login.login(driver, bean.getUserID(), bean.getPassword());
			LoginPage Lp = PageFactory.initElements(driver, LoginPage.class);
			Assert.assertEquals(Lp.getTextErrorMsg(),bean.getExpected1());
			bean.setActual1(Lp.getTextErrorMsg());
			bean.setStatus("PASS");
			System.out.println("Exiting 2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		System.out.println("Status::" + bean.getStatus());
		}
	}

	@Test(dataProvider = "amazon", description = "3.Provide incorrect password and validate error.")
	public void t3LoginIncorrectPassword(String Scenario, AmazonBean bean) {
		System.out.println("Inside 3");
		bean.setStatus("FAIL");
		try {
			login.login(driver, bean.getUserID(), bean.getPassword());

			LoginPage Lp = PageFactory.initElements(driver, LoginPage.class);
			Assert.assertEquals(Lp.getTextErrorMsg(),bean.getExpected1());
			bean.setActual1(Lp.getTextErrorMsg());
			bean.setStatus("PASS");
			System.out.println("Exiting 3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		System.out.println("Status::" + bean.getStatus());
		}
	}

	@Test(dataProvider = "amazon", description = "4.Provide correct username/password and validate home page header.")
	public void t4LogincorrectPassword(String Scenario, AmazonBean bean) {
		System.out.println("Inside 4");
		bean.setStatus("FAIL");
		try {
			login.login(driver, bean.getUserID(), bean.getPassword());
			LoginPage Lp = PageFactory.initElements(driver, LoginPage.class);

			Assert.assertEquals(Lp.getTextUserName(),bean.getExpected1());
			bean.setActual1(Lp.getTextUserName());
			System.out.println("Exiting 4");
			bean.setStatus("PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		System.out.println("Status::" + bean.getStatus());
		}

	}

	@Test(dataProvider = "amazon", description = "5.Search for iphone x 64 gb and click on 1st product and move to product page.")
	public void t5searchAmazon(String scenario, AmazonBean bean) {

		try {
			System.out.println(bean.getSlNo() + "scenario11  ::::  " + scenario);
			bean.setStatus("FAIL");

			System.out.println("Inside 5");
			login.searchPage(driver, bean);
			bean.setStatus("PASS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		}
	}

	@Test(dataProvider = "amazon", description = "6.Capture product name and price and add 1 quantity to basket")
	public void t6AddOnProd(String scenario, AmazonBean bean) {

		try {
			System.out.println("Inside 6");
			System.out.println("scenario666  ::::  " + scenario);
			Thread.sleep(1000);
			ProductDetailsPage Lp = PageFactory.initElements(driver, ProductDetailsPage.class);
			Assert.assertEquals(Lp.getProductText(), bean.getExpected1());
			Thread.sleep(1000);
			Assert.assertEquals(Lp.getProductPrice(), bean.getExpected2());
			Thread.sleep(2000);
			bean.setActual1(Lp.getProductText());
			bean.setActual2(Lp.getProductPrice());
			bean.setStatus("PASS");
			login.addToCart(driver, bean);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		}
	}

	@Test(dataProvider = "amazon", description = "7.Validate product is added into basket on product page")
	public void t7VerifyProduct(String scenario, AmazonBean bean) {

		try {
			System.out.println("Inside 7");
			bean.setStatus("FAIL");
			System.out.println("scenario  ::::  " + scenario);
			CartPage Lp = PageFactory.initElements(driver, CartPage.class);
			System.out.println("Value 7" + Lp.getCartCountNo());
			Assert.assertNotEquals(Lp.getCartCountNo(), "0");
			bean.setActual1(Lp.getCartCountNo());
			Thread.sleep(1000);
			bean.setStatus("PASS");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		}
	}

	@Test(dataProvider = "amazon", description = "8.Vaidate product price and name on basket page.")
	public void t8validatebasket(String scenario, AmazonBean bean) {
		try {
			System.out.println("Inside 8");
			CartPage Lp = PageFactory.initElements(driver, CartPage.class);
			Lp.clickCartNo();
		
			Thread.sleep(1000);
			System.out.println("asserssion 1" + Lp.getTextProductName() + ":::" + bean.getExpected1());

			Assert.assertEquals( Lp.getTextProductName() , bean.getExpected1());
			bean.setActual1( Lp.getTextProductName());

			System.out.println("asserssion 2" + Lp.getPrice() + ":::" + bean.getExpected2());
			Assert.assertEquals(Lp.getPrice(),	bean.getExpected2());
			bean.setActual2(Lp.getPrice());
			
			Thread.sleep(1000);
			bean.setStatus("PASS");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		}
	}

	@Test(dataProvider = "amazon", description = "9.Logout and login again validate product in basket is present or not.")
	public void t9reLoginAndValidate(String scenario, AmazonBean bean) {
		try {
			bean.setStatus("FAIL");

			System.out.println("Inside 9");
			login.logout(driver);
			Thread.sleep(1000);
			//login.launch(driver, bean);
		
			login.login(driver, bean.getUserID(), bean.getPassword());
			Thread.sleep(1000);
			CartPage Cp = PageFactory.initElements(driver, CartPage.class);
			
			
			Cp.clickCartNo();
			System.out.println("asserssion 1" + Cp.getTextProductName() + bean.getExpected1());

			Assert.assertEquals(Cp.getTextProductName(), bean.getExpected1());
			bean.setActual1(Cp.getTextProductName());

			System.out.println("asserssion 2" + Cp.getPrice()+ ":::" + bean.getExpected2());
			Assert.assertEquals(Cp.getPrice(), bean.getExpected2());
			bean.setActual2(Cp.getPrice());
			Thread.sleep(1000);
			bean.setStatus("PASS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		login.writeFile(bean.getSlNo(), bean);
		}
	}


	@AfterTest
	public void afterTest() {

		try {

			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

}
