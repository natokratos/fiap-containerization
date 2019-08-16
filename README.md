# itau-api-todo

Esta API REST armazena e atualiza tarefas. Ela possui os seguintes endpoints:

- **GET /todo/tasks**: Retorna todas as tarefas de todos os usuarios. Exemplo:

	curl -X GET http://localhost:8080/todo/tasks

- **GET /todo/tasks/{taskId}**: Retorna a tarefa especificada pelo {taskID}. Exemplo:

	curl -X GET http://localhost:8080/todo/tasks/2

- **GET /todo/tasks/{userName}**: Retorna as tarefas do usuário passando o nome deste em {userName}. Exemplo:
	
	curl -X GET http://localhost:8080/todo/tasks/Renato
	
- **POST /todo/tasks**: Adiciona uma nova tarefa. Exemplo:

	curl -X POST -d "{\"taskId\": 0, \"userName\": \"Renato\", \"status\": \"PENDING\", \"taskDescription\": \"TAREFA TESTE1\", \"dueDate\": \"2019-08-12T00:00:00.000-03:00\"}" -H "Content-Type: application/json" http://localhost:8080/todo/tasks

- **PUT /todo/tasks**: Altera uma tarefa. Exemplo:

	curl -X PUT -d "{\"taskId\": 2, \"userName\": \"Renato\", \"status\": \"COMPLETED\", \"taskDescription\": \"TAREFA ONE\", \"dueDate\": \"2019-08-12T00:00:00.000-03:00\"}" -H "Content-Type: application/json" http://localhost:8080/todo/tasks

- **DELETE /todo/tasks/{taskId}**: Apaga uma tarefa. Exemplo:

	curl -X DELETE http://localhost:8080/todo/tasks/1

- **GET /healthcheck**: Verifica se todos os endpoints estão no ar funcionando corretamente. Exemplo:

	curl -X GET http://localhost:8080/healthcheck

- **GET /metrics**: Retorna um relatório fornecendo dados sobre o **Total de Requisições**, **Requisições por Tipo de Operação**, **Requisções por Usuário** e a **Média de Tempo de Processamento das Requisições**. Exemplo:

	curl -X GET http://localhost:8080/metrics


# Pré-requisitos

- Linux ou MacOS. É possível fazê-lo, mas este documento não cobre os comandos e procedimentos para rodar no Windows;
- Docker versão mais atual;
- Docker Compose versão mais atual;
- Maven versão mais atual;
- Git client versão mais atual;
- IDE para Desenvolvimento: STS, Eclipse ou IntelliJ;
- Criar um diretório na estação chamado workpsace que conterá os códigos-fonte;
- Editar o arquivo /etc/hosts e adicionar os seguintes IP's:
	
	127.0.0.1		itau-api-todo1
	127.0.0.1		itau-api-todo2
	127.0.0.1		itau-api-todo3
	127.0.0.1		nginx-balancer
	127.0.0.1		itau-api-db
	
# Baixar o código-fonte

- Via linha de comando, entrar no diretório que conterá os códigos-fonte e executar os comandos abaixo, caso esteja vazio:

	cd $HOME/workspace
	git init
	git clone https://github.com/natokratos/itau-api-todo.git

- Abrir a IDE de desenvolvimento, importar como projeto Maven existente.

# Compilar a aplicação

- Via linha de comando, mudar para o diretório itau-api-todo dentro do diretório de códigos-fonte:
  
	cd $HOME/workspace/itau-api-todo

- Para este projeto, o banco de dados H2 já deve estar no ar para que os testes funcionem. Baixe o projeto **itau-api-db** no GitHub e siga as orientações do respectivo **README.md** para fazer o build e o deploy dele:

	https://github.com/natokratos/itau-api-db.git

- Assim que o banco estiver no ar, via linha de comando executar o comando abaixo para gerar as classes, executar os testes e gerar o relatório de cobertura:

	mvn clean install  

- A partir daqui basta usar a IDE de desenvolvimento para construir o código, compilar e testar a aplicação.

# Relatório de Cobertura

- O relatório estará disponível em:

	$HOME/workspace/itau-api-todo/target/site/jacoco/index.html

- Deve ser acessado usando um browser de sua escolha.

# Rodando a aplicação local
  
- Para rodar basta executar a aplicação pelo IDE de desenvolvimento.

# Rodando a aplicação no Docker

