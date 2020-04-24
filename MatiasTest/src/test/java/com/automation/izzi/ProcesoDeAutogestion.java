package com.automation.izzi;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoDeAutogestion {
	
	private WebDriver driver;
	public int tiempo= 4000;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://sittest-izzimx.cs125.force.com/portal");
		driver.findElement ( By.id ("username")). sendKeys ("lsalas_community@yopmail.com.sittest");
		driver.findElement ( By.id ("password")). sendKeys ("izzi12345");
		driver.findElement (By.xpath ("// input [@ class = 'button r4 wide primary']")). click ();
		Thread.sleep(5000);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		Thread.sleep(20000);
	}
		

		
	
	@Test
	public void IniciarContratacion () throws InterruptedException {
		driver.switchTo().frame(0);
		new WebDriverWait (driver, 20)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		driver.findElement (By.xpath ("// button [@ class ='slds-button slds-button_brand btnCommunity']")). click ();
		driver.switchTo().defaultContent();
		
		SeleccionDelPlan(driver);
		Thread.sleep(4000);
		
		//-----------------------Seccion: Dispositivos
		
		Dispositivos(driver, 0);
		
		// Solo funciona al seleccionar Compra de Equipo
		//SeleccionDeDispositivo(driver);
		
		
		//----------- Check: No estoy interesado en estos equipos.
		
		//desinteresEquipo(driver);
		
		
		//-----------------------Seccion: Validacion de Dispositivos
		
		/* Para usar los metodos de MatiasClass es necesario cambiar el valor de 1 a 0 del llamamiento RoynerClass.dispositivos()
		 * Y despues comentar los llamamientos de FranciscoClass.desinteresEquipo() */
		 
		/* Para esta seccion es necesario comentar uno de las 2 lineas de codigos siguientes (IMEI o Dispositivos)*/
		
		//ValidacionImei(driver);
		ValidacionDispositivo(driver);
		
		
		
		//----------------------Portabilidad------------------------------
		
		PortabilidadNo(driver);
		//--------------------------------
		TipoDeEntrega(driver);
		
		//----------------------Resumen de Compra-------------------------
		ResumenDeCompra(driver);
		
		Thread.sleep(3000);
	}
	//------------------------------------------------METODOS---------------------------------------------------------
	public static void SeleccionDelPlan(WebDriver driver) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer1")));
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		driver.switchTo().frame(frame);	
		Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement seleccionar = wait.until(ExpectedConditions.elementToBeClickable(By.id("block_01tc0000007pvuiAAA")));
		Thread.sleep(1000);
		seleccionar.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("PlanSelection_nextBtn")));
		while(siguiente.isEnabled() && siguiente.isDisplayed()) {
			Thread.sleep(1000);
			siguiente.click();
		}
		Thread.sleep(10000);
	}
	
	public static void Dispositivos(WebDriver driver, Integer index) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioDevices")));
		List<WebElement> opt = driver.findElements(By.id("RadioDevices"));
		opt.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("StepDevicesSelect_nextBtn")).click();
		Thread.sleep(5000);
	}
	
	public static void SeleccionDeDispositivo(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement opt = new WebDriverWait(driver, 40)
		        .until(ExpectedConditions.elementToBeClickable(By.id("block_01t3K000000HEDoQAO")));
		opt.findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		driver.findElement(By.id("vlcCart_Top")).findElement(By.xpath(".//div[1]")).click();
		WebElement btn = driver.findElement(By.id("StepChooseDevices_nextBtn"));
		while(btn.isEnabled() && btn.isDisplayed()) {
			Thread.sleep(1000);
			btn.click();
		}
		Thread.sleep(2000);
	}
	
	public static void ValidacionImei(WebDriver driver) throws InterruptedException {
		int tiempo= 5000;
		new WebDriverWait(driver, 40)
		        .until(ExpectedConditions.elementToBeClickable(By.id("RadioSelectMethod")));
		List<WebElement> mdv = driver.findElements(By.id("RadioSelectMethod"));
		Thread.sleep(tiempo);
		mdv.get(0).findElement(By.xpath("./..")).click();
		Thread.sleep(tiempo);
		driver.findElement(By.xpath("//input[@id=\'NumberIMEI\']")).sendKeys("355576090532169");
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id=\'IPAValidateIMEI\']/p")).click();
		Thread.sleep(tiempo);
		driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
		Thread.sleep(tiempo);
	}
	
		
	//Este metodo se utiliza una vez elegido la opcion "Trae tu equipo a IZZI" en la seccion: Dispositivo
	//Solo puede elegir entre la validacion por IMEI o validacion por disposiivo
	public static void ValidacionDispositivo(WebDriver driver) {
		try {
			int tiempo= 5000;
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioSelectMethod")));
			List<WebElement> mdv = driver.findElements(By.id("RadioSelectMethod"));
			Thread.sleep(tiempo);
			mdv.get(1).findElement(By.xpath("./..")).click();

			Thread.sleep(tiempo);
			driver.findElement(By.xpath("//select[@id=\'SelectBrand\']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//option[@label='BITTIUM']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//select[@id=\'SelectModel\']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//option[@label='Tough Mobile']")).click();
			//Thread.sleep(tiempo);
			driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
			Thread.sleep(tiempo);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	public  void PortabilidadNo(WebDriver driver) {
			
		try {
			new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.id("OptionPortability")));
			Thread.sleep(1000);
			
			List<WebElement> web = driver.findElements(By.id("OptionPortability"));
			web.get(1).findElement(By.xpath("./..")).click();
			Thread.sleep(tiempo);
			
			WebElement btn = driver.findElement(By.xpath("//*[@id=\'StepDeviceValidation_nextBtn\']/p"));
			while(btn.isEnabled() && btn.isDisplayed()) {
				Thread.sleep(1000);
				btn.click();
			}
			Thread.sleep(tiempo);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void TipoDeEntrega(WebDriver driver) throws InterruptedException {
		new WebDriverWait(driver, 40)
		        .until(ExpectedConditions.elementToBeClickable(By.id("RadioProfileNoVentas")));
		//List<WebElement> opt = driver.findElements(By.id("RadioProfileNoVentas"));
		driver.findElement(By.id("RadioProfileNoVentas")).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("StepSaleProcessDevice_nextBtn")).click();
		Thread.sleep(5000);
	}
		
		
	//Este metodo es el paso final de la gestion de compra, donde se muestra el resumente y pasa a la siguiente pestaña de finalizar compra
	public static void ResumenDeCompra(WebDriver driver) {
		try {
			int tiempo= 5000;
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
			WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id=\'DeliveryHomeSummary_nextBtn\']/p")));
			while(btn.isDisplayed() && btn.isEnabled()) {
				Thread.sleep(1000);
				btn.click();
			}
			Thread.sleep(tiempo);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
			WebElement btnFinish = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\'slds-button slds-button_brand ng-binding\']")));
			btnFinish.click();
			Thread.sleep(tiempo);
			//Nos muestra el numero de pedido 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Compra de Equipo-> Check: No estoy interesado en estos equipos.
	
	public static void desinteresEquipo(WebDriver driver)throws InterruptedException {
		
		
		driver.findElement(By.id("CheckboxDontWantDevice")).findElement(By.xpath("./..")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("StepChooseDevices_nextBtn")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("slds-button slds-button_brand ng-binding")). click();
		Thread.sleep(2000);
		
	}
}
