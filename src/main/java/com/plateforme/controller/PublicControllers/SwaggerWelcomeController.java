package com.plateforme.controller.PublicControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerWelcomeController {
    @GetMapping("/")
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}
