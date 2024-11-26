package TestComponenets;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
		String filepath = null;
		
		
	    try {
			 filepath = getScreenshot(result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	
	
	@Override
	public void onSuccess (ITestResult result)
	{
		extent.flush();
	}
	

	
}
