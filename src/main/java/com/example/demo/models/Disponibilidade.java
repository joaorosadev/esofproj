package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;

@Data
@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek dia;
    private int horaDeInicio;
    private int horaDeFim;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Explicador explicador;

    public Disponibilidade(DayOfWeek dia, int horaDeInicio, int horaDeFim){
        this.dia = dia;
        this.horaDeInicio = horaDeInicio;
        this.horaDeFim = horaDeFim;
    }

    public void setExplicador(Explicador explicador) {
        this.explicador = explicador;
    }
}
