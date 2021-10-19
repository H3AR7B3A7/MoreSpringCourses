package be.dog.d.steven.conferenceapp.controller;

import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@Valid @ModelAttribute("registration") Registration registration, BindingResult result) {
        
        if (result.hasErrors()) {
            System.out.println("There were errors...");
            return "registration";
        } else {
            registrationService.addRegistration(registration);
        }
        
        System.out.println("Registration: " + registration.getName());
        return "redirect:registration";
    }
}