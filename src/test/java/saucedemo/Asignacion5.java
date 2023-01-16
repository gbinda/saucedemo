package saucedemo;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeSuite;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class Asignacion5 {
	
	 WebDriver driver;
	By btnUser = By.id("user-name");
	By btnPass = By.id("password");
	By btnLogin = By.cssSelector("submit-button btn_action");
	
	By btnProducto = By.name("add-to-cart-sauce-labs-backpack");
	By btnCarrito = By.className("shopping_cart_badge");
	By btnCheckout = By.id("checkout");
	
	By userName = By.id("first-name");
	By userLastName = By.id("last-name");
	By postalCode = By.id("postal-code");
	By btnContinue = By.id("continue");
	By btnConfirmar = By.id("finish");
	
	
    String rutaEvidencia = "..\\saucedemo\\Evidencias\\";
	
	
	
	

@BeforeSuite
  public void beforeSuite() throws Exception  {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
	  this.driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://www.saucedemo.com/");
	  
	//Evidencia
		File pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "01_navegador_open.jpg"));
		 
	  }

	 
  @Test
  public void iniciarSesion() throws Exception {
	 
	  WebElement campoUser = driver.findElement(btnUser);
	  campoUser.clear();
	  campoUser.sendKeys("standard_user");
	  WebElement campoPass = driver.findElement(btnPass);
	  campoPass.clear();
	  campoPass.sendKeys("secret_sauce");
	  
	 driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	 
	 File pantalla2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 FileUtils.copyFile(pantalla2, new File(rutaEvidencia + "02_inicio_sesion.jpg"));
	 
	 String urlEsperada = "https://www.saucedemo.com/inventory.html";
	 String urlActual = driver.getCurrentUrl();
	  
	Assert.assertEquals(urlActual, urlEsperada);
	
	
  }
 
  @Test
	public void seleccionarProducto() throws Exception  {
	
	  driver.findElement(btnProducto).click();
	  driver.findElement(btnCarrito).click();
		  
		File pantalla3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla3, new File(rutaEvidencia + "03_producto_agregado.jpg"));
			
	  driver.findElement(btnCheckout).click();
	  	
	  
	  driver.findElement(userName).sendKeys("Gisela");
	  driver.findElement(userLastName).sendKeys("Binda");
	  driver.findElement(postalCode).sendKeys("1218");
	  
	  File pantalla4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(pantalla4, new File(rutaEvidencia + "04_info_cliente.jpg"));
	  
	  driver.findElement(btnContinue).click();
	  
	  File pantalla5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(pantalla5, new File(rutaEvidencia + "05_checkout.jpg"));
	  
	  driver.findElement(btnConfirmar).click();
	  
	  
	   
	  
  }
  

  @AfterSuite
  public void afterSuite() {
	  driver.close();
  }

}
