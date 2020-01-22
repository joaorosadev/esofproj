package com.example.demo.services;

import com.example.demo.models.Curso;
import com.example.demo.models.Faculdade;
import com.example.demo.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CursoService {

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private FaculdadeService faculdadeService;

    public Optional<Curso> createCurso(Curso curso, String faculdadeName) {
        Optional<Curso> optionalCurso = this.cursoRepo.findByNome(curso.getNome());
        Optional<Faculdade> optionalFaculdade = this.faculdadeService.findByNome(faculdadeName);
        if(!optionalCurso.isPresent() && optionalFaculdade.isPresent()){
            curso.setFaculdade(optionalFaculdade.get());
            return Optional.of(this.cursoRepo.save(curso));
        } else
            return Optional.empty();
    }

    public Set<Curso> findAll() {
        Set<Curso> cursos = new HashSet<>();
        for(Curso curso: this.cursoRepo.findAll()){
            cursos.add(curso);
        }
        return cursos;
    }

    public Optional<Curso> findByNome(String nome){
        return this.cursoRepo.findByNome(nome);
    }

    public Curso save(Curso curso){
        return this.cursoRepo.save(curso);
    }
}
