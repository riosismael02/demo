package com.example.demo.controller;


import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/cargar")
    public String mostrarFormularioCarga() {
        return "cargar-personas";
    }

    @PostMapping("/cargar")
    public String cargarPersona(@RequestParam("nombre") String nombre,
                                @RequestParam("apellido") String apellido,
                                @RequestParam("foto") MultipartFile file) {
        try {
            Persona persona = new Persona(nombre, apellido, file.getBytes());
            personaService.guardarPersona(persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/personas/listar";
    }

    @GetMapping("/listar")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        model.addAttribute("personas", personas);
        return "listar-personas";
    }
}