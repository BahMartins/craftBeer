# Projeto para cadastro de cervejas artesanais

A Beer House é uma empresa que possui um catálogo de cervejas artesanais. Esta empresa está buscando entrar no mundo digital e para isso decidiu começar pelas APIs. As APIs serão utilizadas para compartilhar dados com os parceiros e também para o seu sistema web.

## Especificação do projeto

1. Stack utilizadas: 

- Java 11
- Spring Boot
- Spring Data
- Junit
- MySQL
- Swagger
- Lombok

2. Executar projeto:

- Clone esse repositório
```bash
    $ git clone https://github.com/BahMartins/craftBeer.git
```
- Instale as dependências <br/>
-- Java 11 <br/>
-- MySQL <br/>
-- Lombok
- Altere usuário e senha de acesso ao banco de dados no arquivo application.yml
- Execute a classe Main: Application.java
- As URLs para os testes via Postman são: <br/>
-- Metodos GET e POST: http://localhost:9000/beers <br/>
-- Metodos GET, PUT, DELETE e PATCH: http://localhost:9000/beers/{id}

3. Documentação do projeto:

- Swagger: http://localhost:9000/swagger-ui.html
