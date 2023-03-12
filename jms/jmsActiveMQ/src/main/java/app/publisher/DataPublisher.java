package app.publisher;

import app.model.Employee;
import org.example.model.Order;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jms.Session;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class DataPublisher implements CommandLineRunner {
    @Autowired
    JmsTemplate jmsTemplate;




    @Override
    public void run(String... args) throws Exception {
        try {
            Topic empTopic = Objects.requireNonNull(jmsTemplate.getConnectionFactory()).createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE).createTopic("EmpTopic");
            Product pr = new Product("test",Math.random() * 100);
            List<Product> list= new ArrayList<Product>();
            list.add(pr);
            Order order = new Order((int) (Math.random()*100),list );
            for (int i = 0 ; i <20; i++) {
                jmsTemplate.convertAndSend(empTopic, order.toString());
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
}
