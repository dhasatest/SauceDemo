package com.saucedemo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="user-name")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id="login-button")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//span[text()='Products']")
	@CacheLookup
	WebElement titleProducts;
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement msgloginerror;
	
	public void enterUsername(String username) {
		txtUserName.clear();
		txtUserName.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		txtUserName.clear();
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginButton() {
		btnLogin.click();
	}
	
	public boolean isLoggedin() {
		try {
			return titleProducts.isDisplayed();
		 
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean isNotLoggedin() {
		try {
		return msgloginerror.isDisplayed();
		}catch (Exception e) {
			return false;
		}
	}
}
