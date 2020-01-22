package com.example.demo.controllers;

import com.example.demo.models.Cadeira;
import com.example.demo.models.Explicador;
import com.example.demo.services.CadeiraService;
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

@WebMvcTest(controllers = CadeiraController.class)
class CadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadeiraService cadeiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCadeira() throws Exception{
        Cadeira cadeira = new Cadeira("cadeira");

        String jsonRequest = this.objectMapper.writeValueAsString(cadeira);

        when(this.cadeiraService.createCadeira(cadeira,"course")).thenReturn(Optional.of(cadeira));

        this.mockMvc.perform(
                post("/cadeira/course").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(status().isOk());
    }
}