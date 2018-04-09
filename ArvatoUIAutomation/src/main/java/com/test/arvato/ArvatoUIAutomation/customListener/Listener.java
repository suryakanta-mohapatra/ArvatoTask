package com.test.arvato.ArvatoUIAutomation.customListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class Listener extends TestBase implements ITestListener {

	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		Object currentClass = result.getInstance();
		//WebDriver driver = null;
		driver = ((TestBase)currentClass).getDriver();
		//String methodName = result.getName();
		Reporter.log("Test is Failed: "+"<font color='red'><i>"+result.getInstanceName()+"=>"+result.getMethod().getMethodName()+"</i></font>");
		//getScreenshot(driver, "Fail_TNG_"+methodName);
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test is Skipped:"+result.getMethod().getMethodName());

	}

	public void onTestStart(ITestResult result) {
		Reporter.log("Test started:"+result.getMethod().getMethodName()); 

	}

	public void onTestSuccess(ITestResult result) {
		Reporter.log("Test is Success: "+"<font color='green'><i>"+result.getInstanceName()+"=>"+result.getMethod().getMethodName()+"</i></font>");
	}

}
