package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExplicacaoTest {

    @Test
    void setExplicador() {
        Explicacao explicacao = new Explicacao();
        Explicador explicador = new Explicador("Lelo","123");

        assertNull(explicacao.getExplicador());
        explicacao.setExplicador(explicador);
        assertNotNull(explicacao.getExplicador());
    }

    @Test
    void setEstudante() {
        Explicacao explicacao = new Explicacao();
        Estudante estudante = new Estudante("Tó","toze");

        assertNull(explicacao.getEstudante());
        explicacao.setEstudante(estudante);
        assertNotNull(explicacao.getEstudante());
    }
}