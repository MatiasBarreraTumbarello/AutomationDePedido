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

public class ProcesoCambioDeServicio {

	private WebDriver driver;

	@Before
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(
				"https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQD2yaP0ytv0YuFtsIiCPaxcMnjQ2iuO2TD1vqjRelc0BQiZzaEdA0WgN8_WlOtuN8nt8kuZnNVU4DslEDCNp.xNZoIxF");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		Thread.sleep(20000);

	}

	@Test
	public void testScript() throws InterruptedException {

		cambioDeServicio(driver);
		seleccionDePlan(driver);

	}

	public void cambioDeServicio(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Cambio de Servicio"));
		executor.executeScript("arguments[0].click();", links.get(0));
		driver.switchTo().defaultContent();
	}

	public void seleccionDePlan(WebDriver driver) throws InterruptedException {
		// Paso 1 Seleccion del Nuevo plan-------------------------------------------------------------------
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));

		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		int size = frames.size();
		driver.switchTo().frame(size - 1);
		// frames.get(size-1).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'block_01tc0000007pvuhAAA\']")).click();
		// Paso 2 Click a siguiente--------------------------------------------------------------------------
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		WebElement siguiente = wait.until(ExpectedConditions.elementToBeClickable(By.id("Planes_nextBtn")));
		while (siguiente.isEnabled() && siguiente.isDisplayed()) {
		Thread.sleep(1000);
		siguiente.click();
		// Paso3 Click Finalizar-----------------------------------------------------------------------------
		WebDriverWait wait3 = new WebDriverWait(driver, 40);
		wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'doneAction-137\']/div/div/div[3]/div/button")).click();
		}
	}

}