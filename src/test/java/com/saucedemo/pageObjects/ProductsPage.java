package com.saucedemo.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.errorprone.annotations.CanIgnoreReturnValue;




public class ProductsPage {
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="react-burger-menu-btn")
	@CacheLookup
	WebElement btnMenu;
	
	@FindBy(id="logout_sidebar_link")
	@CacheLookup
	WebElement lnkLogout;
	
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-backpack']")
	@CacheLookup
	WebElement btnAddBackPack;
	
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
	@CacheLookup
	WebElement btnAddTshirt;
	
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bike-light']")
	@CacheLookup
	WebElement btnAddBikeLight;
	
	
	@FindBy(xpath="//span[@class='shopping_cart_badge' and text()='3']")
	@CacheLookup
	WebElement Shopingcart3;
	
	@FindBy(xpath="//button[@id='remove-sauce-labs-backpack']")
	@CacheLookup
	WebElement btnRemoveBackPack;
	
	@FindBy(xpath="//button[@id='remove-sauce-labs-bolt-t-shirt']")
	@CacheLookup
	WebElement btnRemoveTshirt;
	
	@FindBy(xpath="//button[@id='remove-sauce-labs-bike-light']")
	@CacheLookup
	WebElement btnRemoveBikeLight;
	
	@FindBy(id="continue-shopping")
	@CacheLookup
	WebElement btnContinueShopping;
	
	@FindBy(xpath="//div[@class='cart_item']")
	List<WebElement> cartItems ;
	
			
	public void logout() {
		btnMenu.click();
		lnkLogout.click();			
	}
	
	////////Add to Cart/////////////////////////
	
	public void addBackPackToCart() {
		btnAddBackPack.click();
	}
	
	public void addTshirtToCart() {
		btnAddTshirt.click();
	}
	
	public void addBikeLightToCart() {
		btnAddBikeLight.click();
	}
	
	///////Remove From Cart
	
	public void removeBackPackFromCart() {
		btnRemoveBackPack.click();
	}
	
	public void removeTshirtFromCart() {
		btnRemoveTshirt.click();
	}
	
	public void removeBikeLightFromCart() {
		btnRemoveBikeLight.click();
	}
	
	////////////////////////////////////
	
	public boolean isItemsAdded() {
		return Shopingcart3.isDisplayed();
	}
	
	public void goToCart() {
		Shopingcart3.click();
	}
	
	public int cartItemCount() {
		return cartItems.size();
	}
	
	
	
	//////////////////////////////////////////
	
	public void clickContinueShooping() {
		btnContinueShopping.click();
	}
	
	

}
