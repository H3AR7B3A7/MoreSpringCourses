package com.pluralsight.security.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = {"/portfolio/**"})
	public String portfolio() {
		return "forward:/";
	}
}