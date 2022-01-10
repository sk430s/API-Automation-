package com.servicenow.SNAPIAutomation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.Hooks;
import utilities.Logs;
import utilities.extentReports;

public class BaseTestSpecs {

	Properties config = null;
	ExtentReports rpt;
	ExtentTest test;
	
	@BeforeClass
	public void beforeTestClass() throws Exception {
		Hooks.initialize();
		config = Hooks.config_prop;
		rpt= extentReports.getInstance(System.getProperty("user.dir")+config.getProperty("reportPath"));
	//	System.out.println("report" +rpt);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {

		ITestNGMethod method = result.getMethod();
		System.out.println("Method Name:" + method.getDescription());

		test = rpt.createTest(method.getDescription());

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, method.getDescription());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			StringWriter sw = new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(sw));
			test.log(Status.FAIL, sw.toString());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, method.getDescription());

		}

	}
	
	@AfterClass
	public void flushReport() {
		rpt.flush();
	}

}
