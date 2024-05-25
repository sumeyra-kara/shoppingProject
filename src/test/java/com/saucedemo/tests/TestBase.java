package com.saucedemo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import com.saucedemo.utilities.BrowserUtils;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;
    protected Faker faker;
    @BeforeTest
    public void setUpTest(){
        // initialize the object
        report=new ExtentReports();

        String projectPath = System.getProperty("user.dir");
        //String reportPath = projectPath+ "/test-output/report.html";
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String reportPath = System.getProperty("user.dir")+"/test-output/report"+date+".html";

        htmlReporter=new ExtentHtmlReporter(reportPath);

        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("Smoke Test");

        //raporun meta datasini duzenleyelim
        report.setSystemInfo("Enviroment","Test");
        report.setSystemInfo("Browser", ConfigReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));
        report.setSystemInfo("tester",System.getProperty("user.name"));
        report.setSystemInfo("PO",System.getProperty("Sümeyra"));

    }
    @AfterTest
    public void tearDownTest(){
        report.flush();
    }



    @BeforeMethod
    public void setUp() {
        driver= Driver.get();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        faker=new Faker();


    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE) {

            String screenshotPath = BrowserUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);
            extentLogger.fail(result.getThrowable());
        }
        //Driver.closeDriver();

    }
}
