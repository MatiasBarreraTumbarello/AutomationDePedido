package com.automation.izzi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoCambioDeSim {
	
	private Config config = new Config();
	private WebDriver driver;
	private WebDriverWait wait;
	

	@Before
	public void setUp() throws InterruptedException, IOException {
		
		config.initBrowser();
		config.goToAccountLink();
		driver = config.driver;

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	@Test
	public void testScript() throws InterruptedException {
	
		cambioDeSim();
		selecionSim();
		
	}
	public void cambioDeSim () throws InterruptedException{
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
		
	public void selecionSim () throws InterruptedException {
		
		config.waitForInvisibleSpinner();


		WebElement iframe = new WebDriverWait(driver,40).until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		iframe.click();
		driver.switchTo().frame(iframe);

		Thread.sleep(2000);

		WebElement opcion = new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ChooseSim\"]/div/ng-include/div/div[2]/ul/li")));
		opcion.click();
		driver.findElement(By.xpath("//div[@id='SIMSelection_nextBtn']")).click();
		//Paso3
		config.waitForInvisibleSpinner();
		
		driver.findElement(By.xpath("//input[@id='ICCID']")).sendKeys("12345678910111213143");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ICCIDVal']")).sendKeys("12345678910111213143");
		driver.findElement(By.xpath("//div[@id=\'DeliverySimCard_nextBtn\']")).click();
		//Paso4
		config.waitForInvisibleSpinner();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'doneAction-137\']/div/div/div[3]/div/button")).click();
		
	}
	

}