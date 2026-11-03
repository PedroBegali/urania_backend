# ⚛️ UrâniaQuest Backend — API RESTful (Java Advanced)

API RESTful desenvolvida em **Java com Spring Boot** para apoiar a solução educativa **UrâniaQuest** no **Challenge 2026 (FIAP + CTMSP / Marinha do Brasil / AMAZUL)**.

## 🎯 Sobre o Projeto
O **UrâniaQuest** tem como objetivo aproximar a sociedade da tecnologia nuclear, desmistificando conceitos e demonstrando seus benefícios práticos na **Alimentação (irradiação de alimentos)**, **Saúde (radiofármacos)**, **Geração de Energia Limpa**, **Amazônia Azul**, **Indústria** e **Pesquisa Científica**.

Esta API gerencia o catálogo de **Categorias Educativas** e os **Flashcards Interativos (Quiz)** utilizados tanto no aplicativo mobile (Flutter) quanto nas dinâmicas presenciais em feiras e eventos institucionais da **AMAZUL**.

---

## 👨‍💻 Integrantes do Grupo (Turma 2TDSPA)
- **Denise Santos** — RM: 567559
- **Lucas Dudena** — RM: 567600
- **Karen Cardoso** — RM: 566870
- **Pedro Begali** — RM: 567478

---

## 🛠️ Tecnologias e Arquitetura Utilizadas
- **Java 17+ / 21**
- **Spring Boot 3** (`Spring Web`, `Spring Data JPA`, `Spring Validation`)
- **Banco de Dados:** H2 Database / Oracle Database
- **Documentação Interativa:** Springdoc OpenAPI 3 (Swagger UI)
- **Arquitetura em Camadas:**
  - `domainmodel.entities`: Entidades JPA (`Categoria` e `Flashcard`) com relacionamento `1:N`.
  - `datasource.repositories`: Repositórios Spring Data JPA com *Query Methods* para busca textual.
  - `services`: Regras de negócio desacopladas em interfaces e implementações (`ServiceImpl`).
  - `resources` e `dtos`: Endpoints RESTful documentados e validados com Bean Validation.
  - `infrastructure`: Carga automática de dados para teste (`DataLoader`), configuração OpenAPI (`OpenApiConfig`) e tratamento global de exceções (`GlobalExceptionHandler`).

---

## 🎥 Demonstração em Vídeo (Swagger e H2 Database)

Para conferir a API em pleno funcionamento, incluindo as rotas mapeadas na interface interativa do Swagger e a persistência inicial dos dados via H2 Database, assista ao nosso vídeo de evidências:

▶️ **[Clique aqui para assistir ao vídeo de demonstração](https://youtu.be/uBLofpMqIUo)**

---

## 🚀 Como Executar o Projeto (Passo a Passo)

### Pré-requisitos
Certifique-se de ter o **Java 17** (ou superior) instalado no seu computador. 

### Opção A: Executando de forma visual via IDE (Recomendado para Leigos)
1. **Baixe o projeto:** Clique no botão verde "Code" no topo desta página e selecione **"Download ZIP"**. Extraia a pasta no seu computador.
2. Abra a sua IDE de preferência (IntelliJ IDEA, Eclipse ou VS Code).
3. Selecione a opção **"Open"** (Abrir) e selecione a pasta `uraniaquest-backend` que você acabou de extrair.
4. Aguarde alguns segundos enquanto a IDE baixa as dependências automaticamente.
5. No painel de arquivos (geralmente à esquerda), navegue até: `src` ➔ `main` ➔ `java` ➔ `br.com.uraniaquest.backend` ➔ e abra o arquivo **`BackendApplication.java`**.
6. Clique no botão de **Play (▶️)** verde ao lado da linha de código `public class BackendApplication` para iniciar o servidor.

### Opção B: Executando via Terminal (Prompt de Comando)
1. Abra o terminal na pasta raiz do projeto.
2. Digite o seguinte comando e aperte Enter:
   - **No Windows:** `mvnw.cmd spring-boot:run`
   - **No Mac/Linux:** `./mvnw spring-boot:run`

### Acessando a Aplicação
Independentemente da opção escolhida, após o terminal exibir a mensagem indicando que o Tomcat foi iniciado (geralmente *"Started BackendApplication in X seconds"*), **clique no link abaixo para testar a aplicação no seu navegador:**

👉 **[Acessar Interface do Swagger (http://localhost:8080/swagger-ui.html)](http://localhost:8080/swagger-ui.html)**

*(Nota: O banco de dados já vem com dados pré-cadastrados para facilitar o seu teste).*

---

## 🔗 Principais Endpoints da API

### Categorias (`/api/categorias`)
* `GET /api/categorias` — Lista todas as categorias educativas.
* `GET /api/categorias/{id}` — Busca uma categoria específica por ID.
* `GET /api/categorias/pesquisa?termo={texto}` — Busca categorias por texto (Query Method).
* `POST /api/categorias` — Cadastra uma nova categoria (com validação `@Valid`).
* `PUT /api/categorias/{id}` — Atualiza os dados de uma categoria existente.
* `DELETE /api/categorias/{id}` — Remove uma categoria.

### Flashcards (`/api/flashcards`)
* `GET /api/flashcards` — Lista todos os flashcards cadastrados.
* `GET /api/flashcards/{id}` — Busca um flashcard por ID.
* `GET /api/flashcards/categoria/{categoriaId}` — Lista os flashcards de uma categoria específica.
* `GET /api/flashcards/pesquisa?termo={texto}` — Busca flashcards contendo o termo no enunciado (Query Method).
* `POST /api/flashcards` — Cadastra um novo flashcard de múltipla escolha.
* `PUT /api/flashcards/{id}` — Atualiza um flashcard existente.
* `DELETE /api/flashcards/{id}` — Remove um flashcard.
