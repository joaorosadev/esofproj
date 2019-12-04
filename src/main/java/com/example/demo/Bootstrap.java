package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.DisponibilidadeRepo;
import com.example.demo.repositories.EstudanteRepo;
import com.example.demo.repositories.ExplicacaoRepo;
import com.example.demo.repositories.FaculdadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeRepo faculdadeRepo;
    @Autowired
    private EstudanteRepo estudanteRepo;
    @Autowired
    private ExplicacaoRepo explicacaoRepo;
    @Autowired
    private DisponibilidadeRepo disponibilidadeRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        Faculdade faculdade1 = new Faculdade("UFP");

        Curso curso1 = new Curso("Engenharia Informática");
        Curso curso2 = new Curso("Psicologia");

        Cadeira cadeira1 = new Cadeira("Engenharia de Software");
        Cadeira cadeira2 = new Cadeira("Linguagens de Programação");
        Cadeira cadeira3 = new Cadeira("Psicometria");
        Cadeira cadeira4 = new Cadeira("Psicologia Forense");

        Explicador exp1 = new Explicador("Fernando");
        Explicador exp2 = new Explicador("Alberto");
        Explicador exp3 = new Explicador("Sofia");
        Explicador exp4 = new Explicador("Maria");

        Estudante est1 = new Estudante("Ricardo");
        Estudante est2 = new Estudante("João");
        Estudante est3 = new Estudante("Sara");
        Estudante est4 = new Estudante("Alexandra");

        LocalTime hIn = LocalTime.of(14,0), hFim = LocalTime.of(16,0);
        LocalTime hIn2 = LocalTime.of(8,0), hFim2 = LocalTime.of(12,0);

        Disponibilidade d1 = new Disponibilidade(DayOfWeek.WEDNESDAY,hIn,hFim);
        Disponibilidade d2 = new Disponibilidade(DayOfWeek.FRIDAY,hIn2,hFim2);

        //Adicionar cursos à faculdade (2 para uma)
        faculdade1.addCurso(curso1);
        faculdade1.addCurso(curso2);
        //Adicionar cadeiras,explicadores e estudantes aos cursos (duas por curso)
        curso1.addCadeira(cadeira1); curso1.addCadeira(cadeira2);
        curso2.addCadeira(cadeira3); curso2.addCadeira(cadeira4);
        curso1.addEstudante(est1); curso1.addEstudante(est2);
        curso2.addEstudante(est3); curso2.addEstudante(est4);
        curso1.addExplicador(exp1); curso1.addExplicador(exp2);
        curso2.addExplicador(exp3); curso2.addExplicador(exp4);
        //Adicionar cadeiras aos explicador (uma por explicador)
        exp1.addCadeira(cadeira1);
        exp2.addCadeira(cadeira2);
        exp3.addCadeira(cadeira3);
        exp4.addCadeira(cadeira4);
        //Adicionar disponibilidades aos explicadores (para já, apenas duas para um)
        exp1.addDisponibilidade(d1);
        exp1.addDisponibilidade(d2);
        /*Marcar explicação (Dois estudantes tentam marcar à mesma hora, 1 tenta marcar a meio da
        disponibilidade, 1 tenta marcar na hora de fim da disponibilidade, 1 tenta marcar fora
        da disponibilidade*/
        Explicacao e1 = est1.marcarExplicacao(LocalDateTime.of(2019,11,27,14,0),cadeira1);
        Explicacao e2 = est2.marcarExplicacao(LocalDateTime.of(2019,11,27,14,0),cadeira1);
        Explicacao e3 = est1.marcarExplicacao(LocalDateTime.of(2019,11,27,15,0),cadeira1);
        Explicacao e4 = est2.marcarExplicacao(LocalDateTime.of(2019,11,27,16,0),cadeira1);

        this.faculdadeRepo.save(faculdade1);
        this.estudanteRepo.save(est1);this.estudanteRepo.save(est2);
        this.estudanteRepo.save(est3);this.estudanteRepo.save(est4);
        if(e1 != null) this.explicacaoRepo.save(e1); if(e2 != null) this.explicacaoRepo.save(e2);
        if(e3 != null) this.explicacaoRepo.save(e3); if(e4 != null) this.explicacaoRepo.save(e4);
        this.disponibilidadeRepo.save(d1); this.disponibilidadeRepo.save(d2);
    }
}
