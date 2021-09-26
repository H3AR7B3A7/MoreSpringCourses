package be.dog.d.steven.conferenceapp.controller;

import be.dog.d.steven.conferenceapp.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {
    
    @GetMapping("/user")
    public User getUser(
            @RequestParam(value="firstname", defaultValue = "Steven") String firstname,
            @RequestParam(value = "lastname", defaultValue = "D'Hondt") String lastname,
            @RequestParam(value = "age", defaultValue = "32") Integer age
    ) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        return user;
    }
    
    @PostMapping("/user")
    public User postUser(User user) {
        System.out.println("User firstname:" + user.getFirstname());
        return user;
    }
    
    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.sendRedirect("/conference/user");
    }
}