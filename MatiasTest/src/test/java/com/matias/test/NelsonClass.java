package com.matias.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NelsonClass {

	private WebDriver driver;
	
	public void clickEntregaDePedido () {
		By btnEntrega = By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button");		
		
		if(driver.findElement(btnEntrega).isDisplayed()) {
			
			driver.findElement(btnEntrega).click();
		}
		
	}
	
}
