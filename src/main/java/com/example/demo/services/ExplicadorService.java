package com.example.demo.services;

import com.example.demo.models.Curso;
import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.DisponibilidadeRepo;
import com.example.demo.repositories.ExplicadorRepo;
import com.example.demo.repositories.FaculdadeRepo;
import com.example.demo.services.filters.FilterExplicadorService;
import com.example.demo.services.filters.FilterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ExplicadorService {

    @Autowired
    private ExplicadorRepo explicadorRepo;
    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    private FilterExplicadorService filterExplicadorService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DisponibilidadeRepo disponibilidadeRepo;

    public Set<Explicador> findAll() {
        Set<Explicador> explicadores = new HashSet<>();
        for(Explicador explicador: this.explicadorRepo.findAll()){
            explicadores.add(explicador);
        }
        return explicadores;
    }

    //1
    public Optional<Explicador> createExplicador(Explicador explicador) {
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByNome(explicador.getNome());
        if(!optionalExplicador.isPresent()){
            return Optional.of(this.explicadorRepo.save(explicador));
        } else
            return Optional.empty();
    }

    //5
    public Optional<Explicador> putExplicadorCurso(Explicador explicador, String cursoName){
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByNome(explicador.getNome());
        Optional<Curso> cursoOptional = this.cursoService.findByNome(cursoName);
        if(optionalExplicador.isPresent() && cursoOptional.isPresent()){
            optionalExplicador.get().setCurso(cursoOptional.get());
            cursoOptional.get().addExplicador(optionalExplicador.get());
            this.cursoService.save(cursoOptional.get());
            return optionalExplicador;
        } else
            return Optional.empty();
    }

    //6
    public Optional<Explicador> putExplicadorDisp(Explicador explicador){
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByNome(explicador.getNome());
        if(optionalExplicador.isPresent()){
           Explicador explicadorExisting=optionalExplicador.get();
            System.out.println(explicador);

            Set<Disponibilidade> disponibilidades=explicadorExisting.getDisponibilidades();
            explicadorExisting.getDisponibilidades().clear();

           for(Disponibilidade disponibilidade:disponibilidades){
               disponibilidade.setExplicador(null);
               this.disponibilidadeRepo.delete(disponibilidade);
           }
           for(Disponibilidade disponibilidade: explicador.getDisponibilidades()){
                System.out.println(disponibilidade);
                explicadorExisting.addDisponibilidade(new Disponibilidade(disponibilidade));
           }
           return Optional.of(this.explicadorRepo.save(explicadorExisting));
        }
        return Optional.empty();
    }

    public Set<Explicador> filterExplicadores(Map<String, String> searchParams) {

        FilterObject filterObject=new FilterObject(searchParams);
        Set<Explicador> explicadores = this.findAll();

        return this.filterExplicadorService.filter(explicadores,filterObject);
    }

    public Optional<Explicador> findByNome(String nome){
        return this.explicadorRepo.findByNome(nome);
    }
}
