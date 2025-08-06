# Guia para Gerar uma API REST de Biblioteca (Livraria)

Este documento descreve, em alto nível, as etapas e componentes necessários para que o agente gere o código-fonte de uma API REST de livraria usando Spring Boot com Java 21, Lombok e banco de dados H2 em memória.

---

## 1. Configuração Inicial do Projeto

- Definir projeto Maven ou Gradle com suporte a Java 21.
- Incluir dependências principais: Spring Web, Spring Data JPA, H2 e Lombok.
- Configurar o plugin do Spring Boot para empacotamento e execução.

---

## 2. Estrutura de Pacotes

1. **config**: arquivos de configuração geral (por exemplo, CORS, segurança básica).
2. **entity**: classes que representam tabelas do banco com anotações JPA.
3. **repository**: interfaces para acesso a dados, estendendo componentes JPA.
4. **service**: classes responsáveis pela lógica de negócio e uso de repositórios.
5. **controller**: definem endpoints REST para CRUD de entidades.
6. **loader**: componente de inicialização para inserir dados no H2.
7. **dto** (opcional): objetos de transferência de dados, se aplicação exigir separação de camadas.
8. **exception** (opcional): tratamento de erros customizados.

---

## 3. Configurações do Banco H2

- Definir URL de conexão em memória, nome de usuário e senha padrão.
- Habilitar console web do H2 em uma rota específica.
- Configurar estratégia de criação do esquema (por exemplo, `create-drop`).
- Ajustar propriedades de exibição de SQL no log.

---

## 4. Modelagem de Domínio e Relacionamentos

1. **Autor** (Author): entidade com atributos básicos (nome, nacionalidade) e relacionamento de um-para-muitos com Livros.
2. **Categoria** (Category): entidade com nome e lista de Livros associada.
3. **Livro** (Book): entidade com atributos como título, ISBN, número de páginas, e relacionamentos muitos-para-um com Autor e Categoria.

---

## 5. Repositórios e Serviços

- **Repositórios**: interfaces específicas para cada entidade (Author, Category, Book) aproveitando `JpaRepository`.
- **Serviços**: implementar operações CRUD genéricas (listar, buscar por ID, criar, atualizar, excluir) para cada entidade, utilizando injeção de repositório e tratamento de exceções quando não encontrado.

---

## 6. Controladores REST

- Criar controladores para cada entidade, expor rotas REST:
  - `GET /api/{entidade}`: listar todos
  - `GET /api/{entidade}/{id}`: buscar por ID
  - `POST /api/{entidade}`: criar novo registro
  - `PUT /api/{entidade}/{id}`: atualizar registro
  - `DELETE /api/{entidade}/{id}`: remover registro
- Mapear requisições e respostas no formato JSON.

---

## 7. Data Loader (Inicialização de Dados)

- Implementar um `CommandLineRunner` ou componente equivalente para:
  - Criar instâncias de Autores e Categorias.
  - Criar instâncias de Livros associadas aos Autores e Categorias.
  - Salvar todas as entidades no banco H2 assim que a aplicação inicia.

---

## 8. Testes e Verificação

- Validar a inicialização automática de dados no console do H2.
- Testar endpoints via ferramentas como Postman ou curl:
  - Verificar operações CRUD para Autor, Categoria e Livro.
- Confirmar a persistência em memória e o comportamento de criação e remoção de esquema.

---

## 9. Observações Finais

- Este guia serve de roteirização para a geração automática de código. Não contém trechos de código, apenas orientações de alto nível.
- O agente deve interpretar cada etapa e produzir as classes, configurações e componentes correspondentes.
