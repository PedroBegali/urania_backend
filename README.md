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

## 🚀 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/PedroBegali/urania_backend.git](https://github.com/PedroBegali/urania_backend.git)
   cd uraniaquest-backend