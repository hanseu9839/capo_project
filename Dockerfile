FROM eclipse-temurin:17 AS builder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/build/libs/PhotoCardTradeProjectBack-0.0.1-SNAPSHOT.jar photocard.jar
#COPY src/main/resources/photocard-firebase-adminsdk.json /app/photocard-firebase-adminsdk.json
ENTRYPOINT ["java","-jar", "photocard.jar", "-Dspring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

VOLUME /tmp