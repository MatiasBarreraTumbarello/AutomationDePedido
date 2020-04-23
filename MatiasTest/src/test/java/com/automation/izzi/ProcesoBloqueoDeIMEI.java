package com.automation.izzi;

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

public class ProcesoBloqueoDeIMEI {
	
private WebDriver driver;

	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQD2yaP0ytv0YuFtsIiCPaxcMnjQ2iuO2TD1vqjRelc0BQiZzaEdA0WgN8_WlOtuN8nt8kuZnNVU4DslEDCNp.xNZoIxF");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	
	@Test
	public void Bloqueo() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[4]/button"));
		executor.executeScript("arguments[0].click();", boton);
		
		driver.switchTo().defaultContent();
		IMEI();
}
	
	public void IMEI() throws InterruptedException {
		
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		new WebDriverWait(driver, 40)
		
	.until(ExpectedConditions.elementToBeClickable(By.id("CheckIMEIRadio")));
		Thread.sleep(3000);
		
		
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'CheckIMEIRadio\']"));
		Thread.sleep(2000);
		opt.get(0).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\'NumberImei\']")).sendKeys("442267859858533");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id='RAValidationImei']")).click();
		Thread.sleep(2000);
	
		
	}
	}

