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

public class ProcesoSuspenciones {
	private WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQBVfkMth.4rmDYSVQh8fTRP2DFn.P_dufeTxawAJEt93vuuJSeyfluWd5G51mNdu2sD1au99YrXo3Pr.TSoLM5KuxH1m");

		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/001c000002JvBrCAAV/view");

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Thread.sleep(20000);
		
	}
	
	
	@Test
	public void testScript() throws InterruptedException {
		clickSuspension();
		confirmarSuspension();
		motivoDeSuspension();
	}
	
	public void clickSuspension() throws InterruptedException {
		Thread.sleep(2000);
		WebElement frame = driver.findElement(By.id("iFrameResizer2"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.zIndex = '999999';", frame);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.linkText("Suspensiones"));
		executor.executeScript("arguments[0].click();", links.get(0));
		//links.get(0).click();
		Thread.sleep(2000);
	}
	
	public void confirmarSuspension() throws InterruptedException {
		driver.switchTo().defaultContent();
		WebElement frame = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.id("iFrameResizer3")));
		frame.click();
		driver.switchTo().frame(frame);
		new WebDriverWait(driver, 40)
        	.until(ExpectedConditions.elementToBeClickable(By.id("confirmSuspension")));
		List<WebElement> opt = driver.findElements(By.xpath("//*[@id=\'confirmSuspension\']"));
		Thread.sleep(2000);
		opt.get(0).findElement(By.xpath("./..")).click();
		Thread.sleep(2000);
	}
	
	public void motivoDeSuspension() throws InterruptedException {
		Select picklist = new Select(driver.findElement(By.id("suspendMotive")));
		picklist.selectByIndex(1);
		Thread.sleep(1000);
		//driver.quit();

		//wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmSuspensionStep_nextBtn")));
		driver.findElement(By.xpath("//*[@id=\'confirmSuspensionStep_nextBtn\']/p")).click();
		Thread.sleep(2000);
	}
}