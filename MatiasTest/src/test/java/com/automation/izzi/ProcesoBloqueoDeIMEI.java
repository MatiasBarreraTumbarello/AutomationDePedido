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

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQGS.hRPmq7BfqeveksXxEzx.qax3NtTkzOYWbURn6tfZ9Mfv15zXiVD2cm2ItKbhRbyZysjgRLVbnZh8fNknPCyL2d33");

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
		IMEI(0);
}
	
	public void IMEI(int index) throws InterruptedException {
		
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		new WebDriverWait(driver, 40)
		
	.until(ExpectedConditions.elementToBeClickable(By.id("CheckIMEIRadio")));
		Thread.sleep(3000);
		
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'CheckIMEIRadio\']"));
		Thread.sleep(2000);
		if (index == 0) { //¿El cliente posee IMEI? --> SI
		opt.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\'NumberImei\']")).sendKeys("442267859858533");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id='RAValidationImei']")).click();
		Thread.sleep(2000);
		
		}
		
		else { //¿El cliente posee IMEI? --> NO
			opt.get(index).findElement(By.xpath("./..")).click(); //Selecciona la opción NO
			new WebDriverWait (driver, 20)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='slds-radio--faux']")));
			
			List<WebElement> lineas = driver.findElements(By.xpath("//span[@class='slds-radio--faux']")); //Hacemos List con todas las lineas que aparecen
			lineas.get(0).click(); //Seleccionamos linea
			Thread.sleep(2000);
			}
			
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("StepLockImei_nextBtn")));
		siguiente.click();
		Thread.sleep(1000);
	
		Confirmacion();
	}
	
	public void Confirmacion() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("radioConfirmation")));
		Thread.sleep(3000);
		
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'radioConfirmation\']"));
		Thread.sleep(2000);
		opt.get(0).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("Confirmation_nextBtn")));
		siguiente.click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement finalizar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"doneAction-241\"]/div/div/div[3]/div/button")));
		finalizar.click();
		

		} 
		
		
	}

	
		




