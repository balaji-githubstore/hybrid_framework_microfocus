package com.excelr.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoExtent {

    public static void main(String[] args) {

        //initialize the report - runs only once in the beginning - @BeforeSuite
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent.attachReporter(spark);


        //Before running @Test - we need to create test //@BeforeMethod
       ExtentTest test= extent.createTest("MyFirstTest - Sample");

       test.log(Status.INFO, "Running test method");

        //after running @Test - we need to log wether @Test method is passed or failed //@AfterMethod
       test.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");



        //run at the end of all test cases - @AfterSuite
        extent.flush();


    }
}
