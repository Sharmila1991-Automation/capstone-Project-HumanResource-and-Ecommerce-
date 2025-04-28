package Ecommerce;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class D10PracticeTest_Client {
	WebDriver driver;
	PracticeTestUtilityClass p1;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Alert alt;
	ExtentSparkReporter htmlReport;
	// Report file
	ExtentReports report;
	// Actual report
	ExtentTest test;
	@Test
	public void loginLogout1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]")).click();
		p1.InOut("sharmilaG","sharmila123");
		test = report.createTest("Demoblaze Login Successful");
		p1.purchase();
		test = report.createTest("Item Purchased successfully");
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
			test.fail(result.getThrowable());		//getThrowable() will get the failure log
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			test.skip(result.getThrowable());
		}
		
	}


	@BeforeTest
	public void beforeTest()
	{
		htmlReport = new ExtentSparkReporter("ShoppingReport.html");	//Report File
		report = new ExtentReports();
		//Attach report to file
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("Machine Name", "Dell");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("User", "sharmila");
		report.setSystemInfo("Browser", "Google Chrome");
		
		htmlReport.config().setDocumentTitle("OHRM Login Report");
		htmlReport.config().setReportName("My Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setTimeStampFormat("EEEE MMMM dd yyyy, hh:mm a '('zzz')'");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/");
		test = report.createTest("Demoblaze shopping App ULR Launch");
		
		p1 = new PracticeTestUtilityClass(driver);
		
		
	}

	@AfterTest
	public void afterTest() {
		driver.close();
		report.flush();
	}

}