package com.matias.test;

import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EntregarPedidos {
	
private WebDriver driver;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQJcoVlbuLYJB_I_L3aPZLlxIxfHXBLF_i0Fg5dMDy273W2nLhqVkHmv.tqrNnIy4EWTxZ5SuA3hKmHdGF71tNcN7E5wA");
		Thread.sleep(20000);
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EBx3QAG/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		

}
	
	@Test
	public void testScript() throws InterruptedException {
		NelsonClass.clickEntregaDePedido(driver);
		NelsonClass.accid(driver);
	}
}