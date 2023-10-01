# Technologies Used:

- Java 17
- Spring Boot 3.1.3
- Spring Web 3.1.3
- Spring Security 3.1.3
- Spring Data JPA 3.1.3
- Lombok
- Hibernate and Hibernate Validator (Implements Jakarta Validator)
- Eclipse Angus Mail (Implements Jakarta Mail)
- Thymeleaf (For generate email templates)
- Maven

- PostgreSQL
- Docker

# Conceitos Utilizados

- Factory Method to create objects.
- Authentication with Spring Security using a JWT bearer access token.
- Thymeleaf to generate emails templates and Eclipse Angus Mail to send it.
- Recovery Password using email tokens.
- Activate Account using email tokens.
- OWASP Security Recommendations for Password Recovery (Using URL Token, sent by email, recovery)

# Fluxos

## Autenticação

### Fluxo 01
- O usuário realiza o login na aplicação.
- O usuário é redirecionado para a página inicial da aplicação.

### Fluxo 02

- O usuário clica no botão "Recuperar Senha"
- O usuário insere o seu email e, se ele existir, um código de confirmação é enviado para esse email.
- O usuário recebe um link e é redirecionado para a página de mudança de senha.
- O usuário insere a senha nova, clica em "Confirmar" e realiza a mudança de senha.

### Fluxo 03

- O usuário clica no botão "Criar Conta"
- O usuário insere o seu nome e a senha.
- Um email de confirmação é enviado para o usuário.
- Após o usuário clicar no link de confirmação do email, a conta do usuário é ativada e ele pode usar a aplicação.

### Fluxo 04

- O usuário realiza o login na aplicação, mas a conta dele não está ativada.
- O usuário recebe um email com um link para ativar a conta.
- Após o usuário clicar no link de confirmação do email, ele realiza o login novamente na aplicação.

### Fluxo 05

- O usuário está logado na aplicação e deseja mudar a sua senha.
- O usuário insere a sua senha atual e a nova senha.
- Se a senha atual estiver correta e se a nova senha cumprir os padrões de segurança, a senha é alterada.

## Fluxos Internos

### Criação de Conta

- O usuário clica no botão de criar conta, realiza a criação da conta, recebe um email para confirmar a conta e, ao clicar no link do email, o token é consumido, o usuário ativa a conta e pode usá-la normalmente.

### Recuperação de Senha

- O usuário clica no botão de recuperar senha, insere o seu email, recebe um email com um link, que tem um token, ao clicar no link, ele é redirecionado para a página de mudança de senha, insere o código de confirmação e, se o código estiver correto e o token estiver válido, ele pode mudar a senha. Após enviar a requisição de alterar a senha, o token é consumido.

### Token

- Ao logar, o usuário recebe um access e um refresh token. Quando o access token expira, ele utiliza o refresh token no endpoint "/auth/refresh-token" para receber um novo access token. Quando o refresh-token expira, o usuário deve realizar o login novamente, para receber um novo refresh token.