# Sistema de Agendamentos e Vagas

Este projeto é uma aplicação web para gerenciamento de agendamentos e controle de disponibilidade de vagas. Utiliza **Spring Boot** como backend, **PrimeFaces** para o frontend, e **HSQLDB** como banco de dados relacional. O sistema implementa regras de negócio para garantir a integridade dos agendamentos em relação às vagas disponíveis.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **JSF (PrimeFaces)**
- **HSQLDB**
- **Maven**

## Funcionalidades

1. **Cadastro de Vagas**:
   - Permite cadastrar vagas disponíveis para um período (data de início e término).
   - Define a quantidade de vagas disponíveis.
   
2. **Cadastro de Solicitantes**:
   - Cadastro de solicitantes que serão associados aos agendamentos.

3. **Agendamentos**:
   - Registro de agendamentos para um solicitante em uma data específica.
   - Verificação automática da disponibilidade de vagas antes de confirmar o agendamento.
   - Controle de limite de vagas por solicitante (25% do total de vagas disponíveis).

4. **Consulta de Agendamentos**:
   - Visualização de agendamentos já cadastrados, com dados como nome do solicitante e percentual de vagas utilizadas no período.

## Regras de Negócio

- **Disponibilidade de Vagas**: O sistema impede o agendamento caso a quantidade de vagas disponíveis para o período seja insuficiente.
- **Limite de Agendamentos por Solicitante**: Um solicitante não pode agendar mais de 25% das vagas totais em um período específico.
- **Agendamento Existente**: Não é permitido realizar um novo agendamento para um solicitante com um já existente.

## Arquitetura

A aplicação segue o padrão **MVC (Model-View-Controller)** e está dividida nas seguintes camadas:

- **Model**: Representa as entidades do sistema, como `Agendamento`, `Vagas` e `Solicitante`.
- **Controller/Service**: Contém a lógica de negócios. Aqui são feitas as verificações de disponibilidade de vagas, limites de agendamentos e as interações com o banco de dados via repositórios.
- **View**: Utiliza **PrimeFaces** para renderizar as telas de cadastro, visualização e consulta.

### Entidades Principais

- **Agendamento**: Representa o agendamento de um solicitante, com campos como data, número de vagas e motivo.
- **Vagas**: Define a quantidade de vagas disponíveis em um período.
- **Solicitante**: Representa o solicitante que irá realizar os agendamentos.

## Instalação e Execução

### Pré-requisitos

- **Java 17**
- **Maven**

### Passos para executar o projeto

1. Clone o repositório:
   git clone https://github.com/gvinidev/teste-pratico-log.git
   
2. Compile o projeto:
	mvn install -DskipTests
	
3. Rode a aplicação:
   java -jar -Dserver.port=9494 target/Teste-Pratico-Desenvolvedor-Java-0.0.2-SNAPSHOT.jar
   OBS.: Também é possível subir o projeto como uma aplicação Spring diretamente via Spring Tools Suite ou IntelliJ.
   
## Estrutura do Projeto

O projeto está dividido em três pacotes principais, sendo eles `backend`, `model` e `web`.

- **backend**: 
	- pacote **config**, contendo um Handler de Exceptions personalizadas.
	- pacote **controller**, contendo os controller Rest para cadastro de endpoints.
	- pacote **repository**, contendo as classes do JpaRepository.
	- pacote **service**, contendo as classes de serviço, comunicação direta com banco de dados e controle de regras de negócio.

- **model**:
	- pacote **dto**, contendo as classes de DTO.
	- pacote **entity**, contendo as classes de entidade do banco de dados.
	- pacote **exception**, contendo as exceções personalizadas.

- **web**:
	- pacote **client**, contendo as classes que fazem comunicação com o backend através dos endpoints.
	- pacote **controller**, contendo as classes de bean.
	
Além disso, o projeto tem o diretório resources, que contém o arquivo application.properties para configurações do Spring e do projeto em si, assim como os arquivos xhtml e as configurações do JSF.
