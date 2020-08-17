
# Comunik (Api de Notificação)

Esse projeto tem como finalidade agendar envio de comunicação/notificações para usuários em diversos formatos

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

Os seguintes endpoints estão disponíveis na Api

### Agendar novo envio de notificação
**POST**: /enviocomunicacao 
*body*:

	{
	  "dataHoraEnvio": "2020-08-17T18:02:42.138Z",
	  "destinatario": "string",
	  "mensagem": "string",
	  "tipoComunicacao": "EMAIL"
	}
*return* ( Retorna o ID que sera utilizado para consulta de status )

	{
	  "id": "a05cc1cb-3593-45e8-a31c-9f253fe032b8"
	}

### Consulta status agendamento envio

**GET**: /enviocomunicacao/status/{id}

*return* ( Retorna informações do status envio )

	{  
	  	"id":  "a05cc1cb-3593-45e8-a31c-9f253fe032b8",  
	  	"status":  "AGENDADO"  
	}
### Apagar solicitação de agendamento
**DELETE**: /enviocomunicacao/{id}

*return* No content

## Executando os testes

Para executar os testes basta rodar o comando

	./mvnw clean verify

## Build, deploy e run com Docker

Para continuar você precisa:

- Clonar o repositório e entrar na pasta do projeto.
- Docker/docker-compose instalado e configurado
- Java/OpenJDK 11

Para empacotar o sistema e rolada-lo siga os passos a seguir:

**Passo 1:** Empacotar projeto

	./mvnw clean package
	
**Passo 2:** Criar uma imagem Docker do projeto

	docker build -t comunik/comunik .
	
**Passo 3:** Rodar com docker-compose

	docker-compose up

Se todos os passos tiverem sido executados corretamente a Api já vai estar disponível na porta 8080.

## Swagger

Para ter acesso ao swagger ui acesse

http://localhost:8080/swagger-ui.html