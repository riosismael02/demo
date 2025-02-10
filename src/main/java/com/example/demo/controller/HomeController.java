package com.example.demo.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        // Puedes agregar atributos al modelo si es necesario
        model.addAttribute("mensaje", "Bienvenido a la página principal");
        return "index";
    }
}
