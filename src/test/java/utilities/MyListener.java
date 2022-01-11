package utilities;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class MyListener extends TestListenerAdapter {
	
	public static String statusMessage;
	

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // do what you want to do
	    	statusMessage = "Test case failed";
	    	//System.out.println("Test case failed");
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // do what you want to do
	    	statusMessage = "Test case passed";
	    	//System.out.println("Test case passed");
	    }

	   @Override
	    public void onTestSkipped(ITestResult result) {
	        // do what you want to do
		   System.out.println("inside SKIP");
		   //test.log(Status.SKIP, method.getAnnotation(Test.class).description());
		   
	    }
	}


