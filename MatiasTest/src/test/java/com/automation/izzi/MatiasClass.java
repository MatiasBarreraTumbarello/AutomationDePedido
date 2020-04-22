package com.automation.izzi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MatiasClass{

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
		driver.findElement(By.id("Confirmation_nextBtn")).click(); //boton siguiente
		
		//Seccion: Resumen de compra
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("DeliveryHomeSummary_nextBtn")));
		driver.findElement(By.id("DeliveryHomeSummary_nextBtn")).click();//boton finalizar
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


		driver.findElement(By.xpath("//input[@id='NIP']")).sendKeys("4632");

	//	driver.findElement(By.xpath("//input[@id='NIP']")).sendKeys("9158");


		driver.findElement(By.xpath("//input[@id='NIP']")).sendKeys("9168");

		Thread.sleep(1000);
		
		driver.findElement(By.id("StepNumeroNip_nextBtn")).click();
		Thread.sleep(2000);
	}
	
	
	public static void pasoDocumentacion (WebDriver driver) {
		WebDriverWait wait = new WebDriverWait (driver, 40);
		
		driver.switchTo().defaultContent();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe"));
		int dimension = iframe.size();
		iframe.get(dimension-1).click();
		driver.switchTo().frame(dimension-1);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Documentation_nextBtn']")));//*[@id="Documentation_nextBtn"]
		driver.findElement(By.xpath("//div[@id='Documentation_nextBtn']")).click();
		
		driver.switchTo().defaultContent();
	}
	
	public static void equiposCompatibles (WebDriver driver) {
		WebDriverWait wait = new WebDriverWait (driver, 40);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("RadioBuyDevices|0")));
		
		List<WebElement> radioButton = driver.findElements(By.id("RadioBuyDevices|0"));
		
		radioButton.get(1).findElement(By.xpath("./..")).click();
	}
	
	public static void cambioDeSim (WebDriver driver) throws InterruptedException{
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Cambio de SIM"));
		executor.executeScript("arguments[0].click();", links.get(0));
		driver.switchTo().defaultContent();
	}
		
	public static void selecionSim (WebDriver driver) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));


		WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		iframe.click();
		driver.switchTo().frame(iframe);

		Thread.sleep(2000);

		WebElement opcion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ChooseSim\"]/div/ng-include/div/div[2]/ul/li")));
		opcion.click();
		driver.findElement(By.xpath("//*[@id=\'Step1_nextBtn\']")).click();
		//Paso3
		WebDriverWait wait2 = new WebDriverWait (driver, 40);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		driver.findElement(By.xpath("//*[@id=\'newIMSI\']")).sendKeys("123456789101113");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id=\'newICCID\']")).sendKeys("12345678910111213143");
		driver.findElement(By.xpath("//*[@id=\'Step2_nextBtn\']")).click();
		//Paso4
		WebDriverWait wait3 = new WebDriverWait (driver, 40);
		wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'doneAction-137\']/div/div/div[3]/div/button")).click();
		
	}
	

}