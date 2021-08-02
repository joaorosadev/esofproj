package com.example.demo.controllers;

import com.example.demo.models.Explicador;
import com.example.demo.services.CursoService;
import com.example.demo.services.ExplicadorService;
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
import java.util.Set;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExplicadorService explicadorService;

    @Autowired
    private CursoService cursoService;

    //Pedidos HTTP
    //7
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.explicadorService.findAll());
    }

    //8
    @RequestMapping(value = "/{nome}",method = RequestMethod.GET)
    public ResponseEntity<Explicador> getExplicadorByNome(@PathVariable("nome") String nome) throws NoExplicadorNomeException {
        this.logger.info("Received a get request");

        StringBuilder sb = new StringBuilder(nome);
        sb.setCharAt(0,Character.toUpperCase(nome.charAt(0)));

        Optional<Explicador> optionalExplicador=this.explicadorService.findByNome(sb.toString());
        if(optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorNomeException(nome);
    }

    //1
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){
        logger.info("Received a post request.");
        Optional<Explicador> optionalExplicador = this.explicadorService.createExplicador(explicador);
        if(optionalExplicador.isPresent()){
            logger.info("Explainer created with success.");
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new ExplicadorAlreadyExistsException(explicador.getNome());
    }

    ////5
    @PutMapping(value="/{curso}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicadorCurso(@RequestBody Explicador explicador,@PathVariable("curso") String cursoName){
        logger.info("Received a post request.");
        Optional<Explicador> optionalExplicador = this.explicadorService.putExplicadorCurso(explicador,cursoName);
        if(optionalExplicador.isPresent()){
            logger.info("Explainer created and associated with degree successfully.");
            return ResponseEntity.ok(optionalExplicador.get());
        }
        if(this.cursoService.findByNome(cursoName).isEmpty()) throw new CursoNaoExisteException(cursoName);
        throw new ExplicadorNaoExisteException();
    }

    //6
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicadorDisp(@RequestBody Explicador explicador){
        logger.info("Received a put request.");
        Optional<Explicador> optionalExplicador = this.explicadorService.putExplicadorDisp(explicador);
        if(optionalExplicador.isPresent()){
            logger.info("Explainer schedules edited successfully.");
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw  new ExplicadorNaoExisteException();
    }

    //http://localhost:8080/explicador/search?curso=Psicologia&dia=quarta&hora_inicio=1400&hora_fim=1600
    //9
    @GetMapping(value="/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> searchExplicadores(@RequestParam Map<String,String> searchParams){
        logger.info("Received a Get request.");
        return ResponseEntity.ok(this.explicadorService.filterExplicadores(searchParams));
    }


    //Error messages
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="O Explicador especificado não existe.")
    private class NoExplicadorNomeException extends RuntimeException {

        public NoExplicadorNomeException(String cursoNome) {
            super("Não existe nenhum explicador com nome \""+cursoNome+"\".");
        }
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "O Curso especificado não existe.")
    private class CursoNaoExisteException extends RuntimeException{
        public CursoNaoExisteException(String nome) {
            super("Curso \"" + nome+"\" não existe.");
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Explicador já existe")
    private class ExplicadorAlreadyExistsException extends RuntimeException {

        public ExplicadorAlreadyExistsException(String name) {
            super("O explicador com nome: "+name+" já existe.");
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Não existe explicador com esssa disponibilidade.")
    private class ExplicadorNaoExisteException extends RuntimeException{

        public ExplicadorNaoExisteException(){
            super("Não existe explicador com esssa disponibilidade.");
        }
    }
}
