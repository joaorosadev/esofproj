package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadeTest {

    @Test
    void setExplicador() {
        LocalTime hmIn = LocalTime.of(12,0), hmFim = LocalTime.of(14,0);

        Disponibilidade disponibilidade = new Disponibilidade(DayOfWeek.WEDNESDAY,hmIn,hmFim);
        Explicador explicador = new Explicador("Roberto");

        assertNull(disponibilidade.getExplicador());
        disponibilidade.setExplicador(explicador);
        assertNotNull(disponibilidade.getExplicador());
    }
}