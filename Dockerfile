FROM eclipse-temurin:17 AS builder

# 환경 변수 정의
ARG G_CLIENT_ID
ARG G_CLIENT_SECRET
ARG K_CLIENT_ID
ARG K_CLIENT_SECRET
ARG JWT_SECRET_KEY
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG AWS_BUCKET_KEY
ARG AWS_BUCKET_SECRET_KEY

# 환경 변수를 환경 변수 파일에 저장하여 Docker 내에서 사용 가능하게 합니다.
ENV G_CLIENT_ID=${G_CLIENT_ID}
ENV G_CLIENT_SECRET=${G_CLIENT_SECRET}
ENV K_CLIENT_ID=${K_CLIENT_ID}
ENV K_CLIENT_SECRET=${K_CLIENT_SECRET}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}
ENV AWS_BUCKET_KEY=${AWS_BUCKET_KEY}
ENV AWS_BUCKET_SECRET_KEY=${AWS_BUCKET_SECRET_KEY}

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