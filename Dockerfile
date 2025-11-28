FROM maven:3.9.6-eclipse-temurin-25 AS build
WORKDIR /workspace

# Copy only what we need to leverage Docker cache for dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw

# Copy source and package the application (skip tests for faster builds)
COPY src ./src
RUN ./mvnw -B package -DskipTests

FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the jar produced in the build stage
COPY --from=build /workspace/target/*.jar /app/app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Allow runtime PORT override (Render sets $PORT). Also provide basic JVM defaults.
ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
