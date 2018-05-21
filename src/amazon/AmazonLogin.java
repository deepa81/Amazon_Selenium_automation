package amazon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.jna.platform.unix.X11.Font;

import org.testng.Assert;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AmazonLogin {
	
	
	public ArrayList<AmazonBean> fileRead(){

		// TODO Auto-generated method stub
		AmazonBean  amazonBean;
		ArrayList<AmazonBean> dataSet = null;
		
		
	      try {	    	//  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:/Deepa/Selenium/trial/ExtentReports/Amazon_Scenario.xls"));
	    	  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:/Amazon_Scenario.xls"));
	    	  HSSFWorkbook wb = new HSSFWorkbook(fs);
	    	  int noOfSheets = wb.getNumberOfSheets();
	  		//System.out.println("Sheet Size:"+noOfSheets);

	  		for (int sheet =0; sheet<noOfSheets; sheet++)
	  		{	
	  			HSSFSheet hsSheetAttrib = wb.getSheetAt(sheet);
	  	//		System.out.println("Sheet Name:"+hsSheetAttrib.getSheetName());
	  			if (hsSheetAttrib != null)
				{
	  				dataSet = new ArrayList<AmazonBean>();
	  				System.out.println("No. Of Rows :"+hsSheetAttrib.getPhysicalNumberOfRows());
					for (int rowIndex=1; rowIndex < hsSheetAttrib.getPhysicalNumberOfRows(); rowIndex++)
					{
						if (hsSheetAttrib.getRow(rowIndex) != null)
						{
							
							HSSFRow hsSheetAttribRow = hsSheetAttrib.getRow(rowIndex);
							System.out.println("cell2"+hsSheetAttribRow.getCell(2));
							if(("YES").equals(hsSheetAttribRow.getCell(2).getStringCellValue()))
							{
								System.out.println(hsSheetAttribRow.getCell(0)+"hsSheetAttribRow.getCell(4)"+hsSheetAttribRow.getCell(2)+"::"+hsSheetAttribRow.getCell(12).getStringCellValue());
								amazonBean = new AmazonBean();
								
								amazonBean.setURL(""+hsSheetAttribRow.getCell(11));
								amazonBean.setUserID(""+hsSheetAttribRow.getCell(12));
								amazonBean.setPassword(""+hsSheetAttribRow.getCell(13));
								amazonBean.setSearchString(""+hsSheetAttribRow.getCell(14));
								System.out.println(" Quantity :::"+(int) hsSheetAttribRow.getCell(15).getNumericCellValue());
								amazonBean.setQuantity(""+(int) hsSheetAttribRow.getCell(15).getNumericCellValue());
								amazonBean.setExpected1(""+hsSheetAttribRow.getCell(5));
								
								 NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
							        String currency = format.format(hsSheetAttribRow.getCell(7).getNumericCellValue());
							    	amazonBean.setExpected2(currency);
							    
								
								System.out.println(" expected2 :::"+amazonBean.getExpected2());
								
								amazonBean.setExpected3(""+hsSheetAttribRow.getCell(9));
								amazonBean.setScenario(""+hsSheetAttribRow.getCell(0));
								amazonBean.setSlNo((int)hsSheetAttribRow.getCell(3).getNumericCellValue());
								dataSet.add(amazonBean);
							}
							
						}
					}
				}
	          }
	  		
	  		
	      }
	      catch(Exception e)
	      {
	    	  System.out.println("Error"+e.getStackTrace());
	      e.printStackTrace();
	      }
	       
	      return dataSet;
	
		
	
		
	}
	
	
	public void launch(WebDriver driver,AmazonBean bean){
		driver.get("https://www.amazon.com/");
	
		
				
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void login(WebDriver driver, String username, String passWord) throws Exception {
		
		LoginPage Lp = PageFactory.initElements(driver, LoginPage.class);
		System.out.println("Logoin Launch");
		try {
			//driver.findElement(By.id("nav-link-accountList")).click();
			driver.get("https://www.amazon.com/");
			Thread.sleep(1000);
			Lp.clickOnAccount();
			
			Thread.sleep(2000);
			System.out.println("UserID"+username);
			Lp.EnterEmailID(username);
			Lp.clickContinue();
			try {
				System.out.println("PAssword"+passWord);
				Lp.EnterPassword(passWord);
				Thread.sleep(1000);
				Lp.clickSubmit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}
	public void logout(WebDriver driver){
		try {
			System.out.println("inside LOGOUT::");
			Actions action = new Actions(driver);
			LoginPage Lp = PageFactory.initElements(driver, LoginPage.class);
			
			WebElement element = Lp.UserName;
			action.moveToElement(element).build().perform();
			
			Thread.sleep(2000);
			element = Lp.Logout;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
		//	Thread.sleep(2000);
	//		 driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]")).;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Outside LOGOUT::");
	}
	
	public void searchPage(WebDriver driver, AmazonBean bean) throws Exception{
		try {
			SearchPage Lp = PageFactory.initElements(driver, SearchPage.class);
			Lp.enterSearchValue(bean.SearchString);
			Lp.clickOnSearch();
			Thread.sleep(1000);
			Lp.clickOnProduct();
			Thread.sleep(2000);			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void addToCart(WebDriver driver, AmazonBean bean) {
		try {
			System.out.println("inside add to card" +bean.getQuantity());
			ProductDetailsPage Lp = PageFactory.initElements(driver, ProductDetailsPage.class);
			Lp.clickQuantity();
			
			Thread.sleep(500);
			Lp.sendQuantity(bean.getQuantity());		
			Thread.sleep(1000);
		
			Lp.clickAddToCart();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void validatingCart(WebDriver driver){
		try {
			
			
			
			driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/span[3]")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.id("add-to-cart-button")).click();
			Thread.sleep(2000);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void basketNameAndPrice(WebDriver driver){
		try {
			
			
			
			driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/span[3]")).click();
			Thread.sleep(1000);
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeFile(int rownum,  AmazonBean bean)
	{

		System.out.println(rownum + "----" + bean.getStatus());
		// TODO Auto-generated method stubSS
	
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:/Amazon_Scenario.xls"));
			//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:/Deepa/Selenium/trial/ExtentReports/Amazon_Scenario.xls"));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			int noOfSheets = wb.getNumberOfSheets();

			HSSFSheet hsSheetAttrib = wb.getSheetAt(0);
			if (hsSheetAttrib != null) {
				
				System.out.println("No. Of Rows :" + hsSheetAttrib.getPhysicalNumberOfRows());
						HSSFRow hsSheetAttribRow = hsSheetAttrib.getRow(rownum);
						System.out.println("rownum"+rownum);
						
					
						hsSheetAttribRow.createCell(2).setCellValue("NO");
						 hsSheetAttribRow.createCell(4).setCellValue(bean.getStatus());
						hsSheetAttribRow.createCell(6).setCellValue(bean.getActual1());
						hsSheetAttribRow.createCell(8).setCellValue(bean.getActual2());
						hsSheetAttribRow.createCell(10).setCellValue(bean.getActual3());
						System.out.println("case no" + hsSheetAttribRow.getCell(4));
						if(rownum !=(hsSheetAttrib.getPhysicalNumberOfRows()-1) )
						 hsSheetAttrib.getRow(rownum+1).createCell(2).setCellValue("YES");
						else
							 hsSheetAttrib.getRow(1).createCell(2).setCellValue("YES");
				
			}
			FileOutputStream fileOut = new FileOutputStream("D:/Amazon_Scenario.xls");
	//		FileOutputStream fileOut = new FileOutputStream("D:/Deepa/Selenium/trial/ExtentReports/Amazon_Scenario.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	

}
