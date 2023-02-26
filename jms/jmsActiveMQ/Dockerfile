FROM openjdk:17

ADD target/jmsActiveMQ-1.0-SNAPSHOT.jar jms-app.jar

EXPOSE 8080 61616

ENTRYPOINT java  -jar jms-app.jar