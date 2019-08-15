FROM centos:latest

RUN yum install java-1.8.0-openjdk.x86_64 -y

ADD itau-api-todo*.jar /root/itau-api-todo.jar

EXPOSE 9090

ENTRYPOINT [ "bash", "-c", "java -jar /root/itau-api-todo.jar" ]