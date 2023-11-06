FROM --platform=linux/amd64 eclipse-temurin:17 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM --platform=linux/amd64 eclipse-temurin:17
COPY --from=builder build/libs/*.jar photoCard.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/photoCard.jar"]