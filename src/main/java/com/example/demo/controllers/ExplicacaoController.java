package com.example.demo.controllers;

import com.example.demo.models.Explicacao;
import com.example.demo.services.ExplicacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExplicacaoService explicacaoService;

    //10 FALTA TESTAR NO POSTMAN
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicacao> createExplicacao(@RequestBody Explicacao explicacao){
        Optional<Explicacao> optionalExplicacao = this.explicacaoService.createExplicacao(explicacao);
        if(optionalExplicacao.isPresent()){
            return ResponseEntity.ok(optionalExplicacao.get());
        }
        throw new ExplicacaoController.ExplicacaoAlreadyExistsException();
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Explicador já existe")
    private class ExplicacaoAlreadyExistsException extends RuntimeException {

        public ExplicacaoAlreadyExistsException() {
            super("Explicacao já existe.");
        }
    }
}
