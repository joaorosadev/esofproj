package com.example.demo.services.filters;

import com.example.demo.models.Explicador;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterExplicadorService {

    public Set<Explicador> filter(Set<Explicador> explicadors, FilterObject filterObject) {
        FilterExplicadorI cursoNameFilter=new ExplicadorFilterCursoName(filterObject.getCursoName());
        FilterExplicadorI diaFilter=new ExplicadorFilterDia(filterObject.getDia());

        FilterExplicadorI horaInicioFilter = new ExplicadorFilterHoraInicio(filterObject.getHora_inicio());
        FilterExplicadorI horafimFilter = new ExplicadorFilterHoraFim(filterObject.getHora_fim());

        FilterExplicadorI cursoNameAndDia=new AndExplicadorFilter(cursoNameFilter,diaFilter);
        FilterExplicadorI  horaInicioAndHoraFim=new AndExplicadorFilter(horaInicioFilter,horafimFilter);

        return new AndExplicadorFilter(cursoNameAndDia,horaInicioAndHoraFim).filter(explicadors);
    }
}
