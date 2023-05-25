package com.kafka.producer;

import jakarta.jms.Topic;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;

@Component
public class Receiver {
    @Autowired
    JmsTemplate jmsTemplate;




    @RabbitListener(queues = "queueDirect", ackMode = "MANUAL")
    public void listen(String  in) throws IOException {
        try {
            System.out.println("Rabbitmq Odebrał i wysyla na JMS'a " + in);

            // process the message
            jmsTemplate.convertAndSend("your-destination", in);

        } catch (Exception ex) {
            // reject the message if it fails
            throw ex;
        }
    }

}