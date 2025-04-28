package Ecommerce;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeTestUtilityClass {
	WebDriver driver;
	JavascriptExecutor js;
Alert alt;
	
	
	public PracticeTestUtilityClass(WebDriver d)
	{
		driver = d;
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public void InOut(String un,String ps) throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		driver.findElement(By.id("loginusername")).sendKeys("sharmilaG");
		driver.findElement(By.id("loginpassword")).sendKeys("sharmila123");
		driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
		File screenshot2=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 FileHandler.copy(screenshot2, new File("Login.jpg"));
		 System.out.println("Screenshot captured ");
	}
	
	public void purchase() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 600)", "");

		driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h4[1]/a[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/div[2]/div[1]/a[1]")).click();
		Thread.sleep(5000);
		
		Alert alt = driver.switchTo().alert();
		//System.out.println(alt.getText());
		
		alt.accept();
		
		driver.findElement(By.xpath("//*[@id=\"cartur\"]")).click();
		Thread.sleep(5000);
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 FileHandler.copy(screenshot, new File("added.jpg"));
		 System.out.println("Screenshot captured ");
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
		//driver.close();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Sharmila");
		driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys("India");
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Chennai");
		
		driver.findElement(By.xpath("//*[@id=\"card\"]")).sendKeys("2435354464564");
		js.executeScript("window.scrollBy(0, 600)", "");
		driver.findElement(By.xpath("//*[@id=\"month\"]")).sendKeys("March");
		driver.findElement(By.xpath("//*[@id=\"year\"]")).sendKeys("2025");
		
		
		 
		driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(4000);
		File screenshot1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 FileHandler.copy(screenshot1, new File("Purchased successfully.jpg"));
		 System.out.println("Screenshot captured ");
		driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();
		System.out.println("Purchased successfully");
	
		
	}
	
}
