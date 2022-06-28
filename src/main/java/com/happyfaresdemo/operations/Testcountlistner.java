package com.happyfaresdemo.operations;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.happyfaresdemo.utility.Filefilter;

public class Testcountlistner implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		if(testMethod.getName().equals("perform")) {
			File[] files = Filefilter.getfiles();
			annotation.setInvocationCount(files.length);
		}
		
	}

}
