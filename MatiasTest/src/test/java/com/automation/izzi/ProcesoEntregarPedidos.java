package com.automation.izzi;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoEntregarPedidos {
	
	private WebDriver driver;
			//************************LEER*****************************************************************
			// En eclipse para ir al desarrollo del metodo debo hacer CTRL + Click al llamamiento del mismo.
			// En algunos casos hay metodos que estan comentados, en caso de querer cambiar las elecciones solo basta con descomentar uno y comentar el otro.
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQK4dObWJFEK2jq6cH_To8s4EuZ60vP6ruUY5I5plFLiesyIkXNXyeFMyg5bPXylrTepfuEER2A9UmqH8EBAAI2ao25MD");
		Thread.sleep(10000);
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EiyWQAS/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
	}
	
	@Test
	public void testScript() throws InterruptedException {
		clickEntregaDePedido(driver);
		accid(driver);
	}
	//------------------------------------------------METODOS--------------------------------------------------
	public static void clickEntregaDePedido (WebDriver driver) throws InterruptedException{
		By iFrame1 = By.id("iFrameResizer0");
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		driver.switchTo().frame("iFrameResizer0");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
	}
		
	//----------------------------------------------------------------------------------------------------------	
		public static void accid (WebDriver driver) {
		//8952140061736667340F

		//Creacion de lista de Iframes para saleccionar el ultimo de la lista.
		List<WebElement> cantIFrames = driver.findElements(By.xpath("//iFrame"));
		int size = cantIFrames.size();
		driver.switchTo().frame(size - 1);
		
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));

		
		driver.findElement(By.xpath("//input[@id=\'ICCID\']")).sendKeys("8952140061741671430F");
		driver.findElement(By.xpath("//input[@id='ICCIDVal']")).sendKeys("8952140061741671430F");

		driver.findElement(By.xpath("//input[@id=\'ICCID\']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//input[@id='ICCIDVal']")).sendKeys("8952140061733523614F");

		driver.findElement(By.xpath("//div[@id='WrapperValidarICCID']/p")).click();
		try {
			Thread.sleep(3000);
			
		//-----GUARDAR!!!!---- solo descomentar si se esta seguro que se quiere generar/guardar el Pedido.
	
		driver.findElement(By.xpath("//div[@id='DeliverySimCard_nextBtn']/p")).click();
		Thread.sleep(3000);
		
		
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}