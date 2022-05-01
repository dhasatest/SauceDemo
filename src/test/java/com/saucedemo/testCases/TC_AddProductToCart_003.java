package com.saucedemo.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.pageObjects.LoginPage;
import com.saucedemo.pageObjects.ProductsPage;

public class TC_AddProductToCart_003 extends BaseClass{
	
	
	
	@Test
	public void AddProductToCart() throws IOException, InterruptedException {
	
        logger.info("Logging in ...");
		LoginPage lp = new LoginPage(driver);
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickLoginButton();
		
		ProductsPage pro = new ProductsPage(driver);
		
		logger.info("Adding Backpack to Cart.");
		
		
		pro.addBackPackToCart();
		pro.addTshirtToCart();
		pro.addBikeLightToCart();
		
		if (pro.isItemsAdded()) {
			logger.info("The Selected Items are added to the cart");
			Assert.assertTrue(true);
			
		}else {
			logger.info("Items are not added - Test Failed");
			captureScreen(driver, "AddProductToCart");
			Assert.assertTrue(false);
			
		}
		
		logger.info("Navigate to Cart...");
		pro.goToCart();
		
		
		logger.info("Removing items from the cart ...");
		pro.removeBackPackFromCart();
		pro.removeTshirtFromCart();
		pro.removeBikeLightFromCart();
				
		if(pro.cartItemCount() ==0) {
			logger.info("Removed all items from the cart ...");
			Assert.assertTrue(true);
		}else
		{
			logger.info("Items not removed from the cart ...Test Failed");
			captureScreen(driver, "AddProductToCart");
			Assert.assertTrue(false);
		}
			
		logger.info("Go Back to Shopping Page");
		pro.clickContinueShooping();
		
		pro.logout();
		

		
	}

}
