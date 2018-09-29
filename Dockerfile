FROM openjdk:8-jdk-alpine
LABEL maintainer="kcieslak@outlook.com"
VOLUME /tmp
EXPOSE 8081
COPY ./target/TickerService-0.1-SNAPSHOT.jar /TickerService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/TickerService.jar"]