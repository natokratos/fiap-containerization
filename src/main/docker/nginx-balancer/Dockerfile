FROM nginx:latest

RUN apt-get update && \ 
	apt-get install procps -y && \
	apt-get install curl -y

ADD nginx.conf /etc/nginx/nginx.conf

EXPOSE 8080
EXPOSE 80