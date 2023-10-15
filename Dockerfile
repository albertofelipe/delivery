FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY delivery/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]