FROM openjdk:17

ADD target/kafka-project-0.0.1-SNAPSHOT.jar kafka-app.jar

EXPOSE 8081

ENTRYPOINT [ "java", "-jar","kafka-app.jar" ]
