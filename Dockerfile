# Dockerfile
FROM eclipse-temurin:21
WORKDIR /app
COPY target/guardx-auth.jar guardx-auth.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "guardx-auth.jar"]
