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
		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQFbE1UAXI_q3mN0M4ev7w9NML2UQXOQIIlndykweqgxQeijRUyrzcQbg5FYno7xSXkdd65Jk5L5H_oItOC5KGECZc86o");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EBx3QAG/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {
		//RoynerClass.IrACuenta(driver);
		//RoynerClass.AltaDeServicios(driver);
	//	RoynerClass.SeleccionDePlan(driver);
		
		//MatiasClass.confirmarServicio(driver);

		NelsonClass.clickEntregaDePedido(driver);

		NelsonClass.accid(driver);


		
	}
	
	
	
}