package com.simplilearn.OnlineFoodService.model;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "src/test/resources/AddProduct.feature" }, 
		glue = { "com.simplilearn.OnlineFoodService" }, 
		plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" } 
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	 @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}

