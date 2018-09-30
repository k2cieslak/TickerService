FROM openjdk:8-jdk-alpine
LABEL maintainer="kcieslak@outlook.com"
VOLUME /tmp
EXPOSE 8081
COPY ${JAR_FILE} /TickerService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/TickerService.jar"]