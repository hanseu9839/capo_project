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
COPY src/main/resources/photocard-firebase-adminsdk.json /app/photocard-firebase-adminsdk.json
RUN ls -l /app

ENTRYPOINT ["java","-jar", "photocard.jar", "-Dspring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

VOLUME /tmp