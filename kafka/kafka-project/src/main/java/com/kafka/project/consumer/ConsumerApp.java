package com.kafka.project.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }


    @KafkaListener(id = "topic1", topics = "topic1")
    public void listen(String in) {
        System.out.println("AAAAAAAAAAAAAAAAAAA    "+in+"AAAAAAAAAAAAAAAAAAA    ");
    }

}