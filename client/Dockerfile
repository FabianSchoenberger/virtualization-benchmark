FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradle/ gradle/
COPY gradlew ./
COPY build.gradle.kts ./
RUN ./gradlew dependencies
COPY ./ ./
RUN ./gradlew distTar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/distributions/client-*.tar client.tar
RUN tar -xf client.tar
ENTRYPOINT client-*/bin/client "http://server:8080"
