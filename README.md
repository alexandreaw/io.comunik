# Comunik (App de Notificação)

Esse projeto tem como finalidade agendar envio de comunicação/notificacoes para usuarios em diversos formatos

## Arquitetura

Para esse projeto foi utilizada a Arquitetura Hexagonal, segue detalhes no link: https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)

## Stack

- Java/OpenJDK 11
- Spring boot
- PostgreSql
- Liquibase
- Swagger
- Docker
- jUnit,
- Mockito

## Endpoints

## Build e deploy com Docker

Para continuar você precisa:

- Clonar o repositorio e entrar na pasta do projeto.
- Docker/docker-compose instalado e configurado
- Java/OpenJDK 11

Passo 1: empacotar projeto

	./mvnw clean package
	
Passo 2: Criar imagem Docker do projeto

	docker build -t magalu/comunik .
	
Passo 3: Rodar

	docker-compose up

Se todos os passos tiverem sido executados corretamente a Api já vai estar diponivel na porta 8080.

## Swagger

Para ter acesso ao swagger ui acesse

	http://localhost:8080/swagger-ui.html