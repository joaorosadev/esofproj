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
public class Explicador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String password;

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value="secondParent")
    private Set<Cadeira> cadeiras=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Disponibilidade> disponibilidades=new HashSet<>();

    @OneToMany(mappedBy = "explicador",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Explicacao> explicacoes=new HashSet<>();

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Curso curso;

    public Explicador(String nome, String password){
        this.nome = nome;
        this.password = password;
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

    public void setCurso(Curso curso){this.curso = curso;}
}
