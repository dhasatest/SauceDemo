package com.saucedemo.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import com.saucedemo.pageObjects.LoginPage;
import com.saucedemo.pageObjects.ProductsPage;
import com.saucedemo.utilities.XLUtility;

public class TC_Login_001 extends BaseClass {

	
	@Test(dataProvider="loginData")
	public void loginTest(String uname, String pwd, String type) throws IOException {

		LoginPage lp = new LoginPage(driver);
		ProductsPage prod = new ProductsPage(driver);
		logger.info("Entering Username and Password");
		lp.enterUsername(uname);
		lp.enterPassword(pwd);
		logger.info("Clicking Login Button");
		lp.clickLoginButton();

	


		if (type.equalsIgnoreCase("valid")) {
			if(lp.isLoggedin()) {

				logger.info("Login Successfull with Valid User Credential - Test Pass");
				prod.logout();
				logger.info("Logging out...");
				
				Assert.assertTrue(true);

			}
			else {

				logger.info("Unable to login - Test Failed");
				captureScreen(driver, "loginTest");
				Assert.assertTrue(false);

			}
		}
		else if (type.equalsIgnoreCase("invalid")) {
			if(lp.isNotLoggedin()) {
				logger.info("Login Failed with InValid User Credential - Test Pass");
				Assert.assertTrue(true);
				
			}
			else if(lp.isLoggedin()) {
				logger.info("Able to login with wrong Credentials - Test Failed");
				captureScreen(driver, "loginTest");
				prod.logout();
				Assert.assertTrue(false);
			}
			
		}
		else {
			logger.info("Test Data Error .. Please provide valid or invalid");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);

		}


	}
	
	
	@DataProvider(name = "loginData")
	public String[][] getLoginData() throws IOException{
		
		logger.info("Getting Input Test Data from Excel File" + testDataPath);
		
		XLUtility xl = new XLUtility(testDataPath);
		int rows = xl.getRowCount("Login");
		int cols = xl.getColumnCount("Login", 1);
		
		String data[][] = new String[rows][cols];
		
		for(int i=1; i<=rows; i++) {
			
			for(int j=0; j<cols; j++) {
			
				data[i-1][j] = xl.getCellData("Login", i, j);
			}
		}
		return data;
	}




}


