package com.matias.test;

import static org.junit.Assert.assertTrue;

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

	private WebDriver driver;
	
	
	public void clickEntregaDePedido () {
		//By btnEntrega = By.xpath("//button[@class='slds-button slds-button_neutral']");
		
		WebDriverWait ewait = new WebDriverWait(driver, 20);
		
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));

		ewait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_neutral']")));
		
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral']")).click();
		
		
		
		
		
	}
	
}
