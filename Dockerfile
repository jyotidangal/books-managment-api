# Use lightweight OpenJDK 21 image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the Spring Boot JAR
COPY target/SpringRestService-0.0.1-SNAPSHOT.jar app.jar

# Expose custom port
EXPOSE 9090

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
