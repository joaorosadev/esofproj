package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadeTest {

    @Test
    void setExplicador() {
        Disponibilidade disponibilidade = new Disponibilidade(DayOfWeek.WEDNESDAY,12,13);
        Explicador explicador = new Explicador("Roberto");

        assertNull(disponibilidade.getExplicador());
        disponibilidade.setExplicador(explicador);
        assertNotNull(disponibilidade.getExplicador());
    }
}