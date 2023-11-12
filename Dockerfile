FROM eclipse-temurin:17
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:17
COPY build/libs/project-0.0.1-SNAPSHOT.jar photocard.jar

ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Djava.security.egd=file:/dev/./urandom","-jar","/photocard.jar"]
VOLUME /tmp
