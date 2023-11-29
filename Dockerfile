# syntax = docker/dockerfile:1.2
#
# Build stage
#
FROM maven:3.8.6-openjdk-18 AS build
COPY . .
RUN mvn clean package assembly:single -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/TP-DDS-2023-1.0-SNAPSHOT-jar-with-dependencies.jar incidentes.jar
COPY src/main/resources/template/project.properties /root/resources/template/project.properties
COPY src/main/resources/template/password-top-10000.txt /root/resources/template/password-top-10000.txt

# ENV PORT=8080
EXPOSE 8080
CMD ["java","-classpath","incidentes.jar","server.App"]
