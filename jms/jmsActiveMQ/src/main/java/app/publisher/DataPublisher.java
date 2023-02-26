package app.publisher;

import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jms.Session;
import javax.jms.Topic;
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
            for (int i = 0 ; i <20; i++) {
                int empId = (int) (Math.random() * 50 + 1);

                Employee emp = Employee.builder().id(empId).name("employeeNAME").role("employeeROLE").build();
                jmsTemplate.convertAndSend(empTopic, emp);
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
}
