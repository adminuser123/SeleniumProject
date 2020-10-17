package com.TestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Yhoo {
	public static WebDriver driver;
	public static WebElement element;
	public static String browser;

	public static String email = "ferdous1358";
	public static String pass = "test123456";
	public static String expectedUser = "MD FERDOUS";

	public static String emailBody = "Hello World";
	public static String emailSubject = "Hello";
	public static String recipient = "ferdous13581@gmail.com";

	@BeforeClass // since our test scope within the class and we r not using same URL for multiple test class , we should use @BeforeClass annotation instead of BeforeTest
	public static void yahooTest() throws InterruptedException {

		
		setBrowserConfig();
		setConnection();

		System.out.println("before method execution ends.");

	}

	/*public static void setBrowser() {

		
		System.out.println("setBrowser() method executed");
	}*/

	public static void setBrowserConfig() {
		    browser = "Chrome";
		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//chromedriver");
			driver = new ChromeDriver();
			System.out.println(" setBrowserConfig() method executed");
		}
	}

	//@Test// Navigating to URL we should not write inside the  @Test methods. we can write inside @BeforeClass 
	public static void setConnection() {

		driver.get(
				"https://login.yahoo.com/?.src=ym&.lang=pl-PL&.intl=pl&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3Fpspid%3D2023538075%26activity%3Dybar-mail");
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		;
		driver.manage().window().maximize();
		
		System.out.println("  setConnection() complete ");

	}

	@Test(priority = 1)
	public static void login() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='login-signin']")).click();
		driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		Thread.sleep(1000);

	}

	@Test(priority = 2)
	public static void loginValidate() throws InterruptedException {
		// code for extracting any text from UI using get text method
		element = driver.findElement(By.xpath("//span[text()='MD FERDOUS']"));
		Thread.sleep(1000);
		String actualUIUser = element.getText();
		System.out.println("Actual user in UI = " + actualUIUser);

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
	public static void writeMessage() throws InterruptedException {

		driver.findElement(By.xpath(
				"//a[@class='e_dRA l_T cn_dBP cg_FJ k_w r_P A_6EqO u_e69 p_R S_n C_52qC I_ZamTeg D_F H_6VdP gl_C ab_C en_0 M_1Eu7sD ir3_1JO2M7 it3_dRA']"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='message-to-field']")).sendKeys(recipient);
		driver.findElement(By.xpath("//input[@data-test-id='compose-subject']")).sendKeys(emailSubject);
		driver.findElement(By.xpath("//div[@class='rte em_N ir_0 iy_A iz_h N_6Fd5']")).sendKeys(emailBody);
		driver.findElement(By.xpath("//button[@class='q_Z2aVTcY e_dRA k_w r_P H_6VdP s_3mS2U en_0 M_1gLo4F V_M cZ1RN91d_n y_Z2hYGcu A_6EqO u_e69 b_0 C_52qC I4_Z29WjXl ir3_1JO2M7 it3_dRA']")).click();
		Thread.sleep(5000);
		
	}
	@Test(priority = 4)
	public static void validateSent() throws InterruptedException {

		driver.findElement(By.xpath("//div[@id='mail-app-container']//li[5]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='mail-app-component-container']//li[3]//a[1]")).click();
		element = driver.findElement(By.xpath("//span[@title='Hello']"));
		Thread.sleep(1000);
		String actualSubject = element.getText();
		System.out.println("Actual Subject UI = " + actualSubject);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//div[contains(text(),'Hello World')]"));
		Thread.sleep(1000);
		String actualBody = element.getText();
		System.out.println("Actual Body UI = " + actualBody);

		// code for validation using if else condition
		if (( actualSubject.equals(emailSubject))&& (actualBody.equals(emailBody))) {
			AssertJUnit.assertTrue(true);
			System.out.println("Email Sent properly");
					
		} else {
			AssertJUnit.fail();
			System.out.println("Does not Sent properly");
		}
	}

	//@Test(priority = 5) Logout and closing browser we can write inside @AfterClass annotation
	@AfterClass
	public static void logout() throws InterruptedException {
		driver.findElement(By.xpath("//img[@class='_yb_1oxqp']")).click();
		driver.findElement(By.xpath("//span[@class='_yb_5f2oy _yb_bggnv _yb_1ga9f _yb_165sl _yb_bggnv']")).click();
		driver.close();
	}


}
