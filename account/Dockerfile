FROM gradle:8.7-jdk17 AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN ./gradlew dependencies --no-daemon

COPY commons commons
COPY account account

RUN ./gradlew :account:assemble --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/account/build/libs/*.jar account.jar

ENTRYPOINT ["java", "-jar", "account.jar"]