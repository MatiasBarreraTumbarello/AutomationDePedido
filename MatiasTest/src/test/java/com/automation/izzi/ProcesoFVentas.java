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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoFVentas {

	private WebDriver driver;

	private WebDriverWait wait;
	public int tiempo = 2000;
	
	private int pStepDispositivos = 0;
	private int pStepValidacionDeDispositivos = 1;
	private int pStepPortabilidad= 0;
	private int pStepTipoDeEntrega= 0;
	
	private boolean pOptValidacionPorDispositivo = true;
	
	/** 
	 * En eclipse para ir al desarrollo del metodo debo hacer CTRL + Click al llamamiento del mismo.
	 * En algunos casos hay metodos que estan comentados, 
	 * en caso de querer cambiar las elecciones solo basta con descomentar uno y comentar el otro.
	 * 
	 * Al iniciar el setUp se encarga del ingreso en la aplicacion y la redireccion a la pagina correcta
	 */	
	@Before

	public void SetUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(" https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQILXXLk7kXMN1rMnQcJFtEXP0AJTCgjKsXYZVhzPqr.9L2rd9P4DRmuE.suispsvJDYX6AxTWP6OGeO7kaZRLWk87VYK");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/n/Nueva_Venta");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 40);
		
	}
	
	/**
	 * El metodo principal donde se ejecutan todos los demas metodos.
	 * 
	 * @throws InterruptedException			Intercepta los errores de ejecucion.
	 */

	@Test

	public void Main() throws InterruptedException {
		StepBuscarCliente();
		StepPlanes();
		StepDispositivos(pStepDispositivos);
		StepValidacionDeDispositivos(pStepValidacionDeDispositivos);
		StepPortabilidad(pStepPortabilidad);
		StepTipoDeEntrega(pStepTipoDeEntrega);
		StepResumenDeCompra();
		Thread.sleep(tiempo);
	}
	
	/**
	 * En este paso se ingresan los datos del cliente que va a realizar la compra
	 * 
	 * @throws InterruptedException
	 */
	public void StepBuscarCliente() throws InterruptedException {
		WebElement frm = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer0")));
		driver.switchTo().frame(frm);
		Select pklBuscarPor = new Select(driver.findElement(By.id("selectAccountOrTlfn")));
		pklBuscarPor.selectByIndex(2);
		Thread.sleep(tiempo);
		driver.findElement(By.xpath("//input[@id='seibelUser']")).sendKeys("19964717");
		
		WebElement btnBuscarCliente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='IP_validClient']")));
		btnBuscarCliente.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioUpdateDatosSeibel")));

		OptActualizarCliente(0);
	}
	
	/**
	 * Selecciona entre actualizar los datos del cliente o no.
	 * 
	 * @param index			0 = "NO" || 1 = "SI"
	 * @throws InterruptedException
	 */
	public void OptActualizarCliente(Integer index) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioUpdateDatosSeibel")));
		List<WebElement> optActualizarInfo = driver.findElements(By.xpath("//*[@id=\'RadioUpdateDatosSeibel\']"));
		Thread.sleep(2000);
		optActualizarInfo.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchClient_nextBtn")));

		driver.findElement(By.xpath("//*[@id=\'SearchClient_nextBtn\']/p")).click();
		Thread.sleep(2000);
		
		if(index == 1)
			StepDatosAdicionalesDelCliente();
	}
	
	/**
	 * Actualiza los datos del cliente en caso de que se haya seleccionado esa opcion.
	 * 
	 * @throws InterruptedException
	 */
	public void StepDatosAdicionalesDelCliente() throws InterruptedException {	
		WaitForInvisibleSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("TextNumberPhone")));
		WebElement txtPhone = driver.findElement(By.id("TextNumberPhone"));
		txtPhone.clear();
		txtPhone.sendKeys("5569310423");
		Thread.sleep(1000);
		
		WebElement txtEmail = driver.findElement(By.id("TextEmail"));
		txtEmail.clear();
		txtEmail.sendKeys("scardozo@labsxd.com");
		Thread.sleep(1000);
		
		WebElement chkInformacionPrincipal = driver.findElement(By.id("CheckboxPrincipal"));
		chkInformacionPrincipal.findElement(By.xpath("./..")).click();
		Thread.sleep(1000);
		
		WebElement btnSiguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("AditionalInfo_nextBtn")));
		btnSiguiente.click();
		
		Thread.sleep(2000);
	}
	
	/**
	 * Selecciona el plan en el paso Planes.
	 * 
	 * @throws InterruptedException
	 */
	public void StepPlanes() throws InterruptedException{
		 WaitForInvisibleSpinner();
		 
		 // Para elegir otro plan es necesario cambiar el id por el del plan que se desea seleccionar.
		 WebElement optPlan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'block_01tc0000007pvuiAAA\']")));
		 Thread.sleep(1000);
		 optPlan.click();
		 
		 WaitForInvisibleSpinner();
		 WebElement btnSiguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("PlanSelection_nextBtn")));
		 while(btnSiguiente.isEnabled() && btnSiguiente.isDisplayed()) {
			 Thread.sleep(1000);
			 btnSiguiente.click();
		 }
		 
		 
		 Thread.sleep(10000);
	}
	
	/**
	 * Selecciona entre usar un dispositivo propio o adquirir uno nuevo
	 * 
	 * @param index			0 = "Dispositivo propio" || 1 = "Compra de dispositivo"
	 * @throws InterruptedException
	 */
	public void StepDispositivos(int index) throws InterruptedException {
		WaitForInvisibleSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioDevices")));
		List<WebElement> optTipoDeDispositivo = driver.findElements(By.id("RadioDevices"));
		optTipoDeDispositivo.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("StepDevicesSelect_nextBtn")).click();
		Thread.sleep(5000);
		if (index == 1) {

			StepSeleccionDeDispositivo();
			// Selecciona el check que indica que el cliente no esta interesado en estos equipos.
			// OptDesinteresEquipo();
		}
	}
	
	/**
	 * Selecciona un dispositivo en el paso Seleccion de Dispositivo.
	 * 
	 * @throws InterruptedException
	 */
	public void StepSeleccionDeDispositivo() throws InterruptedException {
		WaitForInvisibleSpinner();
		WebElement optDispositivo = wait.until(ExpectedConditions.elementToBeClickable(By.id("block_01t3K000000HEDoQAO")));
		optDispositivo.findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		
		WaitForInvisibleSpinner();
		driver.findElement(By.id("vlcCart_Top")).findElement(By.xpath(".//div[1]")).click();
		WebElement btnSiguiente = driver.findElement(By.id("StepChooseDevices_nextBtn"));
		while(btnSiguiente.isEnabled() && btnSiguiente.isDisplayed()) {
			Thread.sleep(1000);
			btnSiguiente.click();
		}
		Thread.sleep(2000);
	}

	/**
	 * Selecciona el metodo de validacion de compatibilidad con Izzi.
	 * 
	 * @param index			0 = "IMEI" || 1 = "Dispositivo"
	 * @throws InterruptedException
	 */
	public void StepValidacionDeDispositivos(int index) throws InterruptedException {
		WaitForInvisibleSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioSelectMethod")));
		List<WebElement> optMetodoDeValidacion = driver.findElements(By.id("RadioSelectMethod"));
		Thread.sleep(tiempo);
		optMetodoDeValidacion.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(tiempo);
		
		if (index == 0)
			OptValidacionPorImei();
		else
			// Se debe cambiar al false para no seleccionar dispositivos de la lista
			OptValidacionPorDispositivo(pOptValidacionPorDispositivo);
	}
	
	/**
	 * Realiza la validacion por IMEI.
	 * 
	 * @throws InterruptedException
	 */
	public void OptValidacionPorImei() throws InterruptedException {
		// Imei valido
		driver.findElement(By.xpath("//input[@id=\'NumberIMEI\']")).sendKeys("355576090532169");
		
		// Imei invalido
		// driver.findElement(By.xpath("//input[@id=\'NumberIMEI\']")).sendKeys("000000000000000");
		WebElement btnValidar = driver.findElement(By.xpath("//div[@id=\'IPAValidateIMEI\']/p"));
		btnValidar.click();
		Thread.sleep(tiempo);

		WaitForInvisibleSpinner();
		List<WebElement> optListVerEquiposCompatibles = driver.findElements(By.id("RadioBuyDevices"));
		
		boolean optVerEquiposCompatibles = false;
		if (optListVerEquiposCompatibles.size() != 0) {
			optVerEquiposCompatibles = true;
		}
		
		driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
		Thread.sleep(tiempo);
		
		if (optVerEquiposCompatibles)
			StepSeleccionDeDispositivo();
	}
	
	/**
	 * Realiza la validacion por dispositivo. 
	 * Toma el parametro booleano isValid el cual establece si se selecciona un dispositivo de la lista 
	 * o si por el contrario se envia al paso Seleccionar dispositivo
	 * 
	 * @param isValid			true = "Selecciona un dispositivo de la lista" || false = "Habilita la opcion 'seleccion de dispositivo'"
	 * @throws InterruptedException
	 */
	public void OptValidacionPorDispositivo(boolean isValid) throws InterruptedException {
		boolean optVerEquiposCompatibles = false;
		
		// Si el parametro es false:
		if (!isValid) {
			// El flujo va a seleccion de dispositivo
			WebElement chkDispositivoNoEncontrado = driver.findElement(By.xpath("//input[@id=\'CheckCompatibility\']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].style.display = 'block'; "
					+ "arguments[0].style.zIndex = '999999'; "
					+ "arguments[0].click()", chkDispositivoNoEncontrado);
			
			optVerEquiposCompatibles = true;
			
		// En caso contrario selecciona un equipo de la lista y continua a portabilidad
		}else {
			// Selecciona la marca
			driver.findElement(By.xpath("//select[@id=\'SelectBrand\']")).click();
			driver.findElement(By.xpath("//option[@label='BITTIUM']")).click();
			
			// Selecciona el modelo
			driver.findElement(By.xpath("//select[@id=\'SelectModel\']")).click();
			driver.findElement(By.xpath("//option[@label='Tough Mobile']")).click();
		}
		
		driver.findElement(By.xpath("//div[@id='StepApprovedDevice_nextBtn']")).click();
		Thread.sleep(tiempo);
		
		if (optVerEquiposCompatibles)
			StepSeleccionDeDispositivo();
	}
		
	/**
	 * Selecciona la opcion de portabilidad.
	 * 
	 * @param index			0 = "SI" || 1 = "NO"
	 * @throws InterruptedException
	 */
	public void StepPortabilidad(int index) throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("OptionPortability")));
		Thread.sleep(1000);
		
		List<WebElement> optPortarNumeroActual = driver.findElements(By.id("OptionPortability"));
		optPortarNumeroActual.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(tiempo);
		
		WebElement btnSiguiente = driver.findElement(By.xpath("//*[@id=\'StepDeviceValidation_nextBtn\']/p"));
		while(btnSiguiente.isEnabled() && btnSiguiente.isDisplayed()) {
			Thread.sleep(1000);
			btnSiguiente.click();
		}
		Thread.sleep(tiempo);
	}
	
	/**
	 * Selecciona el tipo de entrega en el paso Tipo de Entrega
	 * 
	 * @param index			0 = "Entrega en Suscursal" || 1 = "Entrega en Domicilio"
	 * @throws InterruptedException
	 */
	public void StepTipoDeEntrega(int index) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioProfileNoVentas")));
		List<WebElement> optTipoDeEntrega = driver.findElements(By.xpath("//input[@id='RadioProfileNoVentas']"));
		Thread.sleep(2000);
		
		// Si  la entrega es en Sucursal:
		if (index == 0) {
			optTipoDeEntrega.get(index).findElement(By.xpath("./..")).click();
			Thread.sleep(2000);
			List<WebElement> stock = driver.findElements(By.xpath("//span[@class='slds-radio--faux ng-scope']"));
			//driver.findElement(By.id("RadioRetiroOtraSucursal|0")).click();//ng-form[@id='RadioRetiroOtraSucursal|0']
			
			//Verifica si se puede seleccionar una sucursal
			if (stock.get(0).isEnabled() && stock.get(0).isDisplayed()) {
				
				stock.get(1).click();
				
				//selecciona la sucursal "ATIZAPAN"
				driver.findElement(By.xpath("//*[@id=\'SelectSucursal\']/option[3]")).click(); 
				Thread.sleep(1000);

				
				//selecciona el boton validar
				driver.findElement(By.xpath("//div[@id=\'WrapperCheckDeviceStockSucursal\']")).click(); 
			}

			Thread.sleep(2000);
			
		//En caso contrario, la entrega es en Domicilio
		} else { 
			optTipoDeEntrega.get(index).findElement(By.xpath("./..")).click();
			Thread.sleep(2000);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("StepSaleProcessDevice_nextBtn")));
		driver.findElement(By.id("StepSaleProcessDevice_nextBtn")).click();
		Thread.sleep(5000);
	}
	
	/**
	 * Este metodo es el paso final de la gestion de compra, donde se muestra el resumen y pasa a la siguiente pantalla de finalizar compra
	 * 
	 * @throws InterruptedException
	 */
	public void StepResumenDeCompra() throws InterruptedException {
		WaitForInvisibleSpinner();
		WebElement btnSiguiente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id=\'DeliveryHomeSummary_nextBtn\']/p")));
		while(btnSiguiente.isDisplayed() && btnSiguiente.isEnabled()) {
			Thread.sleep(1000);
			btnSiguiente.click();
		}
		Thread.sleep(tiempo);
		
		WaitForInvisibleSpinner();
		WebElement btnFinish = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\'slds-button slds-button_brand ng-binding\']")));
		btnFinish.click();
		Thread.sleep(tiempo);
		//Nos muestra el numero de pedido
	}

	/**
	 * Selecciona el check que indica que el cliente no esta interesado en ningun equipo.
	 * 
	 * @throws InterruptedException
	 */
	public void OptDesinteresEquipo() throws InterruptedException {
		driver.findElement(By.id("CheckboxDontWantDevice")).findElement(By.xpath("./..")).click();
		Thread.sleep(tiempo);
		driver.findElement(By.id("StepChooseDevices_nextBtn")).click();
		Thread.sleep(tiempo);
		driver.findElement(By.xpath("slds-button slds-button_brand ng-binding")). click();
		Thread.sleep(tiempo);
		
	}

	/**
	 * Retrasa la ejecucion hasta que spinner sea invisible
	 */
	public void WaitForInvisibleSpinner() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
	}

}