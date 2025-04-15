# Этап сборки
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Устанавливаем рабочую директорию
WORKDIR /build

# Кэшируем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходники и собираем проект
COPY src ./src
RUN mvn package -DskipTests

# Этап запуска
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл из этапа сборки
COPY --from=build /build/target/*.jar app.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/authorization-app.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
