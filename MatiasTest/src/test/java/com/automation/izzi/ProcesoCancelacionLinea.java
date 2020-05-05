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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoCancelacionLinea {
	
	private Config config = new Config();
	private WebDriver driver;
	private WebDriverWait wait;
	
	@Before
	public void setUp() throws InterruptedException, IOException {
		
		config.initBrowser();
		config.goToAccountLink();
		driver = config.driver;
		wait = config.wait;
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	@Test
	public void cancelacion() throws InterruptedException {
		
		config.waitForInvisibleSpinner(wait);
		
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[5]/button"));
		executor.executeScript("arguments[0].click();", boton);
		
		driver.switchTo().defaultContent();
		linea(driver);
		
	}
	
	public void linea(WebDriver driver)throws InterruptedException {
		config.waitForInvisibleSpinner(wait);
		
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("Line-options")));
		List<WebElement> radioButton = driver.findElements(By.name("Line-options"));
		Thread.sleep(1000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", radioButton.get(1));
		//radioButton.get(0).findElement(By.xpath("./..")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("SelectLines_nextBtn")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("stepConfirmationCancelLinea_nextBtn")).click();
		Thread.sleep(2000);
		
		/*driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand ng-binding' and contains(text(),Finalizar)]")).click();
		Thread.sleep(2000);*/
}
}