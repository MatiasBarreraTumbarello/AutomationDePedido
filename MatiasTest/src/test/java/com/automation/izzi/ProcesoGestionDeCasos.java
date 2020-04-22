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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcesoGestionDeCasos {
	
private WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQBf1VsgJ9TcQuAhxbO8imePgS8j_99EKgjmLkFOB29wb0X87OXHdrSDf.MLX_vukgM72Gc1vmSdw7vRDUn5TsDbsZQKO");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
	}
	
	@Test
	public void Gestion () throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = driver.findElement(By.id("iFrameResizer1"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		WebElement boton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/section/div[6]/button"));
		executor.executeScript("arguments[0].click();", boton);
		
		driver.switchTo().defaultContent();
		
		CrearModificarCaso(1);
	}
	
	public void CrearModificarCaso(int index) throws InterruptedException {
		WebDriverWait  wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.elementToBeClickable(By.id("RadioOptions")));
		List<WebElement> opt = driver.findElements(By.id("RadioOptions"));
		Thread.sleep(1000);
		opt.get(index).findElement(By.xpath("./..")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("GestionCasos_nextBtn")).click();
		Thread.sleep(2000);
		if (index == 0)
			Crear();
		else
			Modificar();
	}
	
	public void Crear() throws InterruptedException {
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		SelectPicklist("Origen");
		SelectPicklist("Prioridad");
		SelectPicklist("Tipo");
		SelectPicklist("MotivoInfGeneral");
		
		driver.findElement(By.id("CrearCaso_nextBtn")).click();
		Thread.sleep(2000);
	}
	
	void Modificar() throws InterruptedException {
		
		new WebDriverWait (driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
		new WebDriverWait (driver, 40).until(ExpectedConditions.elementToBeClickable(By.id("CaseSelect")));
		
		List<WebElement> casos = driver.findElements(By.xpath("//span[@class = 'slds-radio_faux']"));
		casos.get(0).click();
		
		driver.findElement(By.xpath("//div[@id='Casos_nextBtn']")).click();
		Thread.sleep(2000);
	}

	void SelectPicklist(String id) throws InterruptedException {
		Select picklist = new Select(driver.findElement(By.id(id)));
		picklist.selectByIndex(1);
		Thread.sleep(1000);
	}
}
