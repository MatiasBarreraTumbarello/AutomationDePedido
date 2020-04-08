package com.automation.izzi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		List<WebElement> cantIFrames = driver.findElements(By.tagName("iFrame"));
		int size = cantIFrames.size();
		driver.switchTo().frame("iFrameResizer3");
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(size - 1));
		Thread.sleep(2000);
		
		//Descomentar cualquiera de las dos siguientes lineas, de acuerdo a la respuesta que desea colocar
		
		//Esta opcion afirma que el cliente cuenta con un nro NIP
		//WebElement myDynamicElement = 
		//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("RadioNIP")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='a2c3K0000000hGOQAY-4']")));
		List<WebElement> boton = driver.findElements(By.xpath("/html/body/span/div/span/div/ng-view/div/div/bptree/child[4]/div/section/form/div[1]/div/child[1]/div/ng-form/div/div[1]"));
		Thread.sleep(2000);
		boton.get(0).findElement(By.xpath("./..")).click();
		
		//Esta opcion niega que el cliente cuenta con un nro NIP
		//driver.findElement(By.xpath("//input[@id='RadioNIP' and @value='No']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='NIP']")).sendKeys("4631");
		
		
	}
	
}