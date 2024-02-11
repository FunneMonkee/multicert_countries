FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN ./gradlew war --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/countries-0.0.1-SNAPSHOT.war .

ENTRYPOINT ["java", "-jar", "countries-0.0.1-SNAPSHOT.war"]