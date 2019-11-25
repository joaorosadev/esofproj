package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaculdadeTest {

    @Test
    void addCurso() {

        Faculdade faculdade = new Faculdade("UFP");
        Curso curso = new Curso("Engenharia Informática");

        assertEquals(0,faculdade.getCursos().size());
        faculdade.addCurso(curso);

        assertEquals(1,faculdade.getCursos().size());
        assertNotEquals(0,faculdade.getCursos().size());

        assertEquals(curso.getFaculdade(),faculdade);
    }
}