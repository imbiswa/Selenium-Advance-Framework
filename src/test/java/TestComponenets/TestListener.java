package TestComponenets;
//
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
//
//public class Listeners extends BaseTest implements ITestListener{
//	
//	ExtentReports extent = ExtentReporterNG.getReportObject();
//	ExtentTest test;
//	
//	@Override
//	public void onTestStart(ITestResult result) {
//		extent.createTest(result.getMethod().getMethodName());
//		
//	}
//	
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		test.log(Status.PASS, "Test Passed");
//		
//	}
//	
//	
//	@Override
//	public void onTestFailure(ITestResult result) {
//		
//		//test.fail(result.getThrowable());
//	try {
//		driver	= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		
//	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	    String filepath = null;
//	    
//	    try {
//			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//	    test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
//		
//	}
//	
//	
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void onTestFailedButWithinSuccessPercentage (ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public void onStart(ITestContext context) {
//		
//		
//	}
//	@Override
//	public void onFinish(ITestContext context) {
//		
//		extent.flush();
//	}
//	
//	
//	
//
//	
//}

public class TestListener extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());	
		//Allure.addAttachment(filePath, result.getMethod().getMethodName());
		//Screenshot, Attach to report				
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	
}

