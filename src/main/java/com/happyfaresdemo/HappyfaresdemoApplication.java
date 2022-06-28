package com.happyfaresdemo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.TestNG;

import com.happyfaresdemo.operations.Operation;
import com.happyfaresdemo.operations.Testcountlistner;

@SpringBootApplication
public class HappyfaresdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyfaresdemoApplication.class, args);
		TestNG tng = new TestNG();
		tng.setListenerClasses(Arrays.asList(Testcountlistner.class));
		tng.setTestClasses(new Class[] { Operation.class });
		tng.run();
	}

}
