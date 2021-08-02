package com.example.demo.services;

import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExplicadorServiceTest {

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @Autowired
    private ExplicadorService explicadorService;

    @Test
    void filterExplicadores() {

        Set<Explicador> explicadores=new HashSet<>();
        Explicador explicador=new Explicador();
        explicador.addDisponibilidade(new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(8,0),LocalTime.of(
        10,0)));

        explicadores.add(explicador);

        when(this.explicadorRepo.findAll()).thenReturn(explicadores);

        Map<String,String> params=new HashMap<>();
        params.put("dia","segunda");

        assertEquals(1,this.explicadorService.filterExplicadores(params).size());

        Map<String,String> params2=new HashMap<>();
        params2.put("dia","terca");

        assertEquals(0,this.explicadorService.filterExplicadores(params2).size());
    }
}