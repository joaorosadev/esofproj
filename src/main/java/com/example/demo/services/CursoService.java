package com.example.demo.services;

import com.example.demo.models.Curso;
import com.example.demo.models.Explicador;
import com.example.demo.models.Faculdade;
import com.example.demo.repositories.CursoRepo;
import com.example.demo.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Curso> findByNome(String nome){
        return this.cursoRepo.findByNome(nome);
    }
}
