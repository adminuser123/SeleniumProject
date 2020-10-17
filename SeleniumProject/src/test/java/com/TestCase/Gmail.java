package com.TestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Gmail {
	public static WebDriver driver;
	public static WebElement element;
	public static String browser;

	public static String email = "ferdous13581@gmail.com";
	public static String pass = "test123456@";
	public static String expectedUser = "MD FERDOUS AHMED";

	public static String expectedBody = "Hello World";
	public static String expectedSubject = "Hello";

	public static String emailBody = "I am confirming that I receive your email";
	public static String emailSubject = "Hello";
	public static String recipient = "ferdous13581@gmail.com";

	@BeforeTest
	public static void yahooTest() throws InterruptedException {

		setBrowser();
		setBrowserConfig();
		setConnection();

		System.out.println("before method execution ends.");

	}

	public static void setBrowser() {

		browser = "Chrome";
		System.out.println("setBrowser() method executed");
	}

	public static void setBrowserConfig() {

		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println(" setBrowserConfig() method executed");
		}
	}

	@Test
	public static void setConnection() {

		driver.get(
				"https://accounts.google.com/ServiceLogin/signinchooser?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		;
		driver.manage().window().maximize();
		System.out.println(" URL opened");
		System.out.println(" test 1 : setConnection() complete ");

	}

	@Test(priority = 1)
	public static void login() throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb']")).click();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
		Thread.sleep(1000);

	}

	@Test(priority = 2)
	public static void loginValidate() throws InterruptedException {
		// code for extracting any text from UI using get text method

		driver.findElement(By.xpath("//img[@class='gb_La gbii']")).click();
		element = driver.findElement(By.xpath("//div[contains(text(),'MD FERDOUS AHMED')]"));

		Thread.sleep(1000);
		String actualUIUser = element.getText();
		System.out.println("Actual user in UI = " + actualUIUser);
		driver.findElement(By.xpath("//img[@class='gb_La gbii']")).click();

		// code for validation using if else condition
		if (actualUIUser.equals(expectedUser)) {
			AssertJUnit.assertTrue(true);
			System.out.println(
					"Application user = " + actualUIUser + " matches with the given expected user = " + expectedUser);
		} else {
			AssertJUnit.fail();
			System.out.println("Application user = " + actualUIUser + " do not match with the given expected user "
					+ expectedUser);
		}
	}

	@Test(priority = 3)
	public static void validateReceive() throws InterruptedException {

		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[7]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]"))
				.click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h2[contains(text(),'Hello') ]"));
		Thread.sleep(1000);
		String actualSubject = element.getText();
		System.out.println("Actual Subject UI = " + actualSubject);
		element = driver.findElement(By.xpath("//div[contains(text(),'Hello World')]"));
		Thread.sleep(1000);
		String actualBody = element.getText();
		System.out.println("Actual Body UI = " + actualBody);

		// code for validation using if else condition
		if ((actualSubject.equals(expectedSubject)) && (actualBody.equals(expectedBody))) {
			AssertJUnit.assertTrue(true);
			System.out.println("Email Validated and Received properly");

		} else {
			AssertJUnit.fail();
			System.out.println("Does not recieved");
		}
	}

	@Test(priority = 4)
	public static void reply() throws InterruptedException {

		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/span[1]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/div[1]/div[1]"))
				.sendKeys(emailBody);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[2]/td[1]/div[2]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]"))
				.click();
		Thread.sleep(1000);

	}
	@Test(priority = 5)
	public static void logout() throws InterruptedException {
		driver.findElement(By.xpath("//img[@class='gb_La gbii']")).click();
		driver.findElement(By.xpath("//a[@id='gb_71']")).click();
	    driver.close();
		
	}


}
