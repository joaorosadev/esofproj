package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadeiraTest {

    @Test
    void setCurso() {
        Cadeira cadeira = new Cadeira("LP");
        Curso curso = new Curso("Eng. Inf.");

        cadeira.setCurso(curso);

        assertEquals(curso,cadeira.getCurso());
    }

    @Test
    void setExplicador() {
        Cadeira cadeira = new Cadeira("LP");
        Explicador explicador = new Explicador("Zé");

        cadeira.setExplicador(explicador);

        assertEquals(explicador,cadeira.getExplicador());
    }
}