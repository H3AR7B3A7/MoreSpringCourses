package be.dog.d.steven.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleService {

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String getSchedule() {

        return "Your schedule is M-F 9-5";
    }
}