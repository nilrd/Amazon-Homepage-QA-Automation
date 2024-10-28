package amazon.automation.util;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieManager {

	// Método para adicionar cookies no WebDriver
	public void adicionarCookies(WebDriver driver) {
		// Acessa o domínio correto antes de adicionar os coookies
		driver.get("https://www.amazon.com.br");

		// Verifica se os cookies já estão presentes  atualizados
		Set<Cookie> cookiesExistentes = driver.manage().getCookies();

		if (!cookiesJaPresentes(cookiesExistentes)) {
			// Remove cookies antigos 
			removerCookiesAntigos(driver);

			// Adiciona os cookies manualmente após carregar a página
			Cookie sessionId = new Cookie.Builder("session-id", "138-6118058-3733264").domain("www.amazon.com.br")
					.path("/").isHttpOnly(true).isSecure(true).build();

			Cookie sessionIdTime = new Cookie.Builder("session-id-time", "2082758401").domain("www.amazon.com.br")
					.path("/").isHttpOnly(true).isSecure(true).build();

			Cookie ubidAcbbr = new Cookie.Builder("ubid-acbbr", "130-5598026-9455229").domain("www.amazon.com.br")
					.path("/").isHttpOnly(true).isSecure(true).build();

			Cookie xAcbbr = new Cookie.Builder("x-acbbr", "zJVeLfgQbMedS9U:d6JXtCJaF09ENR2gLy5F6l@l8TOjcG")
					.domain("www.amazon.com.br").path("/").isHttpOnly(true).isSecure(true).build();

			// Adiciona os cookies no WebDriver
			driver.manage().addCookie(sessionId);
			driver.manage().addCookie(sessionIdTime);
			driver.manage().addCookie(ubidAcbbr);
			driver.manage().addCookie(xAcbbr);

			// Atualiza a pgina para que os cookies sejam reconhecidos
			driver.navigate().refresh();
			System.out.println("Cookies adicionados e página atualizada.");
		} else {
			System.out.println("Cookies já presentes e válidos, não é necessário adicionar novamente.");
		}
	}

	// Método para verificar se os cookies estão presentes 
	private boolean cookiesJaPresentes(Set<Cookie> cookies) {
		return cookies.stream().anyMatch(
				cookie -> cookie.getName().equals("session-id") && cookie.getValue().equals("138-6118058-3733264"));
	}

	// Método para remover cookies antigos
	private void removerCookiesAntigos(WebDriver driver) {
		driver.manage().deleteCookieNamed("session-id");
		driver.manage().deleteCookieNamed("session-id-time");
		driver.manage().deleteCookieNamed("ubid-acbbr");
		driver.manage().deleteCookieNamed("x-acbbr");
		System.out.println("Cookies antigos removidos.");
	}
}
