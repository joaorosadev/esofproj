package com.example.demo.services.filters;

import com.example.demo.models.Disponibilidade;
import com.example.demo.models.Explicador;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterHoraFim implements FilterExplicadorI {
    private String horaFim;

    public ExplicadorFilterHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> explicadors) {
        if(this.horaFim ==null || this.horaFim.isBlank()){
            return explicadors;
        }

        int hour = Integer.parseInt(horaFim.substring(0,2));
        int min = Integer.parseInt(horaFim.substring(2));
        Set<Explicador> explicadoresToBeReturned=new HashSet<>();
        for(Explicador exp:explicadors){
            for(Disponibilidade disp: exp.getDisponibilidades()){
                if(disp.getHoraFim().getHour() == hour && disp.getHoraFim().getMinute() == min){
                    explicadoresToBeReturned.add(exp);
                }
            }
        }
        return explicadoresToBeReturned;
    }
}