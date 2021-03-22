FROM centos:latest

RUN yum install java-1.8.0-openjdk.x86_64 -y

ADD ifood-api-order*.jar /root/ifood-api-order.jar

EXPOSE 9090

ENTRYPOINT [ "bash", "-c", "java -jar /root/ifood-api-order.jar" ]