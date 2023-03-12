package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.model.Order;
import org.example.model.Product;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class Producer implements SmartLifecycle {

    private final RateLimiter rateLimiter;
    private KafkaTemplate<String, String> kafkaTemplate;
    private boolean running;

    @Autowired
    public Producer(KafkaTemplate<String,String> kafkaTemplate,
                    Properties properties) {
        this.kafkaTemplate = kafkaTemplate;
        rateLimiter = RateLimiter.create(properties.rateLimit);
    }

    private void run() {
        while (running) {
            rateLimiter.acquire();
            Order order = createOrder();
            kafkaTemplate.send("topic1", order.toString());
        }
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
        double rateLimit = 0.5;
    }
}


