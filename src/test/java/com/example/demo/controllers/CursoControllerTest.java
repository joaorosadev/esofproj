package com.example.demo.controllers;

import com.example.demo.models.Cadeira;
import com.example.demo.models.Curso;
import com.example.demo.models.Explicador;
import com.example.demo.services.CadeiraService;
import com.example.demo.services.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCursos() throws Exception {
        Curso curso1 = new Curso("curso1");
        Curso curso2 = new Curso("curso2");
        HashSet<Curso> cursos = new HashSet<>();
        cursos.add(curso1); cursos.add(curso2);

        when(this.cursoService.findAll()).thenReturn(cursos);
        this.mockMvc.perform(
                get("/curso")
        ).andExpect(status().isOk());
    }

    @Test
    void createCurso() throws Exception {
        Curso curso = new Curso("curso");

        String jsonRequest = this.objectMapper.writeValueAsString(curso);

        when(this.cursoService.createCurso(curso,"facname")).thenReturn(Optional.of(curso));

        this.mockMvc.perform(
                post("/curso/facname").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(status().isOk());

        Curso existingCurso = new Curso("curso2");

        when(this.cursoService.findByNome("curso2")).thenReturn(Optional.of(existingCurso));

        String existingCursoJson=this.objectMapper.writeValueAsString(existingCurso);

        this.mockMvc.perform(
                post("/curso/facname").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}