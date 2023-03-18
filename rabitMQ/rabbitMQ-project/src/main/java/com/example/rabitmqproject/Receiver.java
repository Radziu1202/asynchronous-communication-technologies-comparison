package com.example.rabitmqproject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "test_queue", ackMode = "MANUAL")
    public void listen(@Payload Envelope message, Channel channel) throws IOException {
        try {
            // process the message
            System.out.println("Received Message: " + message);
            // acknowledge the message
            channel.basicAck(message.getDeliveryTag(), false);
        } catch (Exception ex) {
            // reject the message if it fails
            channel.basicReject(message.getDeliveryTag(), true);
        }
    }

}