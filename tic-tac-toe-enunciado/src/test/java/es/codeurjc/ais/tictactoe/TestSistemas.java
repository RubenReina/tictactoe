package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestSistemas {

	 protected WebDriver driver1;
     protected WebDriver driver2;
	
	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
		WebApp.start();	
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}

	@Before
	public void setupTest() throws InterruptedException {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		String jugador1 = "mario";
		String jugador2 = "ruben";

		driver1.findElement(By.id("nickname")).sendKeys(jugador1);
		driver2.findElement(By.id("nickname")).sendKeys(jugador2);
		driver1.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();
		
	}
	
	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		if (driver2 != null){
			driver2.quit();
		}
	}
	
	@Test
	public void test1Gana() throws InterruptedException {
		/*	X|O|O
		 * 	X| |
		 * 	X| |
		 */
		String resultado = "mario wins! ruben looses.";

		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-6")).click();
		Thread.sleep(2000);
		String alerta = driver1.switchTo().alert().getText();
		String alerta2 = driver2.switchTo().alert().getText();
		assertTrue(alerta.equals(resultado));
		assertTrue(alerta2.equals(resultado));
	}
	
	@Test
	public void test1Pierde() throws InterruptedException{
		/*	X| |O
		 * 	 |X|O
		 * 	X| |O
		 */
		String resultado = "ruben wins! mario looses.";
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-6")).click();
		driver2.findElement(By.id("cell-8")).click();
		Thread.sleep(2000);
		String alerta = driver1.switchTo().alert().getText();
		String alerta2 = driver2.switchTo().alert().getText();
		assertTrue(alerta.equals(resultado));
		assertTrue(alerta2.equals(resultado));
	}
	
	@Test
	public void testEmpate() throws InterruptedException{
		/*	X|O|X
		 * 	X|O|O
		 * 	O|X|X
		 */
		String resultado = "Draw!";
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-8")).click();
		Thread.sleep(2000);
		String alerta = driver1.switchTo().alert().getText();
		String alerta2 = driver2.switchTo().alert().getText();
		assertTrue(alerta.equals(resultado));
		assertTrue(alerta2.equals(resultado));
		
	}

}
