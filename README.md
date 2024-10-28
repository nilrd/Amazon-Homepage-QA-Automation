# Projeto de Automação - Testes Funcionalidades Amazon

Este repositório contém a automação dos testes das funcionalidades principais da página inicial do site da Amazon. O objetivo deste projeto é garantir a qualidade das funcionalidades de sugestão de pesquisa (autocomplete), navegação no menu de categorias e o desempenho de carregamento da página inicial. A automação foi desenvolvida em **Java** utilizando o **Selenium WebDriver** e **Cucumber** para criar cenários de teste robustos e reutilizáveis.

## Estrutura do Projeto

A estrutura do projeto é organizada conforme as melhores práticas de automação, visando a fácil manutenção e escalabilidade dos testes:

```
amazon.automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── amazon.automation/
│   │           ├── drivers/           # Contém a classe `Drivers.java` para gerenciamento de drivers do Selenium.
│   │           ├── elements/          # Contém a classe `Elements.java` para armazenar os localizadores de elementos.
│   │           └── steps/             # Contém a classe `Steps.java` para implementar as ações dos passos dos cenários.
│   │           └── util/
│   │               ├── CookieManager.java # Gerencia cookies, usada para contornar captchas que bloqueiam os testes.
│   │               ├── Evidence.java      # Captura evidências de screenshots dos resultados dos testes.
│   │               └── WaitUtil.java      # Implementa métodos de espera explícita, melhorando a estabilidade dos testes.
│   └── test/
│       └── resources/
│           ├── features/            # Arquivo `.feature` contendo os cenários de teste escritos em Gherkin.
│           └── evidencias/          # Pasta com imagens de evidências geradas durante a execução dos testes.
├── pom.xml                           # Arquivo de configuração do Maven, contendo as dependências do projeto.
```

## Funcionalidades Testadas

As principais funcionalidades testadas foram:

1. **Sugestões de Pesquisa (Autocomplete)**:

   **Cenário**: Verificar se sugestões corretas são exibidas ao digitar caracteres na barra de pesquisa.
    
   **Justificativa**: Este é um caso essencial para a experiência do usuário, permitindo uma navegação mais intuitiva.

2. **Menu de Categorias**:
   
   **Cenário**: Validar o funcionamento do menu de categorias e a navegação entre diferentes seções.
    
   
3. **Busca por Termos Inexistentes**:
    
   **Cenário**: Testar a resposta do sistema para buscas com termos inexistentes.
    
   
4. **Desempenho de Carregamento**:
    
   **Cenário**: Verificar o tempo de carregamento da página inicial no navegador Google Chrome.
    
   
## Justificativa para a Escolha dos Cenários Automatizados

Os cenários abaixo foram escolhidos para automação com base na frequência de uso e na criticidade das funcionalidades para a experiência do usuário na Amazon:

### Cenário: Verificar sugestões do autocomplete ao digitar três caracteres
- **Justificativa**: Este é um caso de uso frequente e essencial para a experiência do usuário, pois sugestões de pesquisa são um dos principais facilitadores de navegação e compra no site. Automatizar esse cenário ajuda a garantir a estabilidade da funcionalidade de autocomplete, melhorando a navegabilidade e usabilidade do site.

### Cenário: Verificar que nenhuma sugestão é exibida para uma palavra inexistente
- **Justificativa**: Esse é um teste negativo essencial, pois garante que o sistema lida corretamente com entradas inválidas sem sugerir opções irrelevantes. Automatizar esse tipo de teste ajuda a identificar problemas de consistência na interface e na lógica de busca, prevenindo comportamentos indesejados.

### Cenário: Testar funcionamento do menu de categorias ao lado da barra de pesquisa
- **Justificativa**: A funcionalidade do menu de categorias é crucial para a navegação dos usuários no site. Como ela é uma parte central do layout, esse teste garante que a interface está funcionando conforme esperado ao direcionar os usuários para as subcategorias específicas, proporcionando uma experiência fluida de navegação.

### Cenário: Verificar comportamento ao buscar por um termo inexistente
- **Justificativa**: Esse cenário é um teste negativo importante para a funcionalidade de busca, assegurando que o sistema apresenta uma mensagem clara e útil para o usuário quando nenhum resultado é encontrado. Esse tipo de automação ajuda a manter uma boa experiência de usuário e reduz a frustração em casos de busca mal-sucedida.


## Classe `CookieManager.java`

A classe `CookieManager.java` foi implementada especificamente para gerenciar cookies de sessão e autenticação, permitindo que o teste bypass o captcha apresentado pela Amazon. Este captcha, por vezes, bloqueia os testes automatizados, impedindo a execução fluida do teste de ponta a ponta. Ao controlar os cookies, conseguimos evitar bloqueios durante a execução dos cenários.

## Classe `WaitUtil.java`

A classe `WaitUtil.java` foi desenvolvida para gerenciar as esperas explícitas no Selenium WebDriver. Utilizamos esperas explícitas para aguardar que elementos específicos estejam visíveis ou clicáveis, garantindo que os testes sejam executados de forma mais estável e diminuindo as chances de falhas intermitentes causadas por carregamento de elementos.

## Evidências de Testes

As evidências capturadas durante a execução dos testes estão disponíveis na pasta `evidencias/`. Cada arquivo contém uma captura do resultado esperado ou atual para os cenários testados.

- **Sugestão de Autocomplete**  
  Caminho da Evidência: `evidencias/sugestoes_populares_20241028_052754.png`

- **Nenhuma Sugestão para Palavra Inexistente**  
  Caminho da Evidência: `evidencias/nenhuma_sugestao_20241028_052757.png`

- **Página de Resultados**  
  Caminho da Evidência: `evidencias/pagina_resultados_20241028_052803.png`

- **Lista de Produtos**  
  Caminho da Evidência: `evidencias/lista_produtos_relacionados_20241028_052803.png`



### Relatorio de Casos de Testes

1. Acesse a pasta `reports/` e abra o arquivo `testrail-report.pdf`.

## 🛠 Tecnologias Utilizadas

- ![Java](https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png) **Java**
- <img src="https://junit.org/junit5/assets/img/junit5-logo.png" alt="JUnit" width="50" /> **JUnit**
- <img src="https://upload.wikimedia.org/wikipedia/commons/d/d5/Selenium_Logo.png" alt="Selenium" width="50" /> **Selenium**
- ![Cucumber](https://img.icons8.com/color/48/000000/cucumber.png) **Cucumber**

## Como Executar os Testes

Para executar os testes localmente, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/amazon-automation.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd amazon-automation
   ```
3. Compile e execute os testes utilizando o Maven:
   ```bash
   mvn clean test
   ```
   

## Considerações Finais

Os cenários selecionados foram automatizados com foco em garantir a funcionalidade essencial da página inicial da Amazon. As classes utilitárias, como `CookieManager.java` e `WaitUtil.java`, foram desenvolvidas para contornar desafios específicos (como o captcha) e para assegurar estabilidade e precisão nos testes. Este projeto é uma demonstração prática de boas práticas de automação para garantir a qualidade em ambientes de e-commerce.

## 🤝 Fale comigo: 

Estou sempre aberto a colaborações e discussões sobre qualidade de software! Vamos nos conectar!

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nilsondasilvabrites/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/nilrd)
[![WhatsApp](https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white)](https://wa.me/5511940825120)
[![Gmail](https://img.shields.io/badge/Gmail-333333?style=for-the-badge&logo=gmail&logoColor=red)](mailto:nilson.brites@gmail.com)
