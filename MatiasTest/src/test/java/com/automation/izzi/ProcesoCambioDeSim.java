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

public class ProcesoCambioDeSim {
	
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
	public void testScript() throws InterruptedException {
	
		cambioDeSim(driver);
		selecionSim(driver);
		
	}
	public void cambioDeSim (WebDriver driver) throws InterruptedException{
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Cambio de SIM"));
		executor.executeScript("arguments[0].click();", links.get(0));
		driver.switchTo().defaultContent();
	}
		
	public void selecionSim (WebDriver driver) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));


		WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		iframe.click();
		driver.switchTo().frame(iframe);

		Thread.sleep(2000);

		WebElement opcion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ChooseSim\"]/div/ng-include/div/div[2]/ul/li")));
		opcion.click();
		driver.findElement(By.xpath("//*[@id=\'Step1_nextBtn\']")).click();
		//Paso3
		WebDriverWait wait2 = new WebDriverWait (driver, 40);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		driver.findElement(By.xpath("//*[@id=\'newIMSI\']")).sendKeys("123456789101113");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id=\'newICCID\']")).sendKeys("12345678910111213143");
		driver.findElement(By.xpath("//*[@id=\'Step2_nextBtn\']")).click();
		//Paso4
		WebDriverWait wait3 = new WebDriverWait (driver, 40);
		wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'doneAction-137\']/div/div/div[3]/div/button")).click();
		
	}
	

}