package com.example.demo.controllers;

import com.example.demo.models.Explicador;
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

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExplicadorService explicadorService;

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
        Optional<Explicador> optionalExplicador = this.explicadorService.createExplicador(explicador);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new ExplicadorAlreadyExistsException(explicador.getNome());
    }

    //5 e 6 FALTAM TESTAR NO POSTMAN,para o 5 temos de passar o id
    //???????????????
    @PutMapping(value="/put/{curso}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicadorCurso(@RequestBody Explicador explicador,@PathVariable("curso") String cursoName){
        Optional<Explicador> optionalExplicador = this.explicadorService.putExplicadorCurso(explicador,cursoName);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new ExplicadorAlreadyExistsException(explicador.getNome());
    }
    //??????????????

    //9 FALTA TESTAR NO POSTMAN
    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> searchExplicador(@RequestParam Map<String,String> searchParam){
        String cursoName = searchParam.get("curso");
        String diaName = searchParam.get("dia");
        int horaInicio = Integer.parseInt(searchParam.get("hora_inicio"));
        int horaFim = Integer.parseInt(searchParam.get("hora_fim"));

        Optional<Explicador> optionalExplicador = this.explicadorService.searchExplicador(cursoName,diaName,horaInicio,horaFim);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new ExplicadorNaoExisteException();
    }


    //Error messages
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="O Explicador especificado não existe.")
    private class NoExplicadorNomeException extends RuntimeException {

        public NoExplicadorNomeException(String nome) {
            super("Não existe nenhum explicador com nome \""+nome+"\".");
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
