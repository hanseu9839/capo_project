FROM --platform=linux/amd64 eclipse-temurin:17
LABEL maintainer="hanseu9839@gmail.com"
ARG JAR_FILE=build/libs/project-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} docker-realworld.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","docker-realworld.jar"]