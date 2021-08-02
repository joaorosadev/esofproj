package com.example.demo.controllers;

import com.example.demo.models.Faculdade;
import com.example.demo.repositories.FaculdadeRepo;
import com.example.demo.services.FaculdadeService;
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

@WebMvcTest(controllers = FaculdadeController.class)
class FaculdadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaculdadeService faculdadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllFaculdades() throws Exception {

        Faculdade faculdade1 = new Faculdade("UFP"), faculdade2 = new Faculdade("ISEP");
        HashSet<Faculdade> faculdades = new HashSet<>();
        faculdades.add(faculdade1); faculdades.add(faculdade2);

        when(this.faculdadeService.findAll()).thenReturn(faculdades);
        this.mockMvc.perform(
                get("/faculdade")
        ).andExpect(status().isOk());
    }

    @Test
    void createFaculdade() throws Exception {
        Faculdade faculdade1=new Faculdade("Faculdade1");

        String jsonRequest=this.objectMapper.writeValueAsString(faculdade1);

        when(this.faculdadeService.createFaculdade(faculdade1)).thenReturn(Optional.of(faculdade1));

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Faculdade existingFaculdade=new Faculdade("faculdade2");

        when(this.faculdadeService.findByNome("faculdade2")).thenReturn(Optional.of(existingFaculdade));

        String existingFaculdadeJson=this.objectMapper.writeValueAsString(existingFaculdade);

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(existingFaculdadeJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}