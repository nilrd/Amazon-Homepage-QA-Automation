package amazon.automation.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Drivers {
	protected static WebDriver driver;

	public static WebDriver iniciarDriver() {
		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		}
		return driver;
	}

	public static void finalizarDriver() {
		driver.quit();
		driver = null;
	}
}
