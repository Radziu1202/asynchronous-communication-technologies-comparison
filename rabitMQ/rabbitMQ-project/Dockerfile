FROM openjdk:17

ADD target/rabitMQ-project-0.0.1-SNAPSHOT.jar rabbit-app.jar

EXPOSE 5672 8080

ENTRYPOINT java  -jar rabbit-app.jar