package com.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.example.model.Order;
import org.example.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class Producer implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private boolean running;

    @Autowired
    public Producer(KafkaTemplate<String,String> kafkaTemplate,
                    Properties properties) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);

            kafkaTemplate.send("order-topic", orderJson);
        } catch (JsonProcessingException e) {
            // Handle exception
        }
    }

    private void run() {

            Order order = createOrder();
            sendOrder(order);

    }


    public Order createOrder() {
        Product pr = new Product("test",Math.random() * 100);
        List<Product> list= new ArrayList<Product>();
        list.add(pr);
        Order order = new Order((int) (Math.random()*100),list );
        return order;
    }

    @Override
    public void start() {
        log.info("Starting");
        Executors.newSingleThreadExecutor()
                .submit(this::run);
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        running = false;
        runnable.run();
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Component
    @ConfigurationProperties("app.producer")
    public static class Properties {
    }

}


