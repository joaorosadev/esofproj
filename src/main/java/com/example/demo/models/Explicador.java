package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Explicador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Disponibilidade> disponibilidades=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Explicador(String nome){
        this.nome = nome;
    }

    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        cadeira.setExplicador(this);
    }

    public void addDisponibilidade(Disponibilidade disp){
        this.disponibilidades.add(disp);
        disp.setExplicador(this);
    }

    public void addExplicacao(Explicacao exp){
        this.explicacoes.add(exp);
        exp.setExplicador(this);
    }
}
