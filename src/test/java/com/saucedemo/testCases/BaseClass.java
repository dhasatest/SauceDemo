package com.saucedemo.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.saucedemo.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig rc = new ReadConfig();
	public static Logger  logger;
	public static WebDriver driver;
	public String username = rc.getUsername();
	public String password = rc.getPassword();
	public String testDataPath = rc.getTestDataPath();
	public String url = rc.getUrl();
	
	
	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("SauceDemo");
		PropertyConfigurator.configure("Log4j.properties");
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		driver = new ChromeDriver();
		
		//WebDriverManager.firefoxdriver().setup();
		//driver = new FirefoxDriver();
		
		logger.info("Opening Url : " + url);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void teardown() {
		logger.info("Quiting Browser....");
		driver.quit();
		
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("./Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot Taken");
		
	}
	

	public static  WebElement waitForElementVisible(By locator, WebDriver driver, int durationInSec) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(durationInSec));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
}
