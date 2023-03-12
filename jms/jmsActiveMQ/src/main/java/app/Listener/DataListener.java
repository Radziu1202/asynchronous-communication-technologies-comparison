package app.Listener;

import lombok.extern.slf4j.Slf4j;
import app.model.Employee;
import org.example.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataListener {
    @JmsListener(destination = "${emp.jms.topic}", containerFactory = "empJmsContFactory")
    public void getListener1(String ord) {
        log.info("Order listener1: " + ord);
    }
}