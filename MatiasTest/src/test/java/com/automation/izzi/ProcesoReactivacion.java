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
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoReactivacion {
	
private WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQILXXLk7kXMN1rMnQcJFtEXP0AJTCgjKsXYZVhzPqr.9L2rd9P4DRmuE.suispsvJDYX6AxTWP6OGeO7kaZRLWk87VYK");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
	}
	
	@Test
	public void Reactivar () throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Reactivar"));
		executor.executeScript("arguments[0].click();", links.get(0));
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		Confirmar(1);
		Finalizar();
		
	}
	
	public void Confirmar(int index) throws InterruptedException {
		WebDriverWait  wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = new WebDriverWait(driver, 40)
			.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.elementToBeClickable(By.id("RadioButtonConfirmation")));
		List<WebElement> opt = driver.findElements(By.id("RadioButtonConfirmation"));
		Thread.sleep(1000);

		if (index == 1) {
			opt.get(index).findElement(By.xpath("./..")).click();
			Thread.sleep(1000);
		}
		
		driver.findElement(By.id("StepReactivationConfirmation_nextBtn")).click();
		Thread.sleep(2000);
		
	}

	
	public void Finalizar() throws InterruptedException {
		WebDriverWait  wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_brand ng-binding']")));
		button.click();
		Thread.sleep(1000);
	}
}
