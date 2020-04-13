package com.automation.izzi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoAltaDeServicios {
	/*	public static void IrACuenta(WebDriver driver) throws InterruptedException {
		WebElement link = new WebDriverWait(driver, 40)
	    	.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("REGRESION NUEVAS PRUE")));
		link.click();
		Thread.sleep(2000);
	}*/
	private WebDriver driver;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(" https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQCL4JlpszKvafhfjWfG.6aiPQCeOr0OOBz_u3K.K0Gb.yT0vhHeks09Pb1qumPGEdH8G2NeZDMZS18087xDzzM9pp8Dm");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/0013K000005YuoUQAS/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {

		llamadosDeMetodos(driver);
		
	}

	public static void AltaDeServicios(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Alta de Servicios"));
		executor.executeScript("arguments[0].click();", links.get(0));
		//links.get(0).click();
		Thread.sleep(2000);
	}
	
	public static void SeleccionDePlan(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		WebElement frame = driver.findElement(By.id("iFrameResizer3"));
		driver.switchTo().frame(frame);
		WebElement plan = new WebDriverWait(driver, 40)
		    	.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_0']")));
		plan.findElement(By.xpath("./..")).click();
		new WebDriverWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		driver.findElement(By.xpath("//div[@id='ChooseAndAddProducts_nextBtn']")).click();
		Thread.sleep(2000);
	}
	
	public static void confirmarServicio(WebDriver driver) throws InterruptedException{
		//Seccion: Confirmacion
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioConfirmation")));
		List<WebElement> opcion = driver.findElements(By.id("RadioConfirmation"));
	
		//En caso de seleccionar la opcion de NO, descomentar la siguiente linea, por defecto se selecciona SI
		//opcion.get(1).findElement(By.xpath("../.")).click();
	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Confirmation_nextBtn")));
		Thread.sleep(2000);
		driver.findElement(By.id("Confirmation_nextBtn")).click(); //boton siguiente
	
		//Seccion: Resumen de compra
	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DeliveryHomeSummary_nextBtn")));
		driver.findElement(By.id("DeliveryHomeSummary_nextBtn")).click();//boton finalizar
	}
	
	public static void llamadosDeMetodos (WebDriver driver)throws InterruptedException {
	//	IrACuenta(driver);
		AltaDeServicios(driver);
		SeleccionDePlan(driver);
		confirmarServicio(driver);
	}
}