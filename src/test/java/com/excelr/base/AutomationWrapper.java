package com.excelr.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class AutomationWrapper {

    protected WebDriver driver;
    private static ExtentReports extent;
    protected ExtentTest test;


    @BeforeSuite
    public void init() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
            extent.attachReporter(spark);
        }
    }

    @AfterSuite
    public void end() {
        extent.flush();
    }


    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("ch") String browserName, Method method) {

        test = extent.createTest(method.getName());

        if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        driver.get("https://demo.openemr.io/b/openemr");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        String base64 = ts.getScreenshotAs(OutputType.BASE64);
        test.addScreenCaptureFromBase64String(base64);

        driver.quit();
    }


}
