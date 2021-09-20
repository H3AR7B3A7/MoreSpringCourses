package be.dog.d.steven.conferenceapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @GetMapping("registration")
    public String registration(Map<String, Object> model) {
        return "registration";
    }
}