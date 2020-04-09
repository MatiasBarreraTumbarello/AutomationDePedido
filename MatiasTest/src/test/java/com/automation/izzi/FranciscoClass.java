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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FranciscoClass {
	
	public static void portabilidad (WebDriver driver) throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button"));
		executor.executeScript("arguments[0].click();", boton);
		
		
		/*List<WebElement> cantIFrames = driver.findElements(By.tagName("iFrame"));
		int size = cantIFrames.size();
		driver.switchTo().frame(size - 1);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button")));
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button"));*/
		
		
			
		//Actions actions = new Actions(driver);
		//actions.moveToElement(boton).click().build().perform();
		
		driver.switchTo().defaultContent();
		
		
		
	}

}
