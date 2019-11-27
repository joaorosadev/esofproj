package com.example.demo.controllers;

import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicadorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.explicadorRepo.findAll());
    }

    /*@RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Explicador> getExplicadorById(@PathVariable("id") Long id) throws NoExplicadorExcpetion {
        this.logger.info("Received a get request");

        Optional<Explicador> optionalExplicador=this.explicadorRepo.findById(id);
        if(optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorExcpetion(id);
    }*/

    @RequestMapping(value = "/{nome}",method = RequestMethod.GET)
    public ResponseEntity<Explicador> getExplicadorByNome(@PathVariable("nome") String nome) throws NoExplicadorNomeExcpetion {
        this.logger.info("Received a get request");

        StringBuilder sb = new StringBuilder(nome);
        sb.setCharAt(0,Character.toUpperCase(nome.charAt(0)));

        Optional<Explicador> optionalExplicador=this.explicadorRepo.findByNome(sb.toString());
        if(optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorNomeExcpetion(nome);
    }


    /*@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="O Explicador especificado não existe.")
    private class NoExplicadorExcpetion extends RuntimeException {

        public NoExplicadorExcpetion(Long id) {
            super("Não existe nenhum explicador com id "+id+".");
        }
    }*/

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="O Explicador especificado não existe.")
    private class NoExplicadorNomeExcpetion extends RuntimeException {

        public NoExplicadorNomeExcpetion(String nome) {
            super("Não existe nenhum explicador com nome \""+nome+"\".");
        }
    }
}
