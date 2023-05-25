package com.kafka.producer;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public RabbitMQSender( RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String or) throws Exception {
        System.out.println("Sending Rabbitmessage...");

        rabbitTemplate.convertAndSend("exchangeDirect","key", or);

    }

}