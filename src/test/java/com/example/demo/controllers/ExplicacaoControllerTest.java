package com.example.demo.controllers;

import com.example.demo.models.Explicacao;
import com.example.demo.services.CadeiraService;
import com.example.demo.services.ExplicacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoService explicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExplicacao() throws Exception{
        Explicacao explicacao = new Explicacao();

        String jsonRequest = this.objectMapper.writeValueAsString(explicacao);

        when(this.explicacaoService.createExplicacao(explicacao)).thenReturn(Optional.of(explicacao));

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(status().isOk());
    }
}