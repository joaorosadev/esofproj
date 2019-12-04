package com.example.demo.services;

import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.ExplicacaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExplicacaoService {

    @Autowired
    private ExplicacaoRepo explicacaoRepo;

    public Optional<Explicacao> createExplicacao(Explicacao explicacao){
        Optional<Explicacao> optionalExplicacao = this.explicacaoRepo.findById(explicacao.getId());
        if(!optionalExplicacao.isPresent()){
            return Optional.of(this.explicacaoRepo.save(explicacao));
        } else
            return Optional.empty();
    }
}
