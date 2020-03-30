package com.matias.test;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class NelsonClass {

	private WebDriver driver;
	By btnEntrega = By.className("button[class='slds-button slds-button_neutral']");
	
	public void clickEntregaDePedido () {
		
		
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		WebElement boton = fwait.until(new Function<WebDriver,WebElement>(){
			
			public WebElement apply(WebDriver driver) {
				return driver.findElement(btnEntrega);
			}
		});		
		
		driver.findElement(btnEntrega).click();
		
		
	}
	
}
