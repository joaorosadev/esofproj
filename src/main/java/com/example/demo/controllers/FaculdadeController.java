package com.example.demo.controllers;

import com.example.demo.models.Faculdade;
import com.example.demo.services.FaculdadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeService faculdadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFaculdades(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

    //2 (FUNCIONA NO POSTMAN MAS TESTE FALHA)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade){
        Optional<Faculdade> optionalFaculdade = this.faculdadeService.createFaculdade(faculdade);
        if(optionalFaculdade.isPresent()){
            return ResponseEntity.ok(optionalFaculdade.get());
        }
        throw new FaculdadeController.FaculdadeAlreadyExistsException(faculdade.getNome());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Faculdade já existe")
    private class FaculdadeAlreadyExistsException extends RuntimeException {

        public FaculdadeAlreadyExistsException(String name) {
            super("A faculdade com nome: "+name+" já existe.");
        }
    }

    //Este metodo tem de verificar se a fac já existe na base de dados, se existir devolve ResponseEntity.badRequest()
    /*@PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade){
        System.out.println(faculdade);
        return ResponseEntity.ok().build();
    }*/
}
