package com.matias.test;

import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AutomationTest {
	
	private WebDriver driver;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQAkZimZFoW91SqVre0eLSivQc4qRDpfYplG2E.4LnJotp2lkx7oTzDZb2txh1qxfjSh0x91qS8mqSc1WWA_MSbTPg6G5");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/0013K000005YuoUQAS/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {

		FranciscoClass.portabilidad(driver);
		
	}
	
	
	
}