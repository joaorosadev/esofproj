package com.example.demo.services.filters;

import com.example.demo.models.Explicador;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterCursoName implements FilterExplicadorI {
    private String cursoName;

    public ExplicadorFilterCursoName(String cursoName) {
        this.cursoName = cursoName;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadors) {
        if(this.cursoName==null || this.cursoName.isBlank()){
            return explicadors;
        }

        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador exp:explicadors){
            if(exp.getCurso()!=null && exp.getCurso().getNome()!=null
                    && exp.getCurso().getNome().equals(cursoName)){
                explicadoresToBeReturned.add(exp);
            }
        }
        return explicadoresToBeReturned;
    }
}
