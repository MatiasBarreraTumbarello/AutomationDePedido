package com.automation.izzi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config {
	
	public WebDriver driver;
	public WebDriverWait wait;;
	public String staticAccessLink;
	public String accountId = "001c000002JvBrCAAV";
	public String orderId = "8013K000000EkOjQAK";
	public int tiempo = 2000;
	
	private boolean isAutoTest = false;
	
	public void initBrowser() throws IOException {
		String accessUrl = "";
		if (isAutoTest)
			accessUrl = getAutoTestUrl();
		else
			accessUrl = getStaticAccessLink();
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(accessUrl);
	}
	
	public String getStaticAccessLink() {
		staticAccessLink = "https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQNN7vw_H9HrLMalZm64NxW1cl5QbhwY3tRQpXSn8va2ch.a9buxtS9KanRsGQzo9BZB2FVcCL6JUw0CG7C7SIGfeBHs0";
		return staticAccessLink;
	}
	
	public void goToAccountLink() {
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/" + accountId + "/view");
	}
	
	public void goToOrderLink() {
		driver.get("\"https://test1dom--sittest.lightning.force.com/lightning/r/Order/" + orderId + "/view");
	}
	
	public String getAutoTestUrl() throws IOException {
		auth();
		retrieveUrl();
		String content = Files.readString(Paths.get("C:\\sfdx\\accessurl.txt"));
		String url = content.substring(content.indexOf(" http")+1);
		url.trim();
		System.out.println(url);
		return url;
	}
	
	public void retrieveUrl() throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "sfdx force:org:open -r -u SitTest > C:\\sfdx\\accessurl.txt");
        builder.redirectErrorStream(true);
        builder.start();
	}

	public void auth() throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "sfdx force:auth:sfdxurl:store -f C:\\sfdx\\sfdxurl.txt -u SitTest");
        builder.redirectErrorStream(true);
        builder.start();
	}

	/**
	 * Retrasa la ejecucion hasta que spinner sea invisible
	 */
	public void waitForInvisibleSpinner() {
		new WebDriverWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
	}
}
