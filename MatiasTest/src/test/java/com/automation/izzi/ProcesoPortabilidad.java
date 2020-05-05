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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoPortabilidad {
	private Config config = new Config();
	private WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException, IOException {

		config.initBrowser();
		config.goToAccountLink();
		driver = config.driver;
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		Thread.sleep(20000);
		
	}
	
	
	@Test
	public void testScript() throws InterruptedException {
		
		portabilidad(driver);
		
		codigoNIP(driver);
		
		PortabilidadSeleccionDeLinea(driver);
		
		NumeroAPortar(driver);
		
		//La siguiente linea realiza la portabildiad del nro por lo que no se va a poder volver a realizar la portabilidad
		pasoDocumentacion(driver);
	}
	
	public static void portabilidad (WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button"));
		executor.executeScript("arguments[0].click();", boton);
		
		driver.switchTo().defaultContent();
	}
	
	//Este metodo se utiliza en el primer paso del flujo de portabilidad
	public static void codigoNIP(WebDriver driver) throws InterruptedException{
		//*[@id="RadioNIP|0"]/div/div[1]/label[1]
		
		WebDriverWait  wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		Thread.sleep(2000);
		
		//Descomentar cualquiera de las dos siguientes lineas, de acuerdo a la respuesta que desea colocar
		
		//Esta opcion afirma que el cliente cuenta con un nro NIP
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.elementToBeClickable(By.id("RadioNIP")));
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'RadioNIP\']"));
		Thread.sleep(1000);
		opt.get(0).findElement(By.xpath("./..")).click();

		
		//Esta opcion niega que el cliente cuenta con un nro NIP
		//driver.findElement(By.xpath("//input[@id='RadioNIP' and @value='No']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='NIP']")).sendKeys(generateNIP());
		Thread.sleep(1000);
		
		driver.findElement(By.id("StepNumeroNip_nextBtn")).click();
		Thread.sleep(2000);
	}
	
	//Pertenece al proceso de portabilidad.
	public static void PortabilidadSeleccionDeLinea(WebDriver driver) throws InterruptedException{
		
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		List<WebElement> radioButtons =driver.findElements(By.xpath("//span[@class=\'slds-radio--faux\']"));
		radioButtons.get(0).click();
		Thread.sleep(1000);
		driver.findElement(By.id("StepShowActiveLines_nextBtn")).click();
	}
	
	public static void NumeroAPortar(WebDriver driver) throws InterruptedException {
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		Select picklist = new Select(driver.findElement(By.id("SelectCompany")));
		picklist.selectByIndex(2);
		Thread.sleep(1000);
		driver.findElement(By.id("PortabilityNumber")).sendKeys(generatePortabilityNumber());
		//driver.findElement(By.id("PortabilityNumber")).sendKeys("1112131448");
		Thread.sleep(1000);
		driver.findElement(By.id("IPValidateMSISDN")).click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		Thread.sleep(1000);
		driver.findElement(By.id("StepPortabilityNumber_nextBtn")).click();
		Thread.sleep(3000);
	}
	
	public static void pasoDocumentacion (WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait (driver, 40);
		
		driver.switchTo().defaultContent();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe"));
		int dimension = iframe.size();
		iframe.get(dimension-1).click();
		driver.switchTo().frame(dimension-1);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Documentation_nextBtn']")));//*[@id="Documentation_nextBtn"]
		driver.findElement(By.xpath("//div[@id='Documentation_nextBtn']")).click();
		Thread.sleep(1000);
		
		/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_brand ng-binding' and contains(text(),Finalizar)]")));
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand ng-binding' and contains(text(),Finalizar)]")).click();
		Thread.sleep(1000);*/
		
		
		driver.switchTo().defaultContent();
	}

	public static String generateNIP() {
		int number = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
		return "" + number;
	}
	
	public static String generatePortabilityNumber() {
		int number1 = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
		int number2 = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
		return "" + number1 + number2;
	}
}