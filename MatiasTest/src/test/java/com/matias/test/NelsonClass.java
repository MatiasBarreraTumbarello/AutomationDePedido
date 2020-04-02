package com.matias.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class NelsonClass {
	

	public static void clickEntregaDePedido (WebDriver driver) throws InterruptedException{
		By iFrame1 = By.id("iFrameResizer0");
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		
		//driver.findElement(iFrame1).isDisplayed();
		driver.switchTo().frame("iFrameResizer0");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
	}
	
	public static void accid (WebDriver driver) {
		//8952140061736667340F
		driver.switchTo().frame("iFrameResizer1");//*[@id="iFrameResizer1"]
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		driver.findElement(By.xpath("//input[@id=\'ICCID\']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//input[@id='ICCIDVal']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//div[@id='ValidarICCID']/p")).click();

		
		//Las siguientes lineas nos permiten encontrar el iframe que contiene un xpath definido
		/*int size = driver.findElements(By.tagName("iFrame")).size();
		System.out.println("Hay "+size+" iframes en esta seccion");
		for (int i= 0; i <= size; i++) { //devuelve 1 si encuentra el xpath en cualquier iframe de la lista
			driver.switchTo().frame(i);
			int total = driver.findElements(By.xpath("//input[@id=\'ICCID\']")).size();
			System.out.println(total);
			driver.switchTo().defaultContent();
		}*/
	
	}
	
}
