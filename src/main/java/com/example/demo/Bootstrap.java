package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.models.builders.EstudanteBuilder;
import com.example.demo.repositories.*;
import com.example.demo.services.CursoService;
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
import java.util.HashSet;

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
    @Autowired
    private ExplicadorRepo explicadorRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");


        /*
        Faculdade faculdade1 = new Faculdade("UFP1");
        Faculdade faculdade2 = new Faculdade("UFP2");

        Curso curso1 = new Curso("EngInf");
        Curso curso2 = new Curso("EngCivil");

        Curso curso3 = new Curso("Psicologia");
        Curso curso4 = new Curso("GestãoEmpresarial");

        Cadeira cadeira1 = new Cadeira("LP");
        Cadeira cadeira2 = new Cadeira("AED");
        Cadeira cadeira3 = new Cadeira("Construção");
        Cadeira cadeira4 = new Cadeira("Materias");

        Cadeira cadeira5 = new Cadeira("Psicomotria");
        Cadeira cadeira6 = new Cadeira("PsicologiaI");

        Explicador exp1 = new Explicador("Ribeiro","1234");
        Explicador exp2 = new Explicador("Salgado","4321");
        Explicador exp3 = new Explicador("Norberto","12333");
        Explicador exp4 = new Explicador("Serafim","3232");

        Explicador exp5 = new Explicador("Vanessa","1231231");
        Explicador exp6 = new Explicador("Teresa","nicc");

        Estudante est1 = new Estudante("Rui","ola");
        Estudante est2 = new Estudante("Joaquim","adeus");
        Estudante est3 = new Estudante("Sousa","sara123");
        Estudante est4 = new Estudante("Guilherme","alx");

        Estudante est5 = new EstudanteBuilder().setNome("Margarida").setPassword("andre").build();
        Estudante est6 = new EstudanteBuilder().setNome("Liliana").setPassword("mar").build();

        LocalTime hIn = LocalTime.of(14,0), hFim = LocalTime.of(16,0);
        LocalTime hIn2 = LocalTime.of(8,0), hFim2 = LocalTime.of(12,0);

        Disponibilidade d1 = new Disponibilidade(DayOfWeek.TUESDAY,hIn,hFim);
        Disponibilidade d2 = new Disponibilidade(DayOfWeek.MONDAY,hIn2,hFim2);
        Disponibilidade d3 = new Disponibilidade(DayOfWeek.THURSDAY,hIn,hFim);
        Disponibilidade d4 = new Disponibilidade(DayOfWeek.WEDNESDAY,hIn2,hFim2);

        //Adicionar cursos à faculdade (2 para cada)
        faculdade1.addCurso(curso1); faculdade1.addCurso(curso2);
        faculdade2.addCurso(curso3); faculdade2.addCurso(curso4);

        //Adicionar explicadores às faculdades
        //Faculdade1
        faculdade1.addExplicador(exp1);faculdade1.addExplicador(exp2);
        faculdade1.addExplicador(exp3);faculdade1.addExplicador(exp4);
        //Faculdade2
        faculdade2.addExplicador(exp5);faculdade2.addExplicador(exp6);

        //Adicionar cadeiras,explicadores e estudantes aos cursos
        //Faculdade1
        curso1.addCadeira(cadeira1); curso1.addCadeira(cadeira2);
        curso2.addCadeira(cadeira3); curso2.addCadeira(cadeira4);
        curso1.addEstudante(est1); curso1.addEstudante(est2);
        curso2.addEstudante(est3); curso2.addEstudante(est4);
        curso1.addExplicador(exp1); curso1.addExplicador(exp2);
        curso2.addExplicador(exp3); curso2.addExplicador(exp4);
        //Faculdade2
        curso3.addCadeira(cadeira5);
        curso4.addCadeira(cadeira6);
        curso3.addEstudante(est5);
        curso4.addEstudante(est6);
        curso3.addExplicador(exp5);
        curso4.addExplicador(exp6);

        //Adicionar cadeiras aos explicador (uma por explicador)
        //Faculdade1
        exp1.addCadeira(cadeira1);
        exp2.addCadeira(cadeira2);
        exp3.addCadeira(cadeira3);
        exp4.addCadeira(cadeira4);
        //Fauldade2
        exp5.addCadeira(cadeira5);
        exp6.addCadeira(cadeira6);

        //Adicionar disponibilidades aos explicadores
        //Faculdade1
        exp1.addDisponibilidade(d1);
        exp1.addDisponibilidade(d2);
        exp2.addDisponibilidade(d3);
        //Faculdade2
        exp5.addDisponibilidade(d4);

        //Faculdade1
        Explicacao e1 = est1.marcarExplicacao(LocalDateTime.of(2019,12,17,14,0),cadeira1);
        Explicacao e2 = est2.marcarExplicacao(LocalDateTime.of(2019,12,17,14,0),cadeira1);
        Explicacao e3 = est1.marcarExplicacao(LocalDateTime.of(2019,12,17,15,0),cadeira1);
        Explicacao e4 = est2.marcarExplicacao(LocalDateTime.of(2019,12,17,16,0),cadeira1);

        this.faculdadeRepo.save(faculdade1); this.faculdadeRepo.save(faculdade2);
        this.estudanteRepo.save(est1);this.estudanteRepo.save(est2);
        this.estudanteRepo.save(est3);this.estudanteRepo.save(est4);
        this.estudanteRepo.save(est5);this.estudanteRepo.save(est6);
        if(e1 != null) this.explicacaoRepo.save(e1); if(e2 != null) this.explicacaoRepo.save(e2);
        if(e3 != null) this.explicacaoRepo.save(e3); if(e4 != null) this.explicacaoRepo.save(e4);
        this.disponibilidadeRepo.save(d1); this.disponibilidadeRepo.save(d2);
        this.disponibilidadeRepo.save(d3); this.disponibilidadeRepo.save(d4);
*/


        Faculdade faculdade1 = new Faculdade("ISEP");
        Faculdade faculdade2 = new Faculdade("ISCAP");

        Curso curso1 = new Curso("EngenhariaMecanica");
        Curso curso2 = new Curso("EngenhariaQuimica");

        Curso curso3 = new Curso("Contabilidade");
        Curso curso4 = new Curso("Gestão");

        Cadeira cadeira1 = new Cadeira("Materias Metálicos");
        Cadeira cadeira2 = new Cadeira("Mecanica I");
        Cadeira cadeira3 = new Cadeira("Quimica I");
        Cadeira cadeira4 = new Cadeira("Análise Quimica");

        Cadeira cadeira5 = new Cadeira("Int. à contabilidade");
        Cadeira cadeira6 = new Cadeira("Gestão Financeira");

        Explicador exp1 = new Explicador("Martim","1234");
        Explicador exp2 = new Explicador("Ricardo","4321");
        Explicador exp3 = new Explicador("Sara","12333");
        Explicador exp4 = new Explicador("Ana","3232");

        Explicador exp5 = new Explicador("Maria","1231231");
        Explicador exp6 = new Explicador("Nicolau","nicc");

        Estudante est1 = new Estudante("Ricardo","ola");
        Estudante est2 = new Estudante("João","adeus");
        Estudante est3 = new Estudante("Sara","sara123");
        Estudante est4 = new Estudante("Alexandra","alx");

        Estudante est5 = new EstudanteBuilder().setNome("André").setPassword("andre").build();
        Estudante est6 = new EstudanteBuilder().setNome("Mariana").setPassword("mar").build();

        LocalTime hIn = LocalTime.of(14,0), hFim = LocalTime.of(16,0);
        LocalTime hIn2 = LocalTime.of(8,0), hFim2 = LocalTime.of(12,0);

        Disponibilidade d1 = new Disponibilidade(DayOfWeek.TUESDAY,hIn,hFim);
        Disponibilidade d2 = new Disponibilidade(DayOfWeek.MONDAY,hIn2,hFim2);
        Disponibilidade d3 = new Disponibilidade(DayOfWeek.FRIDAY,hIn,hFim);
        Disponibilidade d4 = new Disponibilidade(DayOfWeek.WEDNESDAY,hIn2,hFim2);

        //Adicionar cursos à faculdade (2 para cada)
        faculdade1.addCurso(curso1); faculdade1.addCurso(curso2);
        faculdade2.addCurso(curso3); faculdade2.addCurso(curso4);

        //Adicionar explicadores às faculdades
          //Faculdade1
        faculdade1.addExplicador(exp1);faculdade1.addExplicador(exp2);
        faculdade1.addExplicador(exp3);faculdade1.addExplicador(exp4);
          //Faculdade2
        faculdade2.addExplicador(exp5);faculdade2.addExplicador(exp6);

        //Adicionar cadeiras,explicadores e estudantes aos cursos
          //Faculdade1
        curso1.addCadeira(cadeira1); curso1.addCadeira(cadeira2);
        curso2.addCadeira(cadeira3); curso2.addCadeira(cadeira4);
        curso1.addEstudante(est1); curso1.addEstudante(est2);
        curso2.addEstudante(est3); curso2.addEstudante(est4);
        curso1.addExplicador(exp1); curso1.addExplicador(exp2);
        curso2.addExplicador(exp3); curso2.addExplicador(exp4);
          //Faculdade2
        curso3.addCadeira(cadeira5);
        curso4.addCadeira(cadeira6);
        curso3.addEstudante(est5);
        curso4.addEstudante(est6);
        curso3.addExplicador(exp5);
        curso4.addExplicador(exp6);

        //Adicionar cadeiras aos explicador (uma por explicador)
          //Faculdade1
        exp1.addCadeira(cadeira1);
        exp2.addCadeira(cadeira2);
        exp3.addCadeira(cadeira3);
        exp4.addCadeira(cadeira4);
          //Fauldade2
        exp5.addCadeira(cadeira5);
        exp6.addCadeira(cadeira6);

        //Adicionar disponibilidades aos explicadores
          //Faculdade1
        exp1.addDisponibilidade(d1);
        exp1.addDisponibilidade(d2);
        exp2.addDisponibilidade(d3);
          //Faculdade2
        exp5.addDisponibilidade(d4);

        //Marcar explicação (Dois estudantes tentam marcar à mesma hora, 1 tenta marcar a meio da
        //disponibilidade, 1 tenta marcar na hora de fim da disponibilidade, 1 tenta marcar fora
        //da disponibilidade
          //Faculdade1
        Explicacao e1 = est1.marcarExplicacao(LocalDateTime.of(2019,12,17,14,0),cadeira1);
        Explicacao e2 = est2.marcarExplicacao(LocalDateTime.of(2019,12,17,14,0),cadeira1);
        Explicacao e3 = est1.marcarExplicacao(LocalDateTime.of(2019,12,17,15,0),cadeira1);
        Explicacao e4 = est2.marcarExplicacao(LocalDateTime.of(2019,12,17,16,0),cadeira1);


        this.faculdadeRepo.save(faculdade1); this.faculdadeRepo.save(faculdade2);
        this.estudanteRepo.save(est1);this.estudanteRepo.save(est2);
        this.estudanteRepo.save(est3);this.estudanteRepo.save(est4);
        this.estudanteRepo.save(est5);this.estudanteRepo.save(est6);
        if(e1 != null) this.explicacaoRepo.save(e1); if(e2 != null) this.explicacaoRepo.save(e2);
        if(e3 != null) this.explicacaoRepo.save(e3); if(e4 != null) this.explicacaoRepo.save(e4);
        //this.disponibilidadeRepo.save(d1); this.disponibilidadeRepo.save(d2);
        //this.disponibilidadeRepo.save(d3); this.disponibilidadeRepo.save(d4);


        //this.disponibilidadeRepo.save(new Disponibilidade(DayOfWeek.MONDAY,LocalTime.of(10,0),LocalTime.of(12,0)));

      //  System.out.println(this.disponibilidadeRepo.findAll());


    }
}
