package com.example.demo.services.filters;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Map;

@Data
public class FilterObject {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private String cursoName;
    private String dia;
    private String hora_inicio;
    private String hora_fim;

    public FilterObject(String cursoName,String dia, String hora_inicio, String hora_fim) {
        this.cursoName = cursoName;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
    }

    public FilterObject() {}

    public FilterObject(Map<String, String> searchParams) {
        this();
        this.cursoName=searchParams.get("curso");
        this.dia=searchParams.get("dia");
        try {
            //LocalTime.parse();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.hora_inicio=searchParams.get("hora_inicio");
        this.hora_fim=searchParams.get("hora_fim");
    }
}
