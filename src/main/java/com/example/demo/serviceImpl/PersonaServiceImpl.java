package com.example.demo.serviceImpl;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.search.search;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public List<Persona> buscarPorNombre(String nombre) {
        List<Persona> todasPersonas = personaRepository.findAll();
        return todasPersonas.stream()
                .filter(persona -> search.calcularDistancia(persona.getNombre(), nombre) <= 2 ||
                        persona.getNombre().contains(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Persona> buscarPorDni(String dni) {
        List<Persona> todasPersonas = personaRepository.findAll();
        return todasPersonas.stream()
                .filter(persona -> search.calcularDistancia(persona.getDni(), dni) <= 2 ||
                        persona.getDni().contains(dni))
                .collect(Collectors.toList());
    }
    
}
