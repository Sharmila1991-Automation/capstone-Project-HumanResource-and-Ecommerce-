package HumanResourceManagement;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginandLogoutWithFourCredentials {
	String fPath = "MyData1.xlsx";
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;
	XSSFFont font;
	int rows, i, j, index = 1;
	WebDriver driver;
	
	ExtentSparkReporter htmlReport;
	// Report file
	ExtentReports report;
	// Actual report
	ExtentTest test;
	
	@Test(dataProvider = "getLoginData")
	public void loginToOHROM(String un, String ps) throws IOException, InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 FileHandler.copy(screenshot, new File(un + ".jpg"));
		 System.out.println("Screenshot captured ");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		test = report.createTest("OHRM_Login");
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dash"));
			
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		row = sheet.getRow(index);
		cell = row.getCell(2);
		
		style = wb.createCellStyle();
		font = wb.createFont();
		
		if (driver.getCurrentUrl().contains("dashboard")) {
			System.out.println("Test case pass");
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
			driver.findElement(By.linkText("Logout")).click();
			
			font.setColor(HSSFColorPredefined.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
			cell.setCellValue("Pass");
		} 
		else {
			System.out.println("Test case fail");
			
			font.setColor(HSSFColorPredefined.RED.getIndex());
			font.setStrikeout(true);
			font.setItalic(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
			cell.setCellValue("Fail");
		}
		index++;
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


	@DataProvider
	public Object[][] getLoginData() {
		rows = sheet.getPhysicalNumberOfRows();
		String[][] loginData = new String[rows - 1][2];
		
		for(i = 0; i < rows - 1; i++)
		{
			row = sheet.getRow(i + 1);
			for(j = 0; j < 2; j++)
			{
				cell = row.getCell(j);
				loginData[i][j] = cell.getStringCellValue();
			}
		}
		return loginData;
	}
	@BeforeTest
	public void beforeTest() throws IOException {
		file = new File(fPath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		fos = new FileOutputStream(file);
		//Put this statement only after configuration of sheet
		htmlReport = new ExtentSparkReporter("MyExtendReport.html");	//Report File
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
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@AfterTest
	public void afterTest() throws IOException {
		wb.write(fos);
		wb.close();
		fis.close();
		report.flush();
		driver.close();
	}

}