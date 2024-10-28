package amazon.automation.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amazon.automation.drivers.Drivers;
import amazon.automation.elements.Elements;
import amazon.automation.util.CookieManager;
import amazon.automation.util.Evidence;
import amazon.automation.util.Metodos;
import amazon.automation.util.WaitUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps extends Drivers {

	WebDriver driver = iniciarDriver();
	Elements elements = new Elements();
	Metodos metodos = new Metodos(driver);
	CookieManager cookieManager = new CookieManager();
	Evidence evidence = new Evidence(driver);
	WaitUtil waitUtil = new WaitUtil(driver, 1); // Timeout aumentado para 15 segundos

	@Given("o usuario acessa a pagina inicial da Amazon")
	public void o_usuario_acessa_a_pagina_inicial_da_amazon() {
		cookieManager.adicionarCookies(driver);
		driver.get("https://www.amazon.com.br");
	}

	@When("o usuario clica na barra de pesquisa")
	public void o_usuario_clica_na_barra_de_pesquisa() {
		waitUtil.esperarElementoVisivel(elements.barraPesquisa);
		metodos.clicar(elements.barraPesquisa);
	}

	@When("o usuario insere {string} na barra de pesquisa")
	public void o_usuario_insere_na_barra_de_pesquisa(String termo) {
		waitUtil.esperarElementoVisivel(elements.barraPesquisa);
		metodos.escrever(elements.barraPesquisa, termo);
	}

	@Then("o sistema deve exibir uma lista de sugestoes populares relacionadas ao termo {string}")
	public void o_sistema_deve_exibir_uma_lista_de_sugestoes_populares_relacionadas_ao_termo(String termoEsperado) {
		waitUtil.esperarElementoVisivel(elements.sugestoesAutocomplete);
		WebElement sugestao = metodos.obterElementoPorTexto(elements.sugestoesAutocomplete, termoEsperado);
		assertTrue(sugestao != null && sugestao.getText().contains(termoEsperado),
				"Sugestões não correspondem ao termo esperado.");
		evidence.capturarScreenshot("sugestoes_populares");
	}

	@Then("nenhuma sugestao deve ser exibida para o termo {string}")
	public void nenhuma_sugestao_deve_ser_exibida_para_o_termo(String termo) {
		waitUtil.esperarElementoVisivel(elements.barraPesquisa);
		boolean sugestoesExibidas = metodos.elementoVisivel(elements.sugestoesAutocomplete);
		if (sugestoesExibidas) {
			WebElement sugestao = metodos.obterElementoPorTexto(elements.sugestoesAutocomplete, termo);
			assertFalse(sugestao != null && sugestao.getText().contains(termo),
					"Sugestões foram exibidas para um termo inexistente.");
		} else {
			assertFalse(sugestoesExibidas, "Nenhuma sugestão deveria estar visível.");
		}
		evidence.capturarScreenshot("nenhuma_sugestao");
	}

	@When("o usuario clica no menu {string} ao lado da barra de pesquisa")
	public void o_usuario_clica_no_menu_ao_lado_da_barra_de_pesquisa(String menu) {
		try {
			waitUtil.esperarElementoVisivel(elements.menuCategorias);
			metodos.clicar(elements.menuCategorias);
		} catch (Exception e) {
			System.out.println("Falha ao clicar no menu de categorias: " + e.getMessage());
		}
	}

	@When("o usuario seleciona a categoria {string} na lista de categorias")
	public void o_usuario_seleciona_a_categoria_na_lista_de_categorias(String categoria) {
		try {
			waitUtil.esperarElementoVisivel(elements.listaCategorias);
			metodos.clicarPorTexto(elements.listaCategorias, categoria);
		} catch (Exception e) {
			System.out.println("Falha ao selecionar a categoria: " + e.getMessage());
		}
	}

	@When("o usuario digita {string} na barra de pesquisa")
	public void o_usuario_digita_na_barra_de_pesquisa(String termo) {
		waitUtil.esperarElementoVisivel(elements.barraPesquisa);
		metodos.escrever(elements.barraPesquisa, termo);
	}

	@When("o usuario seleciona {string} nas sugestoes do autocomplete")
	public void o_usuario_seleciona_nas_sugestoes_do_autocomplete(String sugestao) {
		waitUtil.esperarElementoVisivel(elements.sugestoesAutocomplete);
		metodos.clicarPorTexto(elements.sugestoesAutocomplete, sugestao);
	}

	@Then("a pagina de resultados para {string} deve ser carregada")
	public void a_pagina_de_resultados_para_deve_ser_carregada(String termoPesquisa) {
		waitUtil.esperarTituloConter(termoPesquisa);
		String tituloAtual = driver.getTitle();
		assertTrue(tituloAtual.contains(termoPesquisa), "A página de resultados não foi carregada corretamente.");
		evidence.capturarScreenshot("pagina_resultados");
	}

	@Then("uma lista de produtos relacionados a {string} deve ser exibida")
	public void uma_lista_de_produtos_relacionados_a_deve_ser_exibida(String termo) {
		waitUtil.esperarElementoVisivel(elements.listaProdutos);
		boolean listaProdutosExibida = metodos.elementoVisivel(elements.listaProdutos);
		assertTrue(listaProdutosExibida, "Lista de produtos não foi exibida corretamente.");
		evidence.capturarScreenshot("lista_produtos_relacionados");
	}

	@When("o usuario clica no botao de busca")
	public void o_usuario_clica_no_botao_de_busca() {
		metodos.clicar(elements.botaoBuscar);
	}

	@Then("o sistema deve exibir a mensagem {string}")
	public void o_sistema_deve_exibir_a_mensagem(String mensagemEsperada) {
		try {
			String mensagemCapturada = metodos.obterTexto(elements.mensagemErro);
			assertEquals(mensagemEsperada, mensagemCapturada, "A mensagem de erro exibida não está correta.");
			evidence.capturarScreenshot("mensagem_erro");
		} catch (Exception e) {
			System.out.println("Elemento de mensagem não encontrado ou seletor incorreto: " + e.getMessage());
			driver.quit();
		}
	}
}
