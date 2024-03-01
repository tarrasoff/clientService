FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY /build/libs/service.jar build/

WORKDIR /build
EXPOSE 8080
ENTRYPOINT java -jar service.jar
