package com.matias.test;

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
}