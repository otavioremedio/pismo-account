FROM gradle:8.7-jdk17 AS build
WORKDIR /pismo-account

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY src ./src

RUN gradle clean build --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /pismo-account

COPY --from=build /pismo-account/build/libs/pismo-account.jar pismo-account.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pismo-account.jar"]