package com.example.demo.controller;
import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas/carga")
    public String mostrarFormularioCarga() {
        return "cargar-personas";
    }

    @GetMapping("/personas/renovacion")
    public String mostrarFormularioRenovacion() {
        return "renovar-carnet";
    }

    @GetMapping("/personas/listado")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        model.addAttribute("personas", personas);
        return "listar-personas";
    }

    @GetMapping("/personas/busqueda")
    public String mostrarFormularioBusqueda() {
        return "buscar";
    }
}

