package com.automation.izzi;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class ProcesoFVentas {
	
	private WebDriver driver;
	public int tiempo= 4000;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQIO6gHmnB.8N3_W6jM.Okgok8oGGJJjZsB0sSWaUFNcMKrmNEw23Eh1EVXCzlTz130J91Oz7CM1wKSMX5qVBYprO9B16");
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/n/Nueva_Venta");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		Thread.sleep(20000);
		
	}
		//************************LEER*****************************************************************
		// En eclipse para ir al desarrollo del metodo debo hacer CTRL + Click al llamamiento del mismo.
		// En algunos casos hay metodos que estan comentados, en caso de querer cambiar las elecciones solo basta con descomentar uno y comentar el otro.
	
	@Test
	public void testScript() throws InterruptedException {
		
		//--------------------------------- Cuenta Siebel  ---------------------De la lina 42 a 51 
		driver.switchTo().frame(0);
		Select picklist = new Select(driver.findElement(By.id("selectAccountOrTlfn")));
		picklist.selectByIndex(2);
		driver.findElement(By.xpath("//input[@id='seibelUser']")).sendKeys("19964717");
		//Boton: Buscar Cliente------------------------------------------------
		WebElement res = new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='IP_validClient']")));
		driver.findElement(By.id(res.getAttribute("id"))).click();
		new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.id("RadioUpdateDatosSeibel")));
		// Cambiar indice a 1 para seleccionar opcion "si"
		actualizarCliente(driver, 0);// Para la seleccion de actualizacion utilizar indices: 1 = "si", 0 = "no"
		//driver.quit();

		//------------------------------------PLANES-----------------------
		planesActualizado(driver);
		Thread.sleep(4000);
		
		//-----------------------Seccion: Dispositivos---------------------
		dispositivos(driver, 0);
	
		//-----------------------Seccion: Validacion de Dispositivos------
		
		/* Para usar los metodos de MatiasClass es necesario cambiar el valor de 1 a 0 del llamamiento RoynerClass.dispositivos()
		 * Y despues comentar los llamamientos de FranciscoClass.desinteresEquipo() */
		 
		/* Para esta seccion es necesario comentar uno de las 2 lineas de codigos siguientes (IMEI o Dispositivos)*/
		
		//validacionImei(driver);
		validacionDispositivo(driver, 1);
		
		//----------------------Portabilidad------------------------------
		//Nelson
		portabilidadNo(driver);
		//----------------------------------------------------------------
		tipoDeEntrega(driver, 1);
		//----------------------Seccion: Resumen de Compra
		resumenDeCompra(driver);
		Thread.sleep(3000);

	}
	//------------------------------------------------METODOS DESARROLLO-------------------------------------------------------
	public static void planesActualizado(WebDriver driver) throws InterruptedException{
		 Thread.sleep(2000);
		 WebDriverWait wait = new WebDriverWait(driver, 40);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		 WebElement seleccionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'block_01tc0000007pvuiAAA\']")));
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
	//--------------------------------------------------------------------------------------------------------------------------
	public static void dispositivos(WebDriver driver, Integer index) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioDevices")));
		List<WebElement> opt = driver.findElements(By.id("RadioDevices"));
		opt.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("StepDevicesSelect_nextBtn")).click();
		Thread.sleep(5000);
		if (index == 1) {
			// Solo funciona al seleccionar Compra de Equipo
			seleccionDeDispositivo(driver);
			//----------- Check: No estoy interesado en estos equipos.--------
			//desinteresEquipo(driver);
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
		//Este metodo se utiliza una vez elegido la opcion "Trae tu equipo a IZZI" en la seccion: Dispositivo
		//Solo puede elegir entre la validacion por IMEI o validacion por disposiivo
		public static void validacionDispositivo(WebDriver driver, int index) {
			try {
				int tiempo= 5000;
				WebDriverWait wait = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioSelectMethod")));
				List<WebElement> mdv = driver.findElements(By.id("RadioSelectMethod"));
				Thread.sleep(tiempo);
				mdv.get(1).findElement(By.xpath("./..")).click();
				Thread.sleep(tiempo);

				boolean seleccionarDispositivo = false;
				
				if (index == 1) {
					WebElement check = driver.findElement(By.xpath("//input[@id=\'CheckCompatibility\']"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999'; arguments[0].click()", check);
					
					seleccionarDispositivo = true;
				}else {

					driver.findElement(By.xpath("//select[@id=\'SelectBrand\']")).click();
					//Thread.sleep(3000);
					driver.findElement(By.xpath("//option[@label='BITTIUM']")).click();
					//Thread.sleep(3000);
					driver.findElement(By.xpath("//select[@id=\'SelectModel\']")).click();
					//Thread.sleep(3000);
					driver.findElement(By.xpath("//option[@label='Tough Mobile']")).click();
					//Thread.sleep(tiempo);
				}
				
				driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
				Thread.sleep(tiempo);
				
				if (seleccionarDispositivo) {
					seleccionDeDispositivo(driver);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//--------------------------------------------------------------------------------------------------------------------------
		public  void portabilidadNo(WebDriver driver) {
			
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
		//--------------------------------------------------------------------------------------------------------------------------
		public static void tipoDeEntrega(WebDriver driver, int index) throws InterruptedException {
			new WebDriverWait(driver, 40)
			        .until(ExpectedConditions.elementToBeClickable(By.id("RadioProfileNoVentas")));
			List<WebElement> opt = driver.findElements(By.id("//input[@id='RadioProfileNoVentas']"));
			if (index == 0) { //Entrega en sucursal
			opt.get(index).findElement(By.xpath("./..")).click();
			Thread.sleep(2000);
			List<WebElement> stock = driver.findElements(By.xpath("//input[@id='RadioRetiroOtraSucursal']"));
			if (stock.get(0).isEnabled())
				stock.get(0).click();
			Thread.sleep(2000);
			
			} else { //Entrega en domicilio
			opt.get(index).findElement(By.xpath("./..")).click();
			Thread.sleep(2000);
			}
			driver.findElement(By.id("StepSaleProcessDevice_nextBtn")).click();
			Thread.sleep(5000);
		}
		//--------------------------------------------------------------------------------------------------------------------------
		//Este metodo es el paso final de la gestion de compra, donde se muestra el resumente y pasa a la siguiente pestaï¿½a de finalizar compra
		public static void resumenDeCompra(WebDriver driver) {
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
		//--------------------------------------------------------------------------------------------------------------------------
	public static void actualizarCliente(WebDriver driver, Integer index) throws InterruptedException {
		new WebDriverWait(driver, 40)
        	.until(ExpectedConditions.elementToBeClickable(By.id("RadioUpdateDatosSeibel")));
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'RadioUpdateDatosSeibel\']"));
		Thread.sleep(2000);
		opt.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		//
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchClient_nextBtn")));
		//
		driver.findElement(By.xpath("//*[@id=\'SearchClient_nextBtn\']/p")).click();
		Thread.sleep(2000);
		
		if(index == 1)
			informacionDeCliente(driver);
	}
		//--------------------------------------------------------------------------------------------------------------------------
	public static void informacionDeCliente(WebDriver driver) throws InterruptedException {	
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("TextNumberPhone")));
		WebElement phone = driver.findElement(By.id("TextNumberPhone"));
		phone.clear();
		phone.sendKeys("5569310423");
		Thread.sleep(2000);
		
		WebElement email = driver.findElement(By.id("TextEmail"));
		email.clear();
		email.sendKeys("scardozo@labsxd.com");
		Thread.sleep(1000);
		driver.findElement(By.id("CheckboxPrincipal")).findElement(By.xpath("./..")).click();
		WebElement res = new WebDriverWait(driver, 40)
		        .until(ExpectedConditions.elementToBeClickable(By.id("AditionalInfo_nextBtn")));
		
		Thread.sleep(2000);
		driver.findElement(By.id(res.getAttribute("id"))).click();
		
		Thread.sleep(2000);
	}
		//--------------------------------------------------------------------------------------------------------------------------
		
	
	public static void seleccionDeDispositivo(WebDriver driver) throws InterruptedException {
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
	
		//--------------------------------------------------------------------------------------------------------------------------
		public static void validacionImei(WebDriver driver) throws InterruptedException {

			int tiempo= 5000;
			new WebDriverWait(driver, 40)
			        .until(ExpectedConditions.elementToBeClickable(By.id("RadioSelectMethod")));
			List<WebElement> mdv = driver.findElements(By.id("RadioSelectMethod"));
			Thread.sleep(tiempo);
			mdv.get(0).findElement(By.xpath("./..")).click();
			Thread.sleep(tiempo);
			//driver.findElement(By.xpath("//input[@id=\'NumberIMEI\']")).sendKeys("355576090532169"); // Válido
			driver.findElement(By.xpath("//input[@id=\'NumberIMEI\']")).sendKeys("000000000000000");
			//Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@id=\'IPAValidateIMEI\']/p")).click();
			Thread.sleep(tiempo);
			
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
			
			List<WebElement> buy = driver.findElements(By.id("RadioBuyDevices"));
			boolean seleccionarDispositivo = false;
			if (buy.get(0).isEnabled() && buy.get(0).isDisplayed()) {
				seleccionarDispositivo = true;
			}
			
			driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
			Thread.sleep(tiempo);
			
			if (seleccionarDispositivo) {
				seleccionDeDispositivo(driver);
			}
			
		
	}
		//--------------------------------------------------------------------------------------------------------------------------
		//Compra de Equipo-> Check: No estoy interesado en estos equipos.
	public static void desinteresEquipo(WebDriver driver)throws InterruptedException {
		
		
		driver.findElement(By.id("CheckboxDontWantDevice")).findElement(By.xpath("./..")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("StepChooseDevices_nextBtn")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("slds-button slds-button_brand ng-binding")). click();
		Thread.sleep(2000);
		
	}
	
	/*@After
	public void tearDown() {
		driver.quit();
	}*/
	
	
	
	
}