package com.excelr.test;

import com.aventstack.extentreports.Status;
import com.excelr.base.AutomationWrapper;
import com.excelr.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginUserInterfaceTest extends AutomationWrapper {

    @Test
    public void validateTitleTest() {
        String actualTitle = driver.getTitle();
        test.log(Status.INFO,"Actual title: "+actualTitle);

        Assert.assertEquals(actualTitle, "OpenEMR Login");
    }

    @Test
    public void applicationDescriptionTest() {
        //assert the text - The most popular open-source Electronic Health Record and Medical Practice Management solution.
        LoginPage loginPage=new LoginPage(driver);
        String actualDesc = loginPage.getApplicationDescription();
        Assert.assertEquals(actualDesc, "The most popular open-source Electronic Health Record and Medical Practice Management solution.");
    }

}
