package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek dia;
    LocalTime horaIn, horaFim;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Explicador explicador;

    public Disponibilidade(DayOfWeek dia, LocalTime horaIn, LocalTime horaFim){
        this.dia = dia;
        this.horaIn = horaIn;
        this.horaFim= horaFim;
    }

    public void setExplicador(Explicador explicador) {
        this.explicador = explicador;
    }
}
