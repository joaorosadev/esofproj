package com.example.demo.controllers;

import com.example.demo.models.Curso;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.services.CursoService;
import com.example.demo.services.ExplicadorService;
import com.example.demo.services.FaculdadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorService explicadorService;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllExplicadores() throws Exception {
        Explicador explicador1 = new Explicador("Zé","123");
        Explicador explicador2 = new Explicador("Tó","321");
        HashSet<Explicador> explicadores = new HashSet<>();
        explicadores.add(explicador1); explicadores.add(explicador2);

        when(this.explicadorService.findAll()).thenReturn(explicadores);
        this.mockMvc.perform(
                get("/explicador")
        ).andExpect(status().isOk());
    }

    @Test
    void getExplicadorByNome() throws Exception {
        Explicador explicador=new Explicador("Exp1","123");

        when(this.explicadorService.findByNome(explicador.getNome())).thenReturn(Optional.of(explicador));

        String responseJson=this.mockMvc.perform(
                get("/explicador/Exp1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Explicador responseExplicador=this.objectMapper.readValue(responseJson,Explicador.class);
        responseExplicador.setNome("Exp2");
        assertNotEquals(explicador,responseExplicador);

        this.mockMvc.perform(
                get("/explicador/Exp2")
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    void createExplicador() throws Exception {
        Explicador explicador1 = new Explicador("Explicador1","321");

        String jsonRequest = this.objectMapper.writeValueAsString(explicador1);

        when(this.explicadorService.createExplicador(explicador1)).thenReturn(Optional.of(explicador1));

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );

        Explicador existingExplicador = new Explicador("Explicador2","12345");

        when(this.explicadorService.findByNome("Explicador2")).thenReturn(Optional.of(existingExplicador));

        String existingExplicadorJson=this.objectMapper.writeValueAsString(existingExplicador);

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(existingExplicadorJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void putExplicadorCurso() throws Exception {
        Explicador explicador = new Explicador("José","123");
        Curso curso = new Curso("Psicologia");
        curso.addExplicador(explicador);

        when(this.explicadorService.putExplicadorCurso(explicador,curso.getNome())).thenReturn(Optional.of(explicador));

        String jsonRequest = this.objectMapper.writeValueAsString(explicador);

        String responseJson=this.mockMvc.perform(
          put("/explicador/Psicologia").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        System.out.println(responseJson);

        this.mockMvc.perform(
                put("/explicador/Anatomia").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isBadRequest()
        );

        assertEquals("Psicologia",explicador.getCurso().getNome());
    }

    @Test
    void filterExplicadores() throws Exception {
        Set<Explicador> explicadores=new HashSet<>();
        explicadores.add(new Explicador("expl1","12345"));
        explicadores.add(new Explicador("expl2","12345"));
        explicadores.add(new Explicador("expl3","12345"));
        Map<String,String> params=new HashMap<>();
        params.put("curso","curso1");
        params.put("dia","segunda");


        when(this.explicadorService.filterExplicadores(params)).thenReturn(explicadores);

        String jsonResult=this.mockMvc.perform(get("/explicador/search?curso=curso1&dia=segunda"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(jsonResult);
    }
}