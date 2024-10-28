package amazon.automation.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Evidence {

	private WebDriver driver;

	public Evidence(WebDriver driver) {
		this.driver = driver;
	}

	// Método para capturar e salvar o screenshott
	public void capturarScreenshot(String nomeEvidencia) {
		// Define o caminho e cria a pasta "evidencias" para armazenar evidencias
		Path caminhoDiretorio = Paths.get("evidencias");
		if (Files.notExists(caminhoDiretorio)) {
			try {
				Files.createDirectory(caminhoDiretorio);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Format o nome do arquivo com timestamp
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String nomeArquivo = caminhoDiretorio + "/" + nomeEvidencia + "_" + timestamp + ".png";

		// Captura o print screenshot 
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Salva o arquivo no caminho definnido
		try {
			Files.copy(screenshot.toPath(), Paths.get(nomeArquivo));
			System.out.println("Evidência capturada: " + nomeArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
