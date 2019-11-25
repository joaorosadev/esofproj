package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void addCadeira() {
        Curso curso = new Curso("Engenharia Informática");
        Cadeira cadeira = new Cadeira("LP");

        assertEquals(0,curso.getCadeiras().size());
        curso.addCadeira(cadeira);

        assertEquals(1,curso.getCadeiras().size());
        assertEquals(cadeira.getCurso(),curso);
    }

    @Test
    void setFaculdade() {
        Curso curso = new Curso ("Eng. Inf.");
        Faculdade faculdade = new Faculdade("UFP");

        curso.setFaculdade(faculdade);

        assertEquals(faculdade, curso.getFaculdade());
    }
}