package com.example.demo.services.filters;

import com.example.demo.models.Explicador;

import java.util.Set;

public interface ExplicadorFilterI {
    Set<Explicador> filter(Set<Explicador> explicadors);
}
