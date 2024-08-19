# Stage 1: Build and Test
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
LABEL authors="leoluiten"
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} /app/app.jar

# Entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]