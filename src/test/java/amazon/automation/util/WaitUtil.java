package amazon.automation.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//adicionar espera 
public class WaitUtil {
	private WebDriver driver;
	private WebDriverWait wait;

	public WaitUtil(WebDriver driver, int timeoutInSeconds) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	}

	public WebElement esperarElementoVisivel(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public boolean esperarTituloConter(String tituloParcial) {
		return wait.until(ExpectedConditions.titleContains(tituloParcial));
	}

	// Método para aguardar elemento comm tempo personalizado
	public WebElement esperarElementoVisivelComTimeout(By locator, int timeoutInSeconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
