FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/authorization-app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
