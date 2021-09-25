package be.dog.d.steven.employee;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleService {

    @ApiOperation(value = "Get the default schedule for employees",
        notes = "All employees share the same schedule.")
    @RequestMapping(value = "/employeev1/schedule", method = RequestMethod.GET)
    public String getSchedule1() {

        return "Your schedule is M-F 9-5";
    }

    @ApiOperation(value = "Get the default schedule for employees",
            notes = "All employees share the same schedule.")
    @RequestMapping(value = "/employeev2/schedule", method = RequestMethod.GET)
    public String getSchedule2() {

        return "Your schedule is M-W 9-9";
    }

    @ApiOperation(value = "Get the default schedule for employees",
            notes = "All employees share the same schedule.")
    @RequestMapping(value = "/employeev3/schedule", method = RequestMethod.GET)
    public String getSchedule3() {

        return "Your schedule is T-F 8-6";
    }
}