FROM gradle:8.7-jdk17 AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN ./gradlew dependencies --no-daemon

COPY commons commons
COPY transaction transaction

RUN ./gradlew :transaction:assemble --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/transaction/build/libs/*.jar transaction.jar

ENTRYPOINT ["java", "-jar", "transaction.jar"]