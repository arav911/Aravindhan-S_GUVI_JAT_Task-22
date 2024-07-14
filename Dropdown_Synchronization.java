package task_22_guvi;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dropdown_Synchronization {

	public static void main(String[] args) throws Exception {
//		Setting up the Chrome Driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//		Opening the URL
		driver.get("https://phptravels.com/demo/");

//		Filling in the Form details
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
		driver.findElement(By.name("first_name")).sendKeys("Aravindhan");
		driver.findElement(By.name("last_name")).sendKeys("S");
		driver.findElement(By.name("business_name")).sendKeys("Automation Testing");
		driver.findElement(By.name("email")).sendKeys("testuser@gmail.com");
		
//		Adding the logic for sum verification
		int number1 = Integer.parseInt(driver.findElement(By.id("numb1")).getText());
		int number2 = Integer.parseInt(driver.findElement(By.id("numb2")).getText());
		
		String result = String.valueOf(number1 + number2);
		
		driver.findElement(By.id("number")).sendKeys(result);
		driver.findElement(By.id("demo")).click();
		
//		Verifying that the Form submission is successful by checking the message displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='completed']//strong")));
		if(driver.findElement(By.xpath("//div[@class='completed']//strong")).getText().contains("Thank you!"))
			System.out.println("Form is successfully submitted");
		else
			System.out.println("Form not submitted");
		
//		Taking a screenshot of the page after form submission
		TakesScreenshot screenShot =((TakesScreenshot)driver);
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/Dropdown_Synchronization_Browser_Output.png");
		FileUtils.copyFile(srcFile, destFile);
	}

}
