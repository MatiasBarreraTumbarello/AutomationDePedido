package com.automation.izzi;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoDeAutogestion {
	
	private WebDriver driver;
	public int tiempo= 4000;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://sittest-izzimx.cs125.force.com/portal");
		driver.findElement ( By.id ("username")). sendKeys ("lsalas_community@yopmail.com.sittest");
		driver.findElement ( By.id ("password")). sendKeys ("izzi12345");
		
		driver.findElement (By.xpath ("// input [@ class = 'button r4 wide primary']")). click ();
		Thread.sleep(5000);

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	
		
		
		Thread.sleep(20000);
		
	}
	@Test
	
	public void IniciarContratacion () throws InterruptedException {
		driver.switchTo().frame(0);
		new WebDriverWait (driver, 20)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		driver.findElement (By.xpath ("// button [@ class ='slds-button slds-button_brand btnCommunity']")). click ();
		
		
	}
}
