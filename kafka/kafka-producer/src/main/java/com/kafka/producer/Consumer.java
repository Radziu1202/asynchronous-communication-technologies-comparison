package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {


    @KafkaListener(topics = "topic1")
    public void listen(ConsumerRecord<String,String> in) {
        log.info("Consumed: {}", in);
    }

}
