FROM maven:3.9-amazoncorretto-17 AS builder

WORKDIR /app

COPY app /app

RUN mvn clean install -U dependency:copy-dependencies

FROM openjdk:17

COPY --from=builder /app/target/*.jar /app/app.jar

HEALTHCHECK --interval=3s --timeout=10s CMD curl http://localhost:8080/api/status

ENTRYPOINT ["java", "-jar", "/app/app.jar"]