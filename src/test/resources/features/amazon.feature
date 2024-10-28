# Author: nilson.brites@gmail.com

Feature: Funcionalidades principais do site da Amazon
  Como usuario do site da Amazon
  Quero pesquisar produtos e navegar pelos menus
  Para encontrar produtos e realizar compras de forma eficiente

  Background:
    Given o usuario acessa a pagina inicial da Amazon

  Scenario: Verificar sugestoes do autocomplete ao digitar tres caracteres
    When o usuario clica na barra de pesquisa
    And o usuario insere "liv" na barra de pesquisa
    Then o sistema deve exibir uma lista de sugestoes populares relacionadas ao termo "liv"

  Scenario: Verificar que nenhuma sugestao e exibida para uma palavra inexistente
    When o usuario clica na barra de pesquisa
    And o usuario insere "zzzzzzx" na barra de pesquisa
    Then nenhuma sugestao deve ser exibida para o termo "zzzzzzx"

  Scenario: Testar funcionamento do menu de categorias ao lado da barra de pesquisa
    When o usuario clica no menu "Todos" ao lado da barra de pesquisa
    And o usuario seleciona a categoria "Computadores e Informatica" na lista de categorias
    And o usuario digita "com" na barra de pesquisa
    And o usuario seleciona "computador completo" nas sugestoes do autocomplete
    Then a pagina de resultados para "computador completo" deve ser carregada
    And uma lista de produtos relacionados a "computador completo" deve ser exibida

  Scenario: Verificar comportamento ao buscar por um termo inexistente
    When o usuario insere "xyzabc1234567" na barra de pesquisa
    And o usuario clica no botao de busca
    Then o sistema deve exibir a mensagem "Nenhum resultado para xyzabc1234567."


