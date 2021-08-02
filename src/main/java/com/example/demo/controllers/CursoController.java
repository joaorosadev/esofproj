package com.example.demo.controllers;

import com.example.demo.models.Curso;
import com.example.demo.services.CursoService;
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
@RequestMapping("/curso")
public class CursoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CursoService cursoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Curso>> getAllCursos(){
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.cursoService.findAll());
    }

    //3
    @PostMapping(value="/{faculdade}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso, @PathVariable("faculdade") String faculdadeNome){
        logger.info("Received a Post request.");
        Optional<Curso> optionalCurso = this.cursoService.createCurso(curso,faculdadeNome);
        if(optionalCurso.isPresent()){
            logger.info("Degree created with success.");
            return ResponseEntity.ok(optionalCurso.get());
        }
        throw new CursoController.CursoAlreadyExistsException(curso.getNome());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Curso já existe")
    private class CursoAlreadyExistsException extends RuntimeException {

        public CursoAlreadyExistsException(String name) {
            super("O curso com nome: "+name+" já existe.");
            logger.error("Post request failed.");
        }
    }
}
