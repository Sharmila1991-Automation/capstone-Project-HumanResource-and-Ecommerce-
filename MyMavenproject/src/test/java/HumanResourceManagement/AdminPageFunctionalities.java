package HumanResourceManagement;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
public class AdminPageFunctionalities {
	WebDriver driver;
	AdminPageFuntionalitiesthroughPageObjectModel p1;
	@Test
	public void login() throws InterruptedException {
		p1.setUserName("admin");
		p1.setPassword("admin123");
		p1.clickOnLoginBtn();
		Thread.sleep(3000);
		p1.adminfunction();
		
	}
  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		p1 = new AdminPageFuntionalitiesthroughPageObjectModel(driver);
  }

  @AfterTest
  public void afterTest() {
	     driver.close();
		System.out.println("After Test");
  }
}
