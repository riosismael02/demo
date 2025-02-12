package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/cargar")
    public String cargarPersona(@RequestParam("nombre") String nombre,
                                @RequestParam("apellido") String apellido,
                                @RequestParam("foto") MultipartFile file,
                                @RequestParam("dni") String dni) {
        try {
            Persona persona = new Persona(nombre, apellido, file.getBytes(), dni);
            personaService.guardarPersona(persona);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";  // Devuelve una vista de error si ocurre una excepción
        }
        return "redirect:/personas/listado";
    }

    @GetMapping("/vista/{id}")
    public String vistaPreviaPersona(@PathVariable("id") Long id, Model model) {
        Optional<Persona> persona = personaService.obtenerPersonaPorId(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            return "carnet";
        } else {
            return "redirect:/personas/listar";
        }
    }

    @GetMapping("/buscar")
    public String buscarPersonas(@RequestParam("nombre") String nombre,
            @RequestParam("dni") String dni,
            Model model) {
        List<Persona> personas = null;
        if (!nombre.isEmpty()) {
            personas = personaService.buscarPorNombre(nombre);
        } else if (!dni.isEmpty()) {
            personas = personaService.buscarPorDni(dni);
        }
        model.addAttribute("personas", personas);
        return "resultados-busqueda";
    }
}