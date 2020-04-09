package com.automation.izzi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoynerClass {
/*	public static void IrACuenta(WebDriver driver) throws InterruptedException {
		WebElement link = new WebDriverWait(driver, 40)
	    	.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("REGRESION NUEVAS PRUE")));
		link.click();
		Thread.sleep(2000);
	}*/
	
	public static void AltaDeServicios(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer3"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Alta de Servicios"));
		executor.executeScript("arguments[0].click();", links.get(0));
		//links.get(0).click();
		Thread.sleep(2000);
	}

	public static void SeleccionDePlan(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		WebElement frame = driver.findElement(By.id("iFrameResizer4"));
		driver.switchTo().frame(frame);
		WebElement plan = new WebDriverWait(driver, 40)
		    	.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_0']")));
		plan.findElement(By.xpath("./..")).click();
		new WebDriverWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		driver.findElement(By.xpath("//div[@id='ChooseAndAddProducts_nextBtn']")).click();
		Thread.sleep(2000);
	}
	
	public static void NumeroAPortar(WebDriver driver) throws InterruptedException {
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		Select picklist = new Select(driver.findElement(By.id("SelectCompany")));
		picklist.selectByIndex(2);
		Thread.sleep(1000);
		driver.findElement(By.id("PortabilityNumber")).sendKeys("1112131416");
		Thread.sleep(1000);
		driver.findElement(By.id("IPValidateMSISDN")).click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		Thread.sleep(1000);
		driver.findElement(By.id("StepPortabilityNumber_nextBtn")).click();
		Thread.sleep(5000);
		
	}
}