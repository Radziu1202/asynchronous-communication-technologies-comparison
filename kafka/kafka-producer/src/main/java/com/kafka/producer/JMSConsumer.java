package com.kafka.producer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer {

    @JmsListener(destination = "your-destination")
    public void receiveMessage(String message) {
        System.out.println("JMS odebral : " + message);
    }
}