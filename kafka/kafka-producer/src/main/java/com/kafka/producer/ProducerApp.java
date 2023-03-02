package com.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.model.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
            Product pr = new Product("test",Math.random() * 100);
        List<Product> list= new ArrayList<Product>();
        list.add(pr);
        Order order = new Order((int) (Math.random()*100),list );

        return args -> {
            for(int i = 0 ; i < 1 ; i++){
                System.out.println("WYSYLAM ");
                template.send("topic1",order.toString());
            }

        };
    }



}