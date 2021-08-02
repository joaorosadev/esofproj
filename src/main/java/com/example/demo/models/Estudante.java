package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String password;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Curso curso;

    @OneToMany(mappedBy = "estudante",cascade = CascadeType.ALL)
    @JsonManagedReference(value="secondParent")
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Estudante(String nome,String password){
        this.nome = nome;
        this.password=password;
    }

    public void addExplicacao(Explicacao exp){
        this.explicacoes.add(exp);
        exp.setEstudante(this);
    }

    public void setCurso(Curso curso){
        this.curso=curso;
    }

    //Retorna a Explicacao se for marcada, null se não fôr possível marcar
    public Explicacao marcarExplicacao(LocalDateTime data, Cadeira cadeira){

        Explicador explicadorDaCadeira = cadeira.getExplicador();

        if(this.curso.getNome() != cadeira.getCurso().getNome()) return null;

        //For/If verifica se já existe uma explicação para a data fornecida pelo estudante
        for(Explicacao explicacao: explicadorDaCadeira.getExplicacoes()) {
            if (explicacao.getData().getYear() == data.getYear()
                    && explicacao.getData().getMonthValue() == data.getMonthValue()
                    && explicacao.getData().getDayOfMonth() == data.getDayOfMonth()
                    && explicacao.getData().getHour() == data.getHour()
                    && explicacao.getData().getMinute() == data.getMinute()) {
                System.out.println("Data já ocupada. Não é possível marcar atendimento.");
                return null;
            }

            /* Se não existe explicação marcada para aquela data, vamos verificar se o
        explicador está disponível para essa data, se sim, a explicação é marcada */
        }
        for(Disponibilidade disp: explicadorDaCadeira.getDisponibilidades()){

            if(data.getDayOfWeek() == disp.getDia() && data.getHour() >= disp.getHoraIn().getHour() && data.getHour() + 1 <= disp.getHoraFim().getHour()
            && data.getMinute() >= disp.getHoraIn().getMinute()) {
                Explicacao explicacaoDefinida = new Explicacao();
                explicacaoDefinida.setData(data);
                this.addExplicacao(explicacaoDefinida);
                explicadorDaCadeira.addExplicacao(explicacaoDefinida);
                System.out.println("Explicação marcada em "+data+" com o explicador "+explicadorDaCadeira.getNome()+" para o aluno "+this.getNome()+".");
                return explicacaoDefinida;
            }
        }
        System.out.println("Não foi possível marcar. Data pretendida encontra-se fora do horário do explicador.");
        return null;
    }
}
