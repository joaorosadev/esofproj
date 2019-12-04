package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //Back Reference quando é ManyToOne
    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Estudante> estudantes=new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Explicador> explicadors=new HashSet<>();

    public Curso(String nome){
        this.nome = nome;
    }

    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        cadeira.setCurso(this);
    }

    public void addEstudante(Estudante estudante){
        this.estudantes.add(estudante);
        estudante.setCurso(this);
    }

    public void addExplicador(Explicador explicador){
        this.explicadors.add(explicador);
        explicador.setCurso(this);
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

}
