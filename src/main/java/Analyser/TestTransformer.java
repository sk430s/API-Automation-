package Analyser;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


public class TestTransformer implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
	//	IRetryAnalyzer retry = annotation.getRetryAnalyzer();

		// if (retry == null)
		annotation.setRetryAnalyzer(RetryAnalyser.class);
	}
	
}
