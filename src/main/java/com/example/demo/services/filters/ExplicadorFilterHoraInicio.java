package com.example.demo.services.filters;

import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterHoraInicio implements FilterExplicadorI {
    private String horaInicio;

    public ExplicadorFilterHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadors) {
        if(this.horaInicio ==null || this.horaInicio.isBlank()){
            return explicadors;
        }

        int hour = Integer.parseInt(horaInicio.substring(0,2));
        int min = Integer.parseInt(horaInicio.substring(2));
        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador exp:explicadors){
            for(Disponibilidade disp: exp.getDisponibilidades()){
                if(disp.getHoraIn().getHour() == hour && disp.getHoraIn().getMinute() == min){
                    explicadoresToBeReturned.add(exp);
                }
            }
        }
        return explicadoresToBeReturned;
    }
}
