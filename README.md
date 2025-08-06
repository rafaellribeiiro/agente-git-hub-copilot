# livraria-api

Este projeto foi gerado automaticamente pelo GitHub Copilot com base em um arquivo de instruções em Markdown.

## Visão Geral

1. **Criação do arquivo de instruções** `instrucoes.md`, contendo de forma resumida:

   * Requisitos da API REST de livraria.
   * Bibliotecas e frameworks necessários: Java 17, Spring Boot 3.5.4, Lombok, H2, Springdoc OpenAPI.
   * Endpoints CRUD e documentação de API (Swagger/OpenAPI).
   * Inserção de dados para testes via `DataLoader`.
2. **Estrutura inicial**: pasta vazia `livraria-api` contendo apenas `instrucoes.md`.
3. **Geração do projeto**: no IntelliJ IDEA, com plugin GitHub Copilot ativo, pediu-se ao Copilot que lesse `instrucoes.md` e montasse o projeto conforme diretrizes.

---

## Pré-requisitos

* Java 17
* Maven 3.9+
* IntelliJ IDEA com plugin GitHub Copilot

---

## Como Executar

```bash
# clonar o repositório
git clone https://github.com/seu-usuario/agente-git-hub-copilot.git
cd agente-git-hub-copilot/livraria-api

# compilar e executar
mvn clean spring-boot:run
```

A aplicação ficará disponível em `http://localhost:8080` e a documentação Swagger em `http://localhost:8080/swagger-ui.html`.

---

## Estrutura do Projeto

```text
livraria-api/
├── src/
│   └── main/
│       ├── java/com/livraria
│       │   ├── config/         # Configurações (H2, Springdoc)
│       │   ├── controller/     # Controllers REST (Autor, Categoria, Livro)
│       │   ├── dto/            # Data Transfer Objects
│       │   ├── entity/         # Entidades JPA (Autor, Categoria, Livro)
│       │   ├── exception/      # Tratamento de exceções
│       │   ├── loader/         # DataLoader (popula H2)
│       │   ├── repository/     # Repositórios Spring Data JPA
│       │   └── service/        # Lógica de negócio
│       └── resources/
│           └── application.properties # Configuração de banco e porta
├── instrucoes.md                     # Guia de instruções para o Copilot
├── pom.xml                           # Dependências e build Maven
└── README.md                         # Este arquivo
```

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5.4 (*web*, *data-jpa*, *validation*)
* Lombok
* H2 Database (in-memory)
* Springdoc OpenAPI (Swagger UI)

---

## Controllers

O projeto contém três controllers:

* **AutorController** (`/api/autores`): CRUD de autores.
* **CategoriaController** (`/api/categorias`): CRUD de categorias.
* **LivroController** (`/api/livros`): CRUD de livros usando DTO para transferir dados.

Todos documentados com Swagger.

---

## DataLoader

Em `com.livraria.loader.DataLoader`, ao iniciar a aplicação, são criados exemplos de `Autor`, `Categoria` e `Livro` no H2 para facilitar testes.

---

## Documentação da API

Acesse:

```
http://localhost:8080/swagger-ui.html
```

---

> *Gerado via GitHub Copilot a partir de `instrucoes.md`.*
