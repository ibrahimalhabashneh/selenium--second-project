
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;


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
	
	
	@Test( invocationCount = 5, enabled = false)
	public void test() {
		//driver.get("https://www.almosafer.com/en/hotels-home");
		
		driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[2]/nav/a[1]")).click();
		WebElement src=driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div/div/div[1]/div/div/div/div/input"));
		src.click();
		int myRandomIndex = rand.nextInt(3);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebElement MySelector = driver.findElement(By.cssSelector(".tln3e3-1.eFsRGb"));

		Select selector = new Select(MySelector);
		selector.selectByIndex(rand.nextInt(2));
	
		
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
	
	@Test(enabled =false)
	public void room(){
		WebElement departureDateElement = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div/div[1]/span[2]"));
        String departureDateText = departureDateElement.getText();

        // Find the return date WebElement and extract the text
        WebElement returnDateElement = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div/div[2]/span[2]"));
        String returnDateText = returnDateElement.getText();

        // Calculate the expected departure and return dates
        LocalDate currentDate = LocalDate.now();
        LocalDate expectedDepartureDate = currentDate.plusDays(1);
        LocalDate expectedReturnDate = currentDate.plusDays(2);

        // Format the expected dates as strings
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
        String expectedDepartureDateString = expectedDepartureDate.format(dateFormatter);
        String expectedReturnDateString = expectedReturnDate.format(dateFormatter);
        
        System.out.println("Expected Departure Date: " + expectedDepartureDateString);
        System.out.println("Actual Departure Date: " + departureDateText);
        System.out.println("Expected Return Date: " + expectedReturnDateString);
        System.out.println("Actual Return Date: " + returnDateText);

        // Verify that the extracted dates match the expected dates
        Assert.assertEquals(departureDateText, expectedDepartureDateString, "Departure date mismatch");
        Assert.assertEquals(returnDateText, expectedReturnDateString, "Return date mismatch");
	}
	
	@Test()
	public void Ckeck() throws InterruptedException {
		driver.get("https://www.almosafer.com/en/hotels/Douai/13-11-2023/14-11-2023/2_adult?placeId=ChIJH3HiTzfJwkcR8G1kgT7xCgQ&city=Douai&sortBy=LOWEST_PRICE");
		Thread.sleep(8000);

		WebElement Container = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]"));

		List<WebElement> Prices = Container.findElements(By.className("Price__Value"));

		for (int i = 0; i < Prices.size(); i++) {

			String FirstPrice = Prices.get(0).getText();
			String LastPrice = Prices.get(Prices.size() - 1).getText();
			int FirstPriceAsNumber = Integer.parseInt(FirstPrice);

			int LastPriceAsNumber = Integer.parseInt(LastPrice);
			System.out.println(FirstPriceAsNumber);
			System.out.println(LastPriceAsNumber);
			
			Assert.assertEquals(FirstPriceAsNumber < LastPriceAsNumber, true) ;
			
		}
	}
	
	@Test(enabled = false)
	public void gggg() {
		WebElement ibrahim = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/span"));
		String elementText = ibrahim.getText();
		Assert.assertEquals(elementText.contains("found")||elementText.contains("وجدنا"), true);
		System.out.println(elementText);
	}
	
	@Test()
	public void ffgg() {
		
		SoftAssert myAssertion = new SoftAssert();

		String myName = "abed";

		myAssertion.assertEquals(myName, "abed", "this is to check my name");

		int myAge = 90;

		myAssertion.assertEquals(myAge, 30, "this is to check my age");
		
		myAssertion.assertAll();
	}
	
	@AfterTest
	public void AfterTest() {}
	
	
	
	
}
