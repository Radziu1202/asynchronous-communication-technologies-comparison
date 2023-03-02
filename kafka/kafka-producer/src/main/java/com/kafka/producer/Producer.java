package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.model.Order;

import java.util.Map;

public class Producer {

    private final KafkaProducer<String, Order> producer;
    private final String topic;

    public Producer(String topic, Map<String, Object> configs) {
        this.producer = new KafkaProducer<>(configs);
        this.topic = topic;
    }

    public void sendOrder(Order order) {
        ProducerRecord<String, Order> record = new ProducerRecord<>(topic, order);
        producer.send(record);
    }

    public void close() {
        producer.close();
    }

}


