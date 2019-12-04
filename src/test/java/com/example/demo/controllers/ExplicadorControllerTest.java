package com.example.demo.controllers;

import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.services.ExplicadorService;
import com.example.demo.services.FaculdadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorService explicadorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllExplicadores() throws Exception {
        Explicador explicador1 = new Explicador("Zé");
        Explicador explicador2 = new Explicador("Tó");
        HashSet<Explicador> explicadores = new HashSet<>();
        explicadores.add(explicador1); explicadores.add(explicador2);

        when(this.explicadorService.findAll()).thenReturn(explicadores);
        this.mockMvc.perform(
                get("/explicador")
        ).andExpect(status().isOk());
    }

    @Test
    void getExplicadorByNome() throws Exception {
        Explicador explicador=new Explicador("exp1");

        when(this.explicadorService.findByNome(explicador.getNome())).thenReturn(Optional.of(explicador));

        String responseJson=this.mockMvc.perform(
                get("/explicador/exp1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        //System.out.println(responseJson);

        Explicador responseExplicador=this.objectMapper.readValue(responseJson,Explicador.class);
        responseExplicador.setNome("exp2");
        assertEquals(explicador,responseExplicador);

        this.mockMvc.perform(
                get("/explicador/exp2")
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    void createExplicador() {
    }

    @Test
    void putExplicadorCurso() {
    }

    @Test
    void searchExplicador() {
    }
}