package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {

	@GetMapping("/portfolio")
	public String portfolio() {
		return "portfolio";
	}
	
	@GetMapping
	public String portfolio2() {
        return "redirect:/portfolio";
    }
}