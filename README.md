# 🧠 Jogo da Memória ODS

Este é o repositório do backend para o projeto "ODS Match", desenvolvido como parte da disciplina de Teste de Software. A aplicação é construída em Java com Spring Boot e serve como a API para o jogo, gerenciando jogadores, pontuações e a lógica de distribuição das cartas.

## 👨‍💻 Autor e Contexto Acadêmico

*   **Autor:** Allan Gabriel Silva de Freitas
*   **Universidade:** Universidade Federal Rural do Semi-Árido (UFERSA)
*   **Professor:** Dr. Alysson Figueira Milanez
*   **Disciplina:** Teste de Software

## 🎨 Frontend

O frontend deste projeto foi desenvolvido separadamente, utilizando tecnologias modernas para uma experiência de usuário rica e interativa.

*   **Tecnologias:** React, TypeScript e a metodologia Atomic Design.
*   **Repositório do Frontend:** [**>>Clique aqui<<**](https://github.com/Allan-Gabriell/frontGameODS.git)

## 📄 Documentação do Projeto

A documentação detalhada do projeto, incluindo requisitos, planos de teste e diagramas UML, pode ser encontrada nos links abaixo:

*   **Documento de Requisitos:** [**>>Clique aqui<<**](https://docs.google.com/document/d/1ba1oHgSv1CnUVb0j9CpQdIHdQAqEvysyI3w2M2KE5YI/edit?usp=sharing)
*   **Plano de Teste:** [**>>Clique aqui<<**](https://docs.google.com/document/d/1xr3K39St-RlHVK6wdY3AxCTjJs-uFewMOtn9JU2LY14/edit?usp=sharing)
*   **Apresentação e Diagrama de Classes:** [**>>Clique aqui<<**](https://docs.google.com/document/d/1KWeObJJWvu-NNa5BHNTrgqaPqdFAg3jBZSIZE2Y4_b8/edit?usp=sharing)
*   **Diagrama de Casos de Uso:** [**>>Clique aqui<<**](https://docs.google.com/document/d/1xyU6ZfBLYH74lX7jqqSoRrH7IUI0xUM08rCqiVmqpEg/edit?usp=sharing)

## 🛠️ Tecnologias Utilizadas no Backend

*   **Java 21**
*   **Spring Boot 3.5.5**
*   **Spring Data JPA**
*   **Maven**
*   **PostgreSQL** (Banco de Dados)
*   **Lombok**

## ⚙️ Configuração e Execução

Siga os passos abaixo para configurar e executar o ambiente de desenvolvimento localmente.

### Pré-requisitos

*   **JDK 21** ou superior.
*   **Maven** 3.x.
*   Uma instância do **PostgreSQL** em execução.

### Variáveis de Ambiente

Para que a aplicação se conecte corretamente ao banco de dados, é necessário configurar as variáveis de ambiente. O Spring Boot as utilizará para configurar o `DataSource`.

Abra o arquivo `application.properties` na pasta `src/main/resources` com o seguinte conteúdo:

```properties
# Configuração do Banco de Dados PostgreSQL
spring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# Configuração do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Em seguida, configure as seguintes variáveis no seu ambiente de execução (seja no seu sistema operacional ou na configuração de execução da sua IDE):

*   `DB_HOST`: O endereço do seu servidor de banco de dados (ex: `localhost`).
*   `DB_NAME`: O nome do banco de dados criado para esta aplicação.
*   `DB_USER`: O nome de usuário para acessar o banco de dados.
*   `DB_PASSWORD`: A senha para o usuário do banco de dados.

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone <https://github.com/Allan-Gabriell/MemoryGameODS.git>
    cd MemoryGameOds
    ```

2.  **Crie o banco de dados** no seu PostgreSQL.

3.  **Configure as variáveis de ambiente** conforme descrito acima.

4.  **Execute a aplicação** 

5.  A API estará disponível em `http://localhost:8080`.

## 🚀 Endpoints da API

A aplicação expõe os seguintes endpoints:

### Cartas (Cards)

*   `GET /card`
    *   **Descrição:** Retorna uma lista com 34 cartas (17 pares) embaralhadas, prontas para iniciar o jogo.
    *   **Resposta:** `200 OK`
      ```json
      [
          { "id": 1, "name": "ODS 1", "description": "Erradicação da Pobreza", "imgUrl": "url_da_imagem_1" },
          { "id": 5, "name": "ODS 5", "description": "Igualdade de Gênero", "imgUrl": "url_da_imagem_5" },
          // ... outras 32 cartas
      ]
      ```

### Jogador (Player)

*   `GET /player`
    *   **Descrição:** Retorna uma lista de todos os jogadores e suas pontuações.
*   `POST /player`
    *   **Descrição:** Cria um novo jogador. O número de movimentos iniciais é fixado em 24 e a pontuação inicial é 0.
    *   **Corpo da Requisição:**
      ```json
      {
          "name": "Nome do Jogador",
          "nacionality": "Nacionalidade do Jogador"
      }
      ```

### Demais endpoints ainda em implementação
