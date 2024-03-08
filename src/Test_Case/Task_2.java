package Test_Case;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/***
 * 
 * @author Vinayak
 *
 */
public class Task_2 {
	public WebDriver driver = null;
	public WebDriverWait wait;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		driver.get("https://id.atlassian.com/login");
		driver.findElement(By.id("username")).sendKeys("vnmalagavi.003@gmail.com");
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		driver.findElement(By.id("password")).sendKeys("Vinnu@123");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		WebElement s = driver.findElement(By.xpath("//div[text()='Trello']/.."));
		wait.until(ExpectedConditions.elementToBeClickable(s));
		s.click();
		Reporter.log("user Login Sucessfully");

	}

	@Test
	public void testCase() {
		driver.findElement(By.xpath("//p[text()='Create']")).click();
		driver.findElement(By.xpath("//span[text()='Create board']")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("User Stories");
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("Enter list title…"))));
		driver.findElement(By.name("Enter list title…")).sendKeys("TO-DO");
		driver.findElement(By.xpath("//button[text()='Add list']")).click();
		driver.findElement(By.name("Enter list title…")).sendKeys("IN-PROGRESS");
		driver.findElement(By.xpath("//button[text()='Add list']")).click();

		driver.findElement(By.name("Enter list title…")).sendKeys("CLOSED");
		driver.findElement(By.xpath("//button[text()='Add list']")).click();

		driver.findElement(By.name("Enter list title…")).sendKeys("RE-OPEN");
		driver.findElement(By.xpath("//button[text()='Add list']")).click();
		for (int i = 0; i <= 6; i++) {
			driver.findElement(By.xpath("//button[text()='Add a card']")).click();
			driver.findElement(By.xpath("//textarea[@placeholder=\"Enter a title for this card…\"]"))
					.sendKeys("Task " + i);
			driver.findElement(By.xpath("//button[text()='Add a card']")).click();
		}
		Actions action = new Actions(driver);
		WebElement destination = driver.findElement(By.xpath("//h2[text()='IN-PROGRESS']"));
		WebElement source1 = driver.findElement(By.xpath("//a[text()='Task 2']"));
		WebElement source2 = driver.findElement(By.xpath("//a[text()='Task 4']"));
		action.dragAndDrop(source1, destination).perform();
		action.dragAndDrop(source2, destination).perform();

		WebElement destination2 = driver.findElement(By.xpath("//h2[text()='CLOSED']"));
		WebElement source3 = driver.findElement(By.xpath("//a[text()='Task 1']"));
		WebElement source4 = driver.findElement(By.xpath("//a[text()='Task 3']"));
		WebElement source5 = driver.findElement(By.xpath("//a[text()='Task 5']"));
		action.dragAndDrop(source3, destination2).perform();
		action.dragAndDrop(source4, destination2).perform();
		action.dragAndDrop(source5, destination2).perform();

		WebElement destination3 = driver.findElement(By.xpath("//h2[text()='RE-OPEN']"));
		WebElement source6 = driver.findElement(By.xpath("//a[text()='Task 5']"));
		action.dragAndDrop(source6, destination3).perform();
	}

	@AfterTest
	public void logout() {
		driver.findElement(By.xpath("//div[@title='Vinayak (vinayak231)']")).click();
		driver.findElement(By.xpath("//span[text()='Log out']")).click();
		driver.findElement(By.xpath("//span[text()='Log out']")).click();
		Reporter.log("Task Completed");

	}
}
