package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

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
        Estudante estudante = new Estudante("António");
        Explicador explicador = new Explicador("Fábio");
        Cadeira cadeira = new Cadeira("LP");
        Disponibilidade disp = new Disponibilidade(DayOfWeek.FRIDAY,12,14);

        explicador.addCadeira(cadeira);
        explicador.addDisponibilidade(disp);

        Explicacao explicacao = estudante.marcarExplicacao(LocalDateTime.of(2019,11,28,12,0),cadeira);
        assertNotNull(explicacao);
    }
}