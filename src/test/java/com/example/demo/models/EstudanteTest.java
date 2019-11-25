package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudanteTest {

    @Test
    void addExplicacao() {
        Estudante estudante = new Estudante("Fernandina");
        Explicacao explicacao = new Explicacao();

        assertEquals(0,estudante.getExplicacoes().size());
        estudante.addExplicacao(explicacao);
        assertEquals(1,estudante.getExplicacoes().size());
    }

    @Test
    void marcarExplicacao() {
    }
}