package com.automation.izzi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainClass {
	
	public WebDriver driver;
	public WebDriverWait wait;;
	public String staticAccessLink;
	public String accountId = "001c000002JvBrCAAV";
	public String staticOrderId = "8013K000000ElPJQA0";
	public String fileToWrite;
	public String runningClass;
	
	public boolean isRunning;
	
	public int tiempo = 2000;
	
	private boolean isAutoTest = false;
	
	@Test
	public void startExecution() throws IOException, ClassNotFoundException {
		String[] classesList = executionOrder();
		for (int i = 0; i < classesList.length; i++) {
			runningClass = classesList[i];
			Class<?> cls = (Class<?>) Class.forName("com.automation.izzi." + runningClass);
			
			System.out.println("Started: " + runningClass);
	        System.out.println("...");
			
	        Result result = JUnitCore.runClasses(cls);

			while (result == null) {
				continue;
			}
			
			System.out.println("Finished: " + runningClass);
		}

		fileToWrite = executionFile();
		saveResponse(fileToWrite, "Test Completed!");
		//driver.quit();
	}
	
	public void initBrowser() throws IOException {
		String accessUrl = "";
		if (isAutoTest)
			accessUrl = getAutoTestUrl();
		else
			accessUrl = getStaticAccessLink();
		driver.manage().window().maximize();
		driver.get(accessUrl);
	}
	
	public WebDriver setDriver() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public String[] executionOrder() {
		String[] classesList = {
				"ProcesoFVentas",
				"ProcesoEntregarPedidos",
				"ProcesoCambioDeSim",
				"ProcesoBloqueoDeIMEI",
				"ProcesoCancelacionLinea",
				"ProcesoSuspenciones",
				"ProcesoPortabilidad",
				"ProcesoReactivacion",
				"ProcesoCambioDeServicio",
				"ProcesoGestionDeCasos",
				"ProcesoAltaDeServicios"
				};
		return classesList;
	}
	
	public String getStaticAccessLink() {
		staticAccessLink = "https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQOD2pTorps1alD0aQIWuu2H0__glgaBgZlHjliY89jcUjzAxGYZ25wryIdfIxp6.VY1l9w5Scs7JDbMkn2Vn7gdEH2GD";
		return staticAccessLink;
	}
	
	public void goToAccountLink() {
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/" + accountId + "/view");
	}
	
	public void goToOrderLink() {
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/" + getOrderId() + "/view");
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
	
	public String createFile() throws IOException {
        String date = LocalDate.now().toString();
        String folderName = date;
        
        new File("executions").mkdir();
        
        String absoluteFolderPath = "executions/" + folderName;
        boolean dir = new File(absoluteFolderPath).mkdir();
        if(dir){
            System.out.println(absoluteFolderPath+" Dir Created");
        }else {
        	System.out.println("Directory "+absoluteFolderPath+" already exists");
        }

        String fileName = "execution_" + (new File(absoluteFolderPath).list().length + 1);
        String absoluteFilePath = absoluteFolderPath + "/" + fileName + ".txt";
        File file = new File(absoluteFilePath);
        if (file.createNewFile()) {
    		System.out.println("File " + absoluteFilePath + " Created");
		}else {
        	System.out.println("File already exists in the project root directory");
        }
        
        return file.toString();
    }
	
	public String executionFile() throws IOException {
		String file = "";
		File dir = new File("executions/" + LocalDate.now().toString());
		if (!dir.exists() || dir.list().length == 0) {
			file = createFile();
		} else if(dir.list().length > 0) {
			File lastFile = new File("executions/" + LocalDate.now().toString() + "/execution_" + dir.list().length + ".txt");
			if (lastFile.length() > 0) {
				Scanner scanner = new Scanner(lastFile);

			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        if(line.contains("Test Completed!")) { 
		        		file = createFile();
		        		break;
			        }else {
			        	file = lastFile.toString();
			        }
			    }
			    scanner.close();
			}else {
				file = lastFile.toString();
			}
		}
		System.out.println("File to write: " + file);
			
		return file;
	}
	
	public void saveResponse (String path, String str) throws IOException{
		FileWriter writer = new FileWriter(path, true);
		writer.write(str + "\n");
		writer.close();
		System.out.println("Successfully wrote to the file.");
	}
	
	public void returnExecutionError(String rc, Exception error) {
		try {
			fileToWrite = executionFile();
			saveResponse(fileToWrite, rc + ":\n" + error + "\n" + "-".repeat(30));
		driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void returnExecutionSuccess(String rc) {
		try {
			fileToWrite = executionFile();
			saveResponse(fileToWrite, rc + ":\nSUCCESS\n" + "-".repeat(30));
		driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void storeCreatedOrder(String order) {
		try {
			new File("executions").mkdir();
			new File("executions/order_number.txt");
			FileWriter writer = new FileWriter("executions/order_number.txt");
			writer.write(order);
			writer.close();
			//driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getOrderId() {
		String orderId = "";
		File file = new File("executions/order_number.txt");
		if (file.exists() && file.length() > 0) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						"executions/order_number.txt"));
				String line = reader.readLine();
				while (line != null) {
					orderId = line;
					break;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			orderId = staticOrderId;
		}

		return orderId;
	}
}