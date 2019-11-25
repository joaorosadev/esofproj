package com.example.demo.controllers;

import com.example.demo.models.Faculdade;
import com.example.demo.repositories.FaculdadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFaculdades(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.faculdadeRepo.findAll());
    }

    //Este metodo tem de verificar se a fac já existe na base de dados, se existir devolve ResponseEntity.badRequest()
    /*@PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade){
        System.out.println(faculdade);
        return ResponseEntity.ok().build();
    }*/
}
