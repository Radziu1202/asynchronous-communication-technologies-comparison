package com.example.rabitmqproject;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.impl.MicrometerMetricsCollector;
import io.micrometer.core.instrument.MeterRegistry;
import org.example.model.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.example.model.Order;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;


    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        Product pr = new Product("test",Math.random() * 100);
        List<Product> list= new ArrayList<Product>();
        Order order = new Order((int) (Math.random()*100),list );
        list.add(pr);
        for (int i = 0 ; i < 100 ; i++){
            rabbitTemplate.convertAndSend(RabitMqProjectApplication.topicExchangeName, "foo.bar.baz", order.toString());
        }

    }

}