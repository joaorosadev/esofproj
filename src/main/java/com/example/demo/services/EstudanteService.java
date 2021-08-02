package com.example.demo.services;

import com.example.demo.models.Estudante;
import com.example.demo.repositories.EstudanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepo estudanteRepo;

    public Optional<Estudante> findByNome(String nome){
        return this.estudanteRepo.findByNome(nome);
    }
}
