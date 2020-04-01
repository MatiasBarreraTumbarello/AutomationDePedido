package com.matias.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTest {
	
	private WebDriver driver;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQDp3YriNSZpPXheBpPlA.n7jF9kAmVcC_UKQObRe9rvyDT8Igm5EkRkXvg6g.p0VfuiMfro.wj_P6Lgrfz12QkHgMvbN");
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EAVXQA4/view");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {

		NelsonClass.clickEntregaDePedido(driver);
		NelsonClass.accid(driver);
		
		
		
		
		//---------------------------ALTA DE SERVICIO
		
		
		
		
		//seccion: Confirmacion y Resumen de compra
		//MatiasClass.confirmarServicio(driver);
		

	}
	
	
	
}