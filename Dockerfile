FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/TickerService-0.1-SNAPSHOT.jar /TickerService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/TickerService.jar"]