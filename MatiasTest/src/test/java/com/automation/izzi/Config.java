package com.automation.izzi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config {
	
	public WebDriver driver;
	public WebDriverWait wait;;
	public String staticAccessLink;
	public String accountId = "001c000002JvBrCAAV";
	public String orderId = "8013K000000EkOjQAK";
	public String fileToWrite;
	public int tiempo = 2000;
	
	private boolean isAutoTest = false;
	
	@Test
	public void startExecution() throws IOException {
		String[] classesList = executionOrder();
		fileToWrite = fileToWrite();
		for (int i = 0; i < classesList.length; i++) {
			boolean waiting = true;
			ProcessBuilder builder = new ProcessBuilder(
					"cmd.exe", "/c", "mvn -Dtest=" + classesList[i] + " test");
			builder.redirectErrorStream(true);
	        builder.start();

			while (waiting) {
				continue;
			}

			//writeFile(fileToWrite, "mensaje" + i);
		}
		driver.quit();
	}
	
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
	
	public String[] executionOrder() {
		String[] classesList = {
				"ProcesoFVentas",
				"ProcesoAltaDeServicios",
				"ProcesoSuspenciones",
				"ProcesoPortabilidad",
				"ProcesoReactivacion",
				"ProcesoCambioDeServicio",
				"PorcesoCambioDeSim",
				"ProcesoCancelacionDeLinea",
				"ProcesoEntregarPedidos",
				"ProcesoGestionDeCasos",
				"ProcesoAltaDeServicios"
				};
		return classesList;
	}
	
	public String getStaticAccessLink() {
		staticAccessLink = "https://test1dom--sittest.my.salesforce.com/secur/frontdoor.jsp?sid=00D3K0000008jQa!ARwAQCgBPWINVHE4U9b1PbFZ7rlnUUdukDwm9eLC8_B_MvYDdpNdPK9t0gvaY5.hIhL95Ahgs6pEJ7VxEroAvqu.LWUdnb0h";
		return staticAccessLink;
	}
	
	public void goToAccountLink() {
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Account/" + accountId + "/view");
	}
	
	public void goToOrderLink() {
		driver.get("https://test1dom--sittest.lightning.force.com/lightning/r/Order/" + orderId + "/view");
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
        
        String absoluteFolderPath = "C:\\sfdx\\executions\\" + folderName;
        boolean dir = new File(absoluteFolderPath).mkdir();
        if(dir){
            System.out.println(absoluteFolderPath+" Dir Created");
        }else {
        	System.out.println("File "+absoluteFolderPath+" already exists");
        }

        String fileName = "execution_" + (new File(absoluteFolderPath).list().length + 1);
        String absoluteFilePath = absoluteFolderPath + "\\" + fileName + ".txt";
        File file = new File(absoluteFilePath);
        if (file.createNewFile()) {
    		System.out.println(absoluteFilePath+" File Created");
		}else {
        	System.out.println("File file.txt already exists in the project root directory");
        }
        
        return file.toString();
    }
	
	public void writeFile (String path, String str) {
	    try {
    		FileWriter writer = new FileWriter(path, true);
			writer.write(str + "\n");
			writer.close();
			System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
    		System.out.println("An error occurred.");
 			e.printStackTrace();
	    }
	}
	
	public String fileToWrite() throws IOException {
		String file = "";
		File dir = new File("C:\\sfdx\\executions\\" + LocalDate.now().toString());
		if (!dir.mkdir() || dir.list().length == 0) {
			file = createFile();
		} else if(dir.list().length > 0) {
			File lastFile = new File("C:\\sfdx\\executions\\" + LocalDate.now().toString() + "execution_" + dir.list().length);
			if (lastFile.length() > 0) {
				file = createFile();
			}else {
				file = lastFile.toString();
			}
		}
			
		return file;
	}

}
