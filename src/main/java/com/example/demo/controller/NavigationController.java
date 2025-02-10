package com.example.demo.controller;
import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas/cargar")
    public String mostrarFormularioCarga() {
        return "cargar-personas";
    }

    @GetMapping("/personas/renovar")
    public String mostrarFormularioRenovacion() {
        return "renovar-carnet";
    }

    @GetMapping("/personas/listar")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        model.addAttribute("personas", personas);
        return "listar-personas";
    }

    @GetMapping("/personas/buscar")
    public String mostrarFormularioBusqueda() {
        return "buscar";
    }
}

