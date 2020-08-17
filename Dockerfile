FROM openjdk:11-jdk
ARG JAR_FILE=application/target/*.jar
COPY application/target/comunicacao-api.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/app.jar"]