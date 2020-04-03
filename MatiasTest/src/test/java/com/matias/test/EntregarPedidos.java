package com.matias.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;

public class EntregarPedidos {
	
private WebDriver driver;
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQJcoVlbuLYJB_I_L3aPZLlxIxfHXBLF_i0Fg5dMDy273W2nLhqVkHmv.tqrNnIy4EWTxZ5SuA3hKmHdGF71tNcN7E5wA");
		Thread.sleep(20000);
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/8013K000000EBx3QAG/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		

}
	
	@Test
	public void testScript() throws InterruptedException {
		By iFrame1 = By.id("iFrameResizer0");
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(iFrame1));
		
		//driver.findElement(iFrame1).isDisplayed();
		
		driver.switchTo().frame("iFrameResizer0");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div/button")).click();
		
		
		driver.switchTo().defaultContent();

		Thread.sleep(5000);
		
}
	public static void accid (WebDriver driver){
		//8952140061736667340F
		driver.switchTo().frame("iFrameResizer1");//*[@id="iFrameResizer1"]
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		driver.findElement(By.xpath("//input[@id=\'ICCID\']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//input[@id='ICCIDVal']")).sendKeys("8952140061733523614F");
		driver.findElement(By.xpath("//div[@id='ValidarICCID']/p")).click();
		try {
			Thread.sleep(2000);
			
		//GUARDAR!!!! solo descomentar si se esta seguro que se quiere generar/guardar el Pedido.
		//	driver.findElement(By.xpath("//p[@class='ng-binding'  and contains(text(),'Guardar')]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
}
}
}