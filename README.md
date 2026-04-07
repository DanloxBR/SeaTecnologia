# 🚀 API de Clientes - Sea Tecnologia

API REST desenvolvida em **Java 8 + Spring Boot**, com foco em boas práticas, validações de negócio e controle de acesso.

---

## 📌 Sobre o Projeto

Esta aplicação permite o gerenciamento de clientes, incluindo:

* Create de clientes
* Read de clientes
* Update de dados
* Delete de clientes

A API aplica regras de negócio como validação de CPF, integração com serviço externo de CEP e controle de permissões por perfil de usuário.

---

## 🛠️ Tecnologias Utilizadas

* Java 8
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* H2 Database
* Maven
* Lombok
* Integração com ViaCEP

---

## 🔐 Autenticação e Autorização

A API utiliza **HTTP Basic Authentication** com dois perfis:

| Usuário | Senha     | Permissão      |
| ------- | --------- | -------------- |
| admin   | 123qwe!@# | Acesso total   |
| user    | 123qwe123 | Apenas leitura |

### 🔒 Regras de acesso

* `POST`, `PUT`, `DELETE` → ADMIN
* `GET` → ADMIN e USER

---

## 📂 Endpoints

### 🔹 Criar cliente

POST `/clientes`

### 🔹 Listar clientes

GET `/clientes`

### 🔹 Buscar cliente por ID

GET `/clientes/{id}`

### 🔹 Atualizar cliente

PUT `/clientes/{id}`

### 🔹 Deletar cliente

DELETE `/clientes/{id}`

---

## 📋 Regras de Negócio

### 👤 Cliente

* Nome: obrigatório (3 a 100 caracteres)
* CPF:

  * Validado
  * Persistido sem máscara
  * Retornado com máscara
  * Único no sistema

### 🏠 Endereço

* Obtido via integração com **ViaCEP**
* Não é permitido preenchimento manual
* Persistido sem máscara
* Retornado com máscara

### ☎️ Telefones

* Mínimo: 1 | Máximo: 5
* Tipos: RESIDENCIAL ou CELULAR
* Persistido sem máscara
* Retornado com máscara

### 📧 E-mails

* Múltiplos permitidos
* Pelo menos 1 obrigatório
* Deve ser válido

---

## 🔄 Integração Externa

A API consome o serviço:

* ViaCEP → https://viacep.com.br

Responsável por preencher automaticamente os dados de endereço a partir do CEP informado.

---

## 🗄️ Banco de Dados

* H2 Database (em memória)
* Console disponível em:

http://localhost:8080/h2-console

---

## ▶️ Como Executar o Projeto

```bash
# Clonar o repositório
git clone https://github.com/DanloxBR/SeaTecnologia.git

# Entrar na pasta
cd SeaTecnologia

# Executar
mvn spring-boot:run
```

---

## 🧪 Testes

Você pode testar a API utilizando:

* Postman
* Insomnia

---

## 📌 Estrutura do Projeto

```
controller   → Camada de entrada (REST)
service      → Regras de negócio
repository   → Acesso ao banco
entity       → Modelos JPA
dto          → Transferência de dados
integration  → Consumo de APIs externas
config       → Configurações (Security, Beans)
util         → Classes utilitárias
exception    → Tratamento global de erros
```

---

## 💡 Diferenciais do Projeto

* ✔ Validação completa de CPF
* ✔ Máscara dinâmica para CPF, CEP e telefone
* ✔ Integração com API externa (ViaCEP)
* ✔ Separação clara entre DTO e Entity
* ✔ Controle de acesso por perfil (ADMIN/USER)
* ✔ Boas práticas REST

---

## 👨‍💻 Autor

Desenvolvido por **Daniel Oliveira**

---

## 📄 Licença

Este projeto é de uso livre para fins de estudo e avaliação técnica.
