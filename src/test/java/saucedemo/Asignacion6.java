package saucedemo;

import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import Utilities.DatosExcel;



public class Asignacion6 {
	 	WebDriver driver;
		By btnUser = By.id("user-name");
		By btnPass = By.id("password");
		
		

		
		By userName = By.id("first-name");
		By userLastName = By.id("last-name");
		By postalCode = By.id("postal-code");
		By btnContinue = By.id("continue");
		By btnConfirmar = By.id("finish");
		
		// Evidencia
		String rutaEvidencia = "..\\saucedemo\\Evidencias\\";
		String nombreDocumento = "Evidencia SauceDemo.docx";
	
@BeforeSuite  public void beforeSuite() throws Exception  {
		  System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		  this.driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://www.saucedemo.com/"); 
		 
		  // Captura Evidencia
		  CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento, "Documento Evidencias SaudeDemo", 18);

		  }
	  
@Test(dataProvider="Datos Login")
	  public void login(String user, String password) throws InvalidFormatException, IOException, InterruptedException {

			
			 WebElement campoUser = driver.findElement(btnUser);
			  campoUser.clear();
			  campoUser.sendKeys(user);
			  WebElement campoPass = driver.findElement(btnPass);
			  campoPass.clear();
			  campoPass.sendKeys(password);
			  
			 driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			 
			 
			 driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
			 driver.findElement(By.className("shopping_cart_badge")).click();
			 driver.findElement(By.id("checkout")).click();
			
			 			 
			
			
		}
			
		
	

		 
		 
	  



@DataProvider(name="Datos Login")
public Object [][] leerDatosLogin() throws Exception { 
	return DatosExcel.leerExcel("..\\sauceDemo\\Datos\\DatosLogin.xlsx", "Hoja1");	
		
}






 @AfterSuite
  public void cerrarNavegador() {
	// driver.close();
  }

}
