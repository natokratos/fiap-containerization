# ifood-api-order

Esta API REST armazena e consulta pedidos. Ela possui os seguintes endpoints:

- **GET /ifood/orders/<orderId>**: Retorna o pedido com o id informado na requisição. Exemplo:

	curl -X GET http://localhost:9090/ifood/orders/0
	
- **POST /ifood/orders**: Adiciona um novo pedido. Exemplo:

	curl -X POST -d "{\"order\":{\"userName\": \"Renato\", \"status\": \"APPROVED\", \"paymentType\": \"DEBIT\", \"totalQtty\": \"3\", \"totalValue\": \"56.0\"}, \"orderItem\":[{\"itemDescription\": \"bife parmegiana\", \"itemDescription\": \"bife parmegiana\", \"itemQtty\": \"1\"}, {\"itemDescription\": \"Lasagna\", \"itemQtty\": \"1\"}, {\"itemDescription\": \"peixe\", \"itemQtty\": \"1\"}] }" -H "Content-Type: application/json" http://localhost:9090/ifood/orders

# Pré-requisitos

- Linux ou MacOS. É possível fazê-lo, mas este documento não cobre os comandos e procedimentos para rodar no Windows;
- Docker versão mais atual;
- Docker Compose versão mais atual;
- Maven versão mais atual;
- Git client versão mais atual;
- IDE para Desenvolvimento: STS, Eclipse ou IntelliJ;
- Criar um diretório na estação chamado workpsace que conterá os códigos-fonte;
- Editar o arquivo /etc/hosts e adicionar os seguintes IP's:
	
	127.0.0.1		ifood-api-order
	
# Baixar o código-fonte

- Via linha de comando, entrar no diretório que conterá os códigos-fonte e executar os comandos abaixo, caso esteja vazio:

	cd $HOME/workspace
	git init
	git clone https://github.com/natokratos/ifood-api-order.git

- Abrir a IDE de desenvolvimento, importar como projeto Maven existente.

# Compilar a aplicação

- Via linha de comando, mudar para o diretório fiap-containerization dentro do diretório de códigos-fonte:
  
	cd $HOME/workspace/fiap-containerization

- Executar o comando abaixo para gerar as classes, executar os testes e gerar o relatório de cobertura:

	mvn clean install  

- A partir daqui basta usar a IDE de desenvolvimento para construir o código, compilar e testar a aplicação.

# Relatório de Cobertura

- O relatório estará disponível em:

	$HOME/workspace/fiap-containerization/target/site/jacoco/index.html

- Deve ser acessado usando um browser de sua escolha.

# Rodando a aplicação local
  
- Para rodar basta executar a aplicação pelo IDE de desenvolvimento.

# Rodando a aplicação no Docker

- A aplicação já deve estar compilada para que funcione no Docker, veja na seção **Compilar a Aplicação**. Ao final da compilação, o jar será copiado para o diretório do docker, permitindo que o processo continue;


- Via linha de comando, mudar para o diretório:

	cd $HOME/workspace/fiap-containerization/src/main/docker

- Via linha de comando, executar o comando abaixo:

	make build 

- As imagens serão criadas e/ou baixadas no registry do docker local;


- Via linha de comando, executar o comando abaixo:

	make run
	
- Ele vai criar os containers da aplicação, o container do balanceador e a rede compartilhada entre eles;


- Aguarde alguns instantes até que aplicação suba corretamente, via linha de comando verifique da seguinte forma:

	docker logs ifood-api-order

# Atualizar a Aplicação no Docker

- Compile a aplicação novamente conforme descrito na seção **Compilar a Aplicação**;

- Para atualizar a aplicação no docker, primeiro pare o primeiro container:

	docker stop ifood-api-order

- Via linha de comando execute os comandos a seguir:

	cd $HOME/workspace/fiap-containerization/src/main/docker
	docker cp ifood-api-order*.jar ifood-api-order:/root/ifood-api-order.jar

- Reinicie o container:
	
	docker start ifood-api-order

- Teste se a aplicação subiu corretamente:

	curl -X POST -d "{\"order\":{\"userName\": \"Renato\", \"status\": \"APPROVED\", \"paymentType\": \"DEBIT\", \"totalQtty\": \"3\", \"totalValue\": \"56.0\"}, \"orderItem\":[{\"itemDescription\": \"bife parmegiana\", \"itemDescription\": \"bife parmegiana\", \"itemQtty\": \"1\"}, {\"itemDescription\": \"Lasagna\", \"itemQtty\": \"1\"}, {\"itemDescription\": \"peixe\", \"itemQtty\": \"1\"}] }" -H "Content-Type: application/json" http://localhost:9090/ifood/orders
	
	curl -X GET http://localhost:9090/ifood/orders/0

# Banco em memória

- A versão atual do banco não persiste os dados em disco;

- Para acessar o H2 (banco em memória) local use o seguinte endereço:

	http://localhost:9090/h2

- Coloque no campo JDBC_URL o valor:

	jdbc:h2:mem:ifoodOrderDb
  
- Coloque no campo Username o valor:

	sa
  
- Deixe o campo Password vazio
  
- Clique em Connect

# Logs

- Os logs da aplicação são gravados no seguinte diretório:

	$HOME/logs 

 