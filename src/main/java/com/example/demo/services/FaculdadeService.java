package com.example.demo.services;

import com.example.demo.models.Faculdade;
import com.example.demo.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FaculdadeService {

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    public Set<Faculdade> findAll() {
        Set<Faculdade> faculdades = new HashSet<>();
        for(Faculdade faculdade: this.faculdadeRepo.findAll()){
            faculdades.add(faculdade);
        }
        return faculdades;
    }

    public Optional<Faculdade> createFaculdade(Faculdade faculdade) {
        Optional<Faculdade> optionalFaculdade = this.faculdadeRepo.findByNome(faculdade.getNome());
        if(!optionalFaculdade.isPresent()){
            return Optional.of(this.faculdadeRepo.save(faculdade));
        } else
            return Optional.empty();
    }

    public Optional<Faculdade> findByNome(String nome){
        return this.faculdadeRepo.findByNome(nome);
    }
}
