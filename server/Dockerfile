FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradle/ gradle/
COPY gradlew ./
COPY build.gradle.kts ./
RUN ./gradlew dependencies
COPY ./ ./
RUN ./gradlew distTar

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/build/distributions/server-*.tar server.tar
RUN tar -xf server.tar
ENTRYPOINT server-*/bin/server
