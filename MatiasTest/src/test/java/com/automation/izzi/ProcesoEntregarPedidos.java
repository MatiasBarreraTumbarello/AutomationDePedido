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
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQKqOWdozya.BG9zeiOTFpX38eIXZWd_iojFro6bTekpA5h6O34rZj43kyxrxWjMLUP3mluXgRjWu6EX9QpeNPsef.wgL");
		Thread.sleep(10000);
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EEjrQAG/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
	}
	
	@Test
	public void testScript() throws InterruptedException {
		clickEntregaDePedido(driver);
		accid(driver);
	}
	
	public static void clickEntregaDePedido (WebDriver driver) throws InterruptedException{
		By iFrame1 = By.id("iFrameResizer0");
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		
		//driver.findElement(iFrame1).isDisplayed();
		
		driver.switchTo().frame("iFrameResizer0");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		
		
		driver.switchTo().defaultContent();

		Thread.sleep(5000);

	}
	
	public static void accid (WebDriver driver) {
		//8952140061736667340F

		
		List<WebElement> cantIFrames = driver.findElements(By.xpath("//iFrame"));
		int size = cantIFrames.size();
		driver.switchTo().frame(size - 1);
		
		// driver.switchTo().frame("iFrameResizer1");//*[@id="iFrameResizer1"]
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		driver.findElement(By.xpath("//input[@id=\'ICCID\']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//input[@id='ICCIDVal']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//div[@id='ValidarICCID']/p")).click();
		try {
			Thread.sleep(2000);
			
		//GUARDAR!!!! solo descomentar si se esta seguro que se quiere generar/guardar el Pedido.
		//	driver.findElement(By.xpath("//p[@class='ng-binding'  and contains(text(),'Guardar')]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}