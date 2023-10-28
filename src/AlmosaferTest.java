
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AlmosaferTest {

	WebDriver driver = new ChromeDriver();	
	String Url="https://www.almosafer.com/ar";
	Random rand= new Random();
	@BeforeTest
	public void setup() {
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		int RandomURLINDEX = rand.nextInt(2);
		String[] myURLS = { "https://global.almosafer.com/ar", "https://global.almosafer.com/en" };
		driver.get(myURLS[RandomURLINDEX]);
		
		if (driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div")).isDisplayed()) {
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
			}
	}
	
	
	@Test( invocationCount = 5)
	public void test() {
		driver.get("https://www.almosafer.com/en/hotels-home");
		
		driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[2]/nav/a[1]")).click();
		WebElement src=driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div/div/div[1]/div/div/div/div/input"));
		src.click();
		int myRandomIndex = rand.nextInt(3);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
		
		if(driver.getCurrentUrl().contains("ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguage, "ar");			
			String[] MyIDs = {"دبي","جده","رياض"};
			String selected = MyIDs[myRandomIndex];
			src.sendKeys(selected);
			driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div/div/div[4]/button")).click();
			
		}
		else{
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguage, "en");
			String[] MyIDs = {"Dubai","Jeddah","Riyadh"};
			String selected = MyIDs[myRandomIndex];
			src.sendKeys(selected);
			driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div/div/div[4]/button")).click();
		}
		
	}
	
	@AfterTest
	public void AfterTest() {}
	
	
	
	
}
