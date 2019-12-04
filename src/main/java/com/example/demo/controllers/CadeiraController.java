package com.example.demo.controllers;

import com.example.demo.models.Cadeira;
import com.example.demo.services.CadeiraService;
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
@RequestMapping("/cadeira")
public class CadeiraController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CadeiraService cadeiraService;

    //4
    @PostMapping(value="/{curso}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createCadeira(@RequestBody Cadeira cadeira, @PathVariable("curso") String cursoNome){
        Optional<Cadeira> optionalCadeira = this.cadeiraService.createCadeira(cadeira,cursoNome);
        if(optionalCadeira.isPresent()){
            return ResponseEntity.ok(optionalCadeira.get());
        }
        throw new CadeiraController.CadeiraAlreadyExistsException(cadeira.getNome());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Cadeira já existe")
    private class CadeiraAlreadyExistsException extends RuntimeException {

        public CadeiraAlreadyExistsException(String name) {
            super("O cadeira com nome: "+name+" já existe.");
        }
    }
}
