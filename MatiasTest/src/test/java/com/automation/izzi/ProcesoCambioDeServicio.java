package com.automation.izzi;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
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

public class ProcesoCambioDeServicio {
	
	private MainClass main = new MainClass();
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setUp() throws InterruptedException, IOException {
		
		driver = main.setDriver();
		main.initBrowser();
		main.goToAccountLink();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		Thread.sleep(20000);

	}

	@Test
	public void testScript() throws InterruptedException {
		try {
			cambioDeServicio(driver);
			seleccionDePlan(driver);
			main.returnExecutionSuccess(getClass().getName());
		} catch (Exception e) {
			main.returnExecutionError(getClass().getName(), e);
		}
	}

	public void cambioDeServicio(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Cambio de Servicio"));
		executor.executeScript("arguments[0].click();", links.get(1));
		driver.switchTo().defaultContent();
	}

	public void seleccionDePlan(WebDriver driver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement frame = wait.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		driver.switchTo().frame(frame);
		// frames.get(size-1).click();
		Thread.sleep(2000);
		
		if(seleccion(driver)) {
			WebElement plan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_01tc0000007pvuiAAA']")));
			plan.click();
		}else {
			WebElement plan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_01tc0000007pvuhAAA']")));
			plan.click();
		}
		
		
		
		// siguiente
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("Planes_nextBtn")));
		siguiente.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("Confirmacion_nextBtn")));
		siguiente2.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement finalizar = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='doneAction-217']/div/div/div[3]/div/button")));
		finalizar.click();

	}
	public boolean seleccion (WebDriver driver) throws InterruptedException{
		try {
			WebElement opt = new WebDriverWait(driver,40).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_01tc0000007pvuiAAA']")));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}