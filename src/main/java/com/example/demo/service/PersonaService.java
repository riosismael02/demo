package com.example.demo.service;

import com.example.demo.entity.Persona;

import java.util.List;
import java.util.Optional;


public interface PersonaService {
    Persona guardarPersona(Persona persona);

    Optional<Persona> obtenerPersonaPorId(Long id);

    List<Persona> obtenerTodasLasPersonas();

    void eliminarPersona(Long id);
}

