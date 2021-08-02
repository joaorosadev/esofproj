package com.example.demo.services;

import com.example.demo.models.Estudante;
import com.example.demo.models.Explicacao;
import com.example.demo.models.Explicador;
import com.example.demo.repositories.EstudanteRepo;
import com.example.demo.repositories.ExplicacaoRepo;
import com.example.demo.repositories.ExplicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExplicacaoService {

    @Autowired
    private ExplicacaoRepo explicacaoRepo;
    @Autowired
    private ExplicadorRepo explicadorRepo;
    @Autowired
    private EstudanteRepo estudanteRepo;

    public Optional<Explicacao> createExplicacao(Explicacao explicacao){
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByNome(explicacao.getExplicador().getNome());
        Optional<Estudante> optionalEstudante = this.estudanteRepo.findByNome(explicacao.getEstudante().getNome());

        if(optionalExplicador.isPresent() && optionalEstudante.isPresent()){
            optionalExplicador.get().addExplicacao(explicacao);
            optionalEstudante.get().addExplicacao(explicacao);
            return Optional.of(this.explicacaoRepo.save(explicacao));
        } else
            return Optional.empty();
    }
}
