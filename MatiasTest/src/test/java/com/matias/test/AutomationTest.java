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
<<<<<<< HEAD
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");
=======
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EAVXQA4/view");
		//driver.get("https://test1dom--sittest.lightning.force.com/one/one.app#eyJjb21wb25lbnREZWYiOiJ2bG9jaXR5X2NtdDp2bG9jaXR5SWZyYW1lQ29tcG9uZW50IiwiYXR0cmlidXRlcyI6eyJ1cmwiOiIvYXBleC92bG9jaXR5X2NtdF9fT21uaVNjcmlwdFVuaXZlcnNhbFBhZ2U%2FaWQ9MDJpM0swMDAwMDA1QnFlUUFFJmxheW91dD1saWdodG5pbmcmYWNjb3VudElkPTAwMWMwMDAwMDJKdkJyQ0FBViZwcm9kdWN0MklkPTAxdGMwMDAwMDA3cHZ1aUFBQSZ0cmFja0tleT0xNTg1NjE4MTkxMTQ2Jm9tbmlDYW5jZWxBY3Rpb249YmFjayZvbW5pSWZyYW1lRW1iZWRkZWQ9dHJ1ZSZpc2R0cD1wMSZzZmRjSUZyYW1lT3JpZ2luPWh0dHBzJTNBJTJGJTJGdGVzdDFkb20tLXNpdHRlc3QubGlnaHRuaW5nLmZvcmNlLmNvbSZzZmRjSUZyYW1lSG9zdD13ZWImIy9PbW5pU2NyaXB0VHlwZS9JWlpJL09tbmlTY3JpcHRTdWJUeXBlL0FkZE9ucy9PbW5pU2NyaXB0TGFuZy9TcGFuaXNoJTIwKE1leGljbykvQ29udGV4dElkLzAyaTNLMDAwMDAwNUJxZVFBRS9QcmVmaWxsRGF0YVJhcHRvckJ1bmRsZS8vdHJ1ZSJ9LCJzdGF0ZSI6e319");

>>>>>>> branch 'master' of https://github.com/MatiasBarreraTumbarello/AutomationDePedido.git
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {
		RoynerClass.IrACuenta(driver);
		RoynerClass.AltaDeServicios(driver);
		RoynerClass.SeleccionDePlan(driver);

		NelsonClass.clickEntregaDePedido(driver);

		
		
		
		
		//---------------------------ALTA DE SERVICIO
		
		
		
		
		//seccion: Confirmacion y Resumen de compra
		//MatiasClass.confirmarServicio(driver);

	}
	
	
	
}