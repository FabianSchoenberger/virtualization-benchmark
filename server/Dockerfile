FROM gradle:jdk21 AS build
VOLUME /home/gradle/.gradle
WORKDIR /app
COPY build.gradle.kts .
RUN gradle dependencies
COPY . .
RUN gradle distTar

FROM eclipse-temurin:21
COPY --from=build /app/build/distributions/server-1.0.0.tar server.tar
RUN tar -xf server.tar
ENTRYPOINT server-1.0.0/bin/server
