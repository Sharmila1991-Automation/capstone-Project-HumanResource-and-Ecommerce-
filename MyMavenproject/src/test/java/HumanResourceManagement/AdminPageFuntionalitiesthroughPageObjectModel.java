package HumanResourceManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageFuntionalitiesthroughPageObjectModel{
	WebDriver driver;
	JavascriptExecutor js;
	//PageFactory
	@FindBy (xpath="//input[@placeholder='Username']") WebElement userName;
	@FindBy (xpath = "//button[@type='submit']") WebElement loginBtn;
	public AdminPageFuntionalitiesthroughPageObjectModel(WebDriver d)
	{
		driver = d;
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	public void setUserName(String un)
	{
		js.executeScript("window.scrollBy(0, 200)", "");
		userName.sendKeys(un);
	}
	public void setPassword(String ps)
	{
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
	}
	public void clickOnLoginBtn()
	{
		loginBtn.click();
	}
	public void adminfunction() throws InterruptedException
	{
		
		List<WebElement>allLinks=driver.findElements(By.tagName("a"));//a is a <Link> tag name in html script
		System.out.println("TotalLinks:"+allLinks.size());
		//System.out.println(allLinks.get(0).getText()); //To display a specific link get(0), get(1).....
		for(int i=0;i<allLinks.size();i++)
			System.out.println(allLinks.get(i).getText()); 	
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]/span[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("Sachin89");
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]")).click();
		Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[2]")).click();
       Thread.sleep(5000);
      
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/input")).sendKeys("Sachin Gaikwad");
       //status
       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]")).click();
   	
   	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div[2]/div[2]")).click();
	//search
   	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]")).click();
   	System.out.println("Search Result Found");
	driver.close();
	}
	
}