- A aplicação já deve estar compilada para que funcione no Docker, veja na seção **Compilar a Aplicação**. Ao final da compilação, o jar será copiado para o diretório do docker, permitindo que o processo continue;


- Via linha de comando, mudar para o diretório:

	cd $HOME/workspace/itau-api-todo/src/main/docker

- Via linha de comando, executar o comando abaixo:

	make build 

- As imagens serão criadas e/ou baixadas no registry do docker local;


- Via linha de comando, executar o comando abaixo:

	make run
	
- Ele vai criar os containers da aplicação, o container do balanceador e a rede compartilhada entre eles;


- Aguarde alguns instantes até que aplicação suba corretamente, via linha de comando verifique da seguinte forma:

	docker logs itau-api-todo1
	docker logs itau-api-todo2
	docker logs itau-api-todo3

- O procedimento atual visa que a aplicação tenha alta disponibilidade, subindo um container balanceador (**nginx-balancer**) para fazer o balanceamento entre 3 containers de aplicação (**itau-api-todo1**, **itau-api-todo2**, **itau-api-todo3**) e um banco de dados centralizado para todos eles (**itau-api-db**);


- A quantidade de containers da aplicação pode ser modificada no arquivo **nginx.conf** na seção ** upstream app_servers**, localizado em:

	$HOME/workspace/itau-api-todo/src/main/docker/nginx-balancer/nginx.conf

- Após modificá-lo, basta executar este procedimento novamente para que a imagem local do balanceador (**nginx-balancer**) seja modificada com o novo arquivo.

# Atualizar a Aplicação no Docker

- Compile a aplicação novamente conforme descrito na seção **Compilar a Aplicação**;

- Para atualizar a aplicação no docker, primeiro pare o primeiro container:

	docker stop itau-api-todo1

- Via linha de comando execute os comandos a seguir:

	cd $HOME/workspace/itau-api-todo/src/main/docker
	docker cp itau-api-todo*.jar itau-api-todo1:/root/itau-api-todo.jar

- Reinicie o container:
	
	docker start itau-api-todo1

- Teste se a aplicação subiu corretamente:

	curl -X GET http://localhost:8080/healthcheck

- Caso esteja funcionando, pare os outros dois containers:

	docker stop itau-api-todo2
	docker stop itau-api-todo3

- Via linha de comando execute os comandos a seguir:

	cd $HOME/workspace/itau-api-todo/src/main/docker
	docker cp itau-api-todo*.jar itau-api-todo2:/root/itau-api-todo.jar
	docker cp itau-api-todo*.jar itau-api-todo3:/root/itau-api-todo.jar
	
- Reinicie os containers:
	
	docker start itau-api-todo2
	docker start itau-api-todo3

# Banco em memória

- A versão atual do banco não persiste os dados em disco;

- Para acessar o H2 (banco em memória) local use o seguinte endereço:

	http://localhost:9090/h2
    
- Caso tenha subido no docker, basta mudar as portas para ver os bancos de cada aplicação no ar:

	http://localhost:9050/h2
	http://localhost:9070/h2
	http://localhost:9090/h2

- Coloque no campo JDBC_URL o valor:

	jdbc:h2:tcp://itau-api-db:9000/mem:itaudb
  
- Coloque no campo Username o valor:

	sa
  
- Deixe o campo Password vazio
  
- Clique em Connect

# Logs

- Os logs da aplicação são gravados no seguinte diretório:

	$HOME/logs 

# Melhorias

Identificamos até o momento as seguintes melhorias

- Inserir segurança via spring-security (OAuth2);
- Persistir em disco os dados do banco H2 interno;
- Usar o banco H2 apenas em desenvolvimento, criar novos perfis apontando para os bancos dos outros ambientes no momento do deploy;
- Definir e inserir mais estastísticas em /metrics, como por exemplo totais por data;
- Aumentar a cobertura de código dos testes, olhando para o relatório do Jacoco;
- A quantidade de containers de aplicação hoje está definida dentro do arquivo do docker-compose e da configuração do balanceador. Devemos usar o docker no modo swarm, cloud pública e/ou uma ferramenta como Kubernetes para escalar os containers horizontalmente de forma mais simples e parametrizada;
- Usar processos e ferramentas para CI/CD, como o GitLab CI/CD, Jenkins e SonarQube para agilizar o desenvolvimento e a identificação e correção de erros antes que cheguem nos ambientes produtivos.
 