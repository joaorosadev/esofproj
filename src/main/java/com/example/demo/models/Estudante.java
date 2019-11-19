package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "estudante",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Estudante(String nome){
        this.nome = nome;
    }

    public void addExplicacao(Explicacao exp){
        this.explicacoes.add(exp);
        exp.setEstudante(this);
    }
}
