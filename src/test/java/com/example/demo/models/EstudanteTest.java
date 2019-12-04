package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        LocalTime hmIn = LocalTime.of(12,0), hmFim = LocalTime.of(14,0);
        Disponibilidade disp = new Disponibilidade(DayOfWeek.FRIDAY,hmIn,hmFim);

        explicador.addCadeira(cadeira);
        explicador.addDisponibilidade(disp);

        //Marcar em disponibilidade livre
        Explicacao explicacao1 = estudante.marcarExplicacao(LocalDateTime.of(2019,11,29,12,0),cadeira);
        assertNotNull(explicacao1);

        //Marcar fora da disponibilidade
        Explicacao explicacao2 = estudante.marcarExplicacao(LocalDateTime.of(2019,11,29,1,0), cadeira);
        assertNull(explicacao2);

        //Marcar em disponibilidade, mas ocupada
        Explicacao explicacao3 = estudante.marcarExplicacao(LocalDateTime.of(2019,11,29,12,0),cadeira);
        assertNull(explicacao3);
    }

}