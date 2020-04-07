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
		
		//descomentar cuando termines
		//driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQJcoVlbuLYJB_I_L3aPZLlxIxfHXBLF_i0Fg5dMDy273W2nLhqVkHmv.tqrNnIy4EWTxZ5SuA3hKmHdGF71tNcN7E5wA");
		
		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQAkZimZFoW91SqVre0eLSivQc4qRDpfYplG2E.4LnJotp2lkx7oTzDZb2txh1qxfjSh0x91qS8mqSc1WWA_MSbTPg6G5");
		
		//driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view?ws=%2Flightning%2Fr%2FOrder%2F8013K000000EBx3QAG%2Fview");
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/0013K000005YuoUQAS/view");//link de testeo de FranciscoClass
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {

		//RoynerMatiasClass.llamadosDeMetodos(driver);

		FranciscoClass.portabilidad(driver);
		
	}
	
	
	
}