# Step 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application using Eclipse Temurin (Stable)
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "demo.jar"]