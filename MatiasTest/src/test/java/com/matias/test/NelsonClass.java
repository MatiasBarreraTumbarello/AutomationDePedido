package com.matias.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class NelsonClass {
	

	public static void clickEntregaDePedido (WebDriver driver) throws InterruptedException{
		By iFrame1 = By.id("iFrameResizer0");
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		
		//driver.findElement(iFrame1).isDisplayed();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		
		
		driver.switchTo().defaultContent();

		Thread.sleep(5000);

	}
	
	public static void accid (WebDriver driver) {

	

	}
	
}
