package amazon.automation.elements;

import org.openqa.selenium.By;

public class Elements {
    public By barraPesquisa = By.id("twotabsearchtextbox"); 
    public By sugestoesAutocomplete = By.cssSelector(".s-suggestion"); 
    public By menuCategorias = By.xpath("//div[@id='nav-search-dropdown-card']/div[@class='nav-search-scope nav-sprite']");
    public By listaCategorias = By.cssSelector("#searchDropdownBox option[value='search-alias=computers']");
    public By listaProdutos = By.cssSelector(".s-main-slot .s-result-item"); 
    public By botaoBuscar = By.id("nav-search-submit-button"); 
    public By mensagemErro = By.cssSelector(".a-spacing-top-base .a-color-state");
}
