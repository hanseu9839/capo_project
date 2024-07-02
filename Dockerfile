FROM eclipse-temurin:17 AS builder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar
RUN ls -l /app/build/libs #debuggin line

FROM eclipse-temurin:17
WORKDIR /app
COPY /build/libs/*.jar photocard.jar
RUN ls -l /app

ENTRYPOINT ["java","-jar", "photocard.jar", "-Dspring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

VOLUME /tmp