package com.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    RabbitMQSender rabbitMQSender;

    @KafkaListener(topics = "order-topic")
    public void receiveOrder(String orderJson) throws JsonProcessingException {
        try {
            System.out.println("Kafka Odebrała i wysyla na rabbita" + orderJson);
            rabbitMQSender.send(orderJson);

            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(orderJson, Order.class);
            // Handle the deserialized Order object
            //logger.error("AAAAAAAAAAAAAAAAAAAAAAAAAA" + order.getOrderNumber());
        } catch (JsonProcessingException e) {
           throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
