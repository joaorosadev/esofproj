package com.example.demo.controllers;

import com.example.demo.models.Faculdade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    //Este metodo tem de verificar se a fac já existe na base de dados, se existir devolve ResponseEntity.badRequest()
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade){
        System.out.println(faculdade);
        return ResponseEntity.ok().build();
    }
}
