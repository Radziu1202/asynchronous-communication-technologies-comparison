FROM openjdk:17

ADD target/kafka-producer-0.0.1-SNAPSHOT.jar kafka-producer.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar","kafka-producer.jar" ]
