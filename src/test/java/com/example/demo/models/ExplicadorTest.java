package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorTest {

    @Test
    void addCadeira() {
        Explicador explicador = new Explicador("Zé","123");
        Cadeira cadeira = new Cadeira("LP");

        assertEquals(0,explicador.getCadeiras().size());
        explicador.addCadeira(cadeira);

        assertEquals(1,explicador.getCadeiras().size());
        assertEquals(cadeira.getExplicador(),explicador);
    }

    @Test
    void addDisponibilidade() {
        Explicador explicador = new Explicador("Alves","123");
        LocalTime hmIn = LocalTime.of(15,0), hmFim = LocalTime.of(18,0);
        Disponibilidade disponibilidade = new Disponibilidade(DayOfWeek.WEDNESDAY,hmIn,hmFim);

        assertEquals(0,explicador.getDisponibilidades().size());
        explicador.addDisponibilidade(disponibilidade);

        assertEquals(1,explicador.getDisponibilidades().size());
        assertEquals(disponibilidade.getExplicador(),explicador);

    }

    @Test
    void addExplicacao() {
        Explicador explicador = new Explicador("Alves","123");
        Explicacao explicacao = new Explicacao();

        assertEquals(0,explicador.getExplicacoes().size());
        explicador.addExplicacao(explicacao);

        assertEquals(1,explicador.getExplicacoes().size());
        assertEquals(explicacao.getExplicador(),explicador);

    }
}