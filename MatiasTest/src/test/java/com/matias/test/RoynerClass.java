package com.matias.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoynerClass {
	public static void IrACuenta(WebDriver driver) throws InterruptedException {
		WebElement link = new WebDriverWait(driver, 40)
	    	.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("REGRESION NUEVAS PRUE")));
		link.click();
		Thread.sleep(2000);
	}
	
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
		//driver.switchTo().defaultContent();
		/*int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		for(int i=0; i<=size; i++){
			//new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(i));
			int total = driver.findElements(By.xpath("//div[@id='block_0']")).size();
			if(total > 0) {
				WebElement plan = driver.findElement(By.xpath("//div[@id='block_0']"));
				plan.findElement(By.xpath("./..")).click();
			}else {
				driver.switchTo().defaultContent();
			}
		}*/
		new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iFrameResizer4")));
		WebElement plan = driver.findElement(By.xpath("//div[contains(@id,'block_0')]"));
		plan.findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'ChooseAndAddProducts_nextBtn')]")).click();
		Thread.sleep(2000);
	}
}
