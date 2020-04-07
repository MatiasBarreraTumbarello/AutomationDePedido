package com.matias.test;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
		List<WebElement> cantIFrames = driver.findElements(By.tagName("iFrame"));
		int size = cantIFrames.size();
		driver.switchTo().frame(size - 1);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button")));
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]/button"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(boton).click().build().perform();
		
		//driver.findElement(By.xpath("//button[contains(@class,'neutral')][3])")). click();
		
		/*WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement iFrame = driver.findElement(By.id("iFrameResizer1"));
		//int tamanio = iFrame.size();
		//System.out.println(tamanio);
		
		driver.switchTo().frame(iFrame);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]")));
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[3]"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(boton).click().build().perform();*/
//		Actions action = new Actions (driver);
//		action.moveToElement(iFrame.get(1)).
		
		
	}

}
