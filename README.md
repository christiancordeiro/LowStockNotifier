# Estoque API

API para gerenciamento de produtos em estoque, com alerta por e-mail quando o estoque estiver baixo.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (banco de dados em memória)**
- **Spring Mail**
- **Lombok**

## Configuração do Projeto

### 1. Clone o repositório
```sh
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Configurar as Variáveis de Ambiente
Crie um arquivo `.env` na raiz do projeto e adicione:
```sh
EMAIL_DESTINATARIO=seuemail@gmail.com
SPRING_MAIL_USERNAME=seuemail@gmail.com
SPRING_MAIL_PASSWORD=suasenha
```

**Importante:** Para utilizar o envio de e-mails via Gmail, é necessário gerar uma [senha de segurança específica para aplicativos](https://support.google.com/mail/answer/185833?hl=pt-BR). Essa senha é gerada automaticamente pelo Google e possui exatamente 16 caracteres. Essa senha deve ser utilizada na configuração abaixo.

No `application.properties`, use:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${SPRING_MAIL_USERNAME}
spring.mail.password=${SENHA_SMTP}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```



### 3. Instale as dependências
```sh
mvn clean install
```

### 4. Execute a API
```sh
mvn spring-boot:run
```

## Endpoints Disponíveis

### Criar um Produto
- **POST** `/api/produto/create`
- **Body (JSON):**
```json
{
  "name": "Produto Teste",
  "quantidadeEstoque": 10
}
```

### Consultar Produtos com Estoque Baixo
- **GET** `/api/produtos/estoque-baixo`
- **Resposta:** Lista de produtos com estoque abaixo do limite.

## Funcionamento do Alerta de Estoque Baixo
- Sempre que um novo produto é criado, o sistema verifica se algum produto está abaixo do limite.
- Caso tenha produtos com estoque baixo, um e-mail de alerta é enviado automaticamente.

## Considerações de Segurança
- Nunca exponha senhas ou credenciais no GitHub.
- Use variáveis de ambiente ou arquivos `.env` (com `.gitignore`).

