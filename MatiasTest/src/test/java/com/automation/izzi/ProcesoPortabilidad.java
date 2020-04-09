package com.automation.izzi;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProcesoPortabilidad {
	private WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQCL4JlpszKvafhfjWfG.6aiPQCeOr0OOBz_u3K.K0Gb.yT0vhHeks09Pb1qumPGEdH8G2NeZDMZS18087xDzzM9pp8Dm");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/0013K000005YuoUQAS/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	
	@Test
	public void testScript() throws InterruptedException {
		
		FranciscoClass.portabilidad(driver);
		
		MatiasClass.codigoNIP(driver);
		
		NelsonClass.PortabilidadSeleccionDeLinea(driver);
		
		RoynerClass.NumeroAPortar(driver);
		
		//La siguiente linea realiza la portabildiad del nro por lo que no se va a poder volver a realizar la portabilidad
		//MatiasClass.pasoDocumentacion(driver);
	}
}