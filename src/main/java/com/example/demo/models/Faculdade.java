package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Faculdade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "faculdade",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Curso> cursos=new HashSet<>();

    public Faculdade(String nome){
        this.nome = nome;
    }

    public void addCurso(Curso curso){
        this.cursos.add(curso);
        curso.setFaculdade(this);
    }
}
