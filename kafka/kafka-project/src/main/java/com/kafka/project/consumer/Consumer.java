package com.kafka.project.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {


    @KafkaListener(id = "topic1", topics = "inbound")
    public void listen(String in) {
        System.out.println("AAAAAAAAAAAAAAAAAAA    "+in+"AAAAAAAAAAAAAAAAAAA    ");
    }

}
