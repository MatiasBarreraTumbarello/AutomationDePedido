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
		//By btnEntrega = By.xpath("//button[@class='slds-button slds-button_neutral']");
		By iFrame1 = By.id("iFrameResizer0");
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		
		//driver.findElement(iFrame1).isDisplayed();
		driver.switchTo().frame("iFrameResizer0");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		driver.switchTo().defaultContent();
		
		
		
		
	}
	
	public static void accid (WebDriver driver) throws InterruptedException{
		//8952140061736667340F
		By entradaUno = By.xpath("/html/body/span/div/span/div/ng-view/div/div/bptree/child[5]/div/section/form/div[1]/div/child[2]/div/ng-form/div/div[1]/input");
		By entradaDos = By.xpath("/html/body/span/div/span/div/ng-view/div/div/bptree/child[5]/div/section/form/div[1]/div/child[2]/div/ng-form/div/div[1]/input");
		By btnValidar = By.xpath("//div[@id='ValidarICCID']/p");
		By iFrame2 = By.id("iFrameResizer3");
		
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		driver.switchTo().frame("iFrameResizer3");
		
		ewait.until(ExpectedConditions.visibilityOfElementLocated(entradaUno));
		//driver.findElement(entradaUno).click();
		
		driver.findElement(entradaUno).sendKeys("8952140061736667340F");
		driver.findElement(entradaDos).sendKeys("8952140061736667340F");
		driver.findElement(btnValidar).click();
		driver.switchTo().defaultContent();
		
	}
	
}
