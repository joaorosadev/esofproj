package com.example.demo.models.builders;

import com.example.demo.models.Estudante;

public class EstudanteBuilder {
    private String nome;
    private String password;

    public EstudanteBuilder setNome(String nome){
        this.nome=nome;
        return this;
    }

    public EstudanteBuilder setPassword(String password){
        this.password=password;
        return this;
    }

    public Estudante build(){
        return new Estudante(nome,password);
    }
}
