package amazon.automation.util; 

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Metodos {
	private WebDriver driver;
	private WaitUtil waitUtil;

	/**
	 * Construtor da classe Metodos para inicializar o WebDriver e a classe WaitUtil
	 * @param driver Instância do WebDriver utilizada nos métodos
	 * @category Utilitário de métodos de interação com elementos da página
	 * @autor Nilson da Silva Brites
	 */
	public Metodos(WebDriver driver) {
		this.driver = driver;
		this.waitUtil = new WaitUtil(driver, 10); // 10 segundos de espera padrão
	}

	/**
	 * Obter um elemento da página que contenha um texto específico.
	 * @param seletor Seletor do elemento
	 * @param textoEsperado Texto que o elemento deve conter
	 * @return WebElement correspondente ou null se não for encontrado
	 * @category Localização de elemento
	 * @autor Nilson da Silva Brites
	 */
	public WebElement obterElementoPorTexto(By seletor, String textoEsperado) {
		List<WebElement> elementos = driver.findElements(seletor);
		for (WebElement elemento : elementos) {
			if (elemento.getText().contains(textoEsperado)) {
				return elemento;
			}
		}
		return null; // Retorna null caso nenhum elemento corresponda ao texto esperado
	}

	/**
	 * Clicar em um elemento visível da página.
	 * @param elemento Seletor do elemento a ser clicado
	 * @category Ação de clique
	 * @autor Nilson da Silva Brites
	 */
	public void clicar(By elemento) {
		WebElement elementoVisivel = waitUtil.esperarElementoVisivel(elemento);
		elementoVisivel.click();
	}

	/**
	 * Inserir texto em um campo da página.
	 * @param elemento Seletor do campo de texto
	 * @param texto Texto a ser inserido
	 * @category Ação de escrita
	 * @autor Nilson da Silva Brites
	 */
	public void escrever(By elemento, String texto) {
		WebElement elementoVisivel = waitUtil.esperarElementoVisivel(elemento);
		elementoVisivel.sendKeys(texto);
	}

	/**
	 * Obter o texto de um elemento da página.
	 * @param elemento Seletor do elemento
	 * @return Texto contido no elemento
	 * @category Extração de texto
	 * @autor Nilson da Silva Brites
	 */
	public String obterTexto(By elemento) {
		WebElement elementoVisivel = waitUtil.esperarElementoVisivel(elemento);
		return elementoVisivel.getText();
	}

	/**
	 * Verificar se um elemento está visível na página.
	 * @param elemento Seletor do elemento a verificar
	 * @return true se o elemento está visível, false caso contrário
	 * @category Verificação de visibilidade
	 * @autor Nilson da Silva Brites
	 */
	public boolean elementoVisivel(By elemento) {
		return driver.findElements(elemento).size() > 0;
	}

	/**
	 * Clicar em um elemento que contenha um texto específico.
	 * @param elemento Seletor do grupo de elementos
	 * @param texto Texto que o elemento a ser clicado deve conter
	 * @category Ação de clique por texto
	 * @autor Nilson da Silva Brites
	 */
	public void clicarPorTexto(By elemento, String texto) {
		for (WebElement item : driver.findElements(elemento)) {
			if (item.getText().equalsIgnoreCase(texto)) {
				item.click();
				break;
			}
		}
	}
}
