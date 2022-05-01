package com.saucedemo.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener extends TestListenerAdapter {
	private static ExtentSparkReporter reporter;
	private static ExtentReports extent;
	
	
	
	public void onStart(ITestContext testcontext) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		
		reporter = new ExtentSparkReporter("./ExtentReports/TestReport-"+timestamp+".html");
		try {
			reporter.loadXMLConfig("./extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "dhasa");
		
		reporter.config().setDocumentTitle("SauceDemo Test Project");
		reporter.config().setReportName("SauceDemo Automation Testing Report");
		reporter.config().setTheme(Theme.DARK);
		
	}
	
	public void onTestStart(ITestResult tr) {
		System.out.println("Test execution of "+ tr.getName()+" started");
		
	}
	
	
	public void onTestSuccess(ITestResult tr) {
		extent.createTest(tr.getName()).log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		extent.flush();
	}
	
	public void onTestFailure(ITestResult tr) {
		extent.createTest(tr.getName())
		.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED))
		.addScreenCaptureFromPath("../Screenshots/"+tr.getName()+".png");
		extent.flush();
	}
	

	public void onTestSkipped(ITestResult tr) {
		System.out.println("Test Skipped");
	}

	
}
