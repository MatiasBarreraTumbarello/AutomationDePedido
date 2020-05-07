package com.automation.izzi;

import java.io.IOException;
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

	private MainClass main = new MainClass();
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	@Before
	public void setUp() throws InterruptedException, IOException {
		
		
		driver = main.setDriver();
		main.initBrowser();
		main.goToAccountLink();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {
		try {
			llamadosDeMetodos();
			main.returnExecutionSuccess(getClass().getName());
		} catch (Exception e) {
			main.returnExecutionError(getClass().getName(), e);
		}
		
	}

	public void AltaDeServicios() throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Alta de Servicios"));
		executor.executeScript("arguments[0].click();", links.get(2));
		//links.get(0).click();
		Thread.sleep(2000);
	}
	
	public void SeleccionDePlan() throws InterruptedException {
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		WebElement frame = driver.findElement(By.id("iFrameResizer3"));
		driver.switchTo().frame(frame);
		
		main.waitForInvisibleSpinner();
		WebElement plan = new WebDriverWait(driver, 40)
		    	.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_0']")));
		plan.findElement(By.xpath("./..")).click();
		
		main.waitForInvisibleSpinner();
		new WebDriverWait (driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ChooseAndAddProducts_nextBtn']")));
		driver.findElement(By.xpath("//div[@id='ChooseAndAddProducts_nextBtn']")).click();

		main.waitForInvisibleSpinner();
		Thread.sleep(2000);
	}
	
	public void confirmarServicio() throws InterruptedException{
		//Seccion: Confirmacion
		main.waitForInvisibleSpinner();
		new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.id("RadioConfirmation")));
		List<WebElement> opcion = driver.findElements(By.id("RadioConfirmation"));
		
		//En caso de seleccionar la opcion de NO, descomentar la siguiente linea, por defecto se selecciona SI
		//opcion.get(1).findElement(By.xpath("../.")).click();
	
		main.waitForInvisibleSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Confirmation_nextBtn")));
		Thread.sleep(2000);
		driver.findElement(By.id("Confirmation_nextBtn")).click(); //boton siguiente
		Thread.sleep(3000);
		
		//Seccion: Resumen de compra
	
		main.waitForInvisibleSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DeliveryHomeSummary_nextBtn")));
		driver.findElement(By.xpath("//*[@id='DeliveryHomeSummary_nextBtn']/p")).click();
	
		Thread.sleep(3000);
		
		main.waitForInvisibleSpinner();
		
		Thread.sleep(3000);
		
		
		//Finalizar
		
		/*driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand ng-binding' and contains(text(),Finalizar)]")).click();
		Thread.sleep(3000);*/
		
		
		

	}
	
	public void llamadosDeMetodos ()throws InterruptedException {
	//	IrACuenta(driver);
		AltaDeServicios();
		SeleccionDePlan();
		confirmarServicio();
	}
}