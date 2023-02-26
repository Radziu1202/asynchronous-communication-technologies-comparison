package app.controller;

import app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Session;
import javax.jms.Topic;
import java.util.Objects;

@Slf4j
@RestController
public class DataController {

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * Create new employee
     *
     * @param employee
     * @return ResponseEntity
     */
    @PostMapping("/employee")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee) {
        try {
            Topic empTopic = Objects.requireNonNull(jmsTemplate.getConnectionFactory()).createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE).createTopic("EmpTopic");
            int empId = (int)(Math.random() * 50 + 1);
            Employee emp = Employee.builder().id(empId).name(employee.getName()).role(employee.getRole()).build();
            log.info("Sending Employee Object: " + emp);
            jmsTemplate.convertAndSend(empTopic, emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}