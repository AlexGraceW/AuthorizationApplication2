FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/AuthorizationApplication-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
