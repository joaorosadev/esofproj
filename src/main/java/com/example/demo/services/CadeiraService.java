package com.example.demo.services;

import com.example.demo.models.Cadeira;
import com.example.demo.models.Curso;
import com.example.demo.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadeiraService {

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired private CursoService cursoService;

    public Optional<Cadeira> createCadeira(Cadeira cadeira, String cursoName) {
        Optional<Cadeira> optionalCadeira = this.cadeiraRepo.findByNome(cadeira.getNome());
        Optional<Curso> optionalCurso = this.cursoService.findByNome(cursoName);
        if(!optionalCadeira.isPresent() && optionalCurso.isPresent()){
            cadeira.setCurso(optionalCurso.get());
            return Optional.of(this.cadeiraRepo.save(cadeira));
        } else
            return Optional.empty();
    }
}
