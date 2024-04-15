FROM gradle:jdk21 AS build
VOLUME /home/gradle/.gradle
WORKDIR /app
COPY build.gradle.kts .
RUN gradle dependencies
COPY . .
RUN gradle distTar

FROM eclipse-temurin:21
COPY --from=build /app/build/distributions/client-1.0.0.tar client.tar
RUN tar -xf client.tar
ENTRYPOINT client-1.0.0/bin/client
