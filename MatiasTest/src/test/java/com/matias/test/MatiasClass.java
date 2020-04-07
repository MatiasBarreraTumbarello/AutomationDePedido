package com.matias.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MatiasClass{

	public static void confirmarServicio(WebDriver driver) {
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
	
	public static void codigoNIP (WebDriver driver) {
		WebDriverWait wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		List<WebElement> cantIFrames = driver.findElements(By.xpath("//iframe"));
		int size = cantIFrames.size();
		driver.switchTo().frame(size - 1);
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("//ng-form[@id=\'RadioNIP|0\']")));
		List<WebElement> opciones = driver.findElements(By.id("RadioNIP"));
		
		opciones.get(0).findElement(By.xpath("./..")).click();
		driver.findElement(By.xpath("//input[@id=\'NIP\']")).sendKeys("6789");
		
		
	}
}
