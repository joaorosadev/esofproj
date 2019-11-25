package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorTest {

    @Test
    void addCadeira() {
        Explicador explicador = new Explicador("Zé");
        Cadeira cadeira = new Cadeira("LP");

        assertEquals(0,explicador.getCadeiras().size());
        explicador.addCadeira(cadeira);

        assertEquals(1,explicador.getCadeiras().size());
        assertEquals(cadeira.getExplicador(),explicador);
    }

    @Test
    void addDisponibilidade() {
        Explicador explicador = new Explicador("Alves");
        Disponibilidade disponibilidade = new Disponibilidade(DayOfWeek.WEDNESDAY,15,18);

        assertEquals(0,explicador.getDisponibilidades().size());
        explicador.addDisponibilidade(disponibilidade);

        assertEquals(1,explicador.getDisponibilidades().size());
        assertEquals(disponibilidade.getExplicador(),explicador);

    }

    @Test
    void addExplicacao() {
        Explicador explicador = new Explicador("Alves");
        Explicacao explicacao = new Explicacao();

        assertEquals(0,explicador.getExplicacoes().size());
        explicador.addExplicacao(explicacao);

        assertEquals(1,explicador.getExplicacoes().size());
        assertEquals(explicacao.getExplicador(),explicador);

    }
}