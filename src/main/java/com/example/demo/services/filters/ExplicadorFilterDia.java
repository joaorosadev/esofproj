package com.example.demo.services.filters;

import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterDia implements FilterExplicadorI {
    private String dia;

    public ExplicadorFilterDia(String dia) {
        this.dia = dia;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadors) {
        if(this.dia ==null || this.dia.isBlank()){
            return explicadors;
        }

        int diaValue;
        switch(dia){
            case "domingo" : diaValue = 0; break;
            case "segunda": diaValue = 1; break;
            case "terca": diaValue = 2; break;
            case "quarta": diaValue = 3; break;
            case "quinta": diaValue = 4; break;
            case "sexta": diaValue = 5; break;
            case "sabado": diaValue = 6; break;
            default: diaValue = -1; break;
        }

        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador exp:explicadors){
            for(Disponibilidade disp: exp.getDisponibilidades()){
                if(disp.getDia().getValue() == diaValue){
                    explicadoresToBeReturned.add(exp);
                }
            }
        }
        return explicadoresToBeReturned;
    }
}
