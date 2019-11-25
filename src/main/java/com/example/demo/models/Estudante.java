package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "estudante",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Explicacao> explicacoes=new HashSet<>();

    public Estudante(String nome){
        this.nome = nome;
    }

    public void addExplicacao(Explicacao exp){
        this.explicacoes.add(exp);
        exp.setEstudante(this);
    }

    //Retorna a Explicacao se for marcada, null se não fôr possível marcar
    public Explicacao marcarExplicacao(LocalDateTime data, Cadeira cadeira){
        Explicador explicadorDaCadeira = cadeira.getExplicador();
        //For/If verifica se já existe uma explicação para a data fornecida pelo estudante
        for(Explicacao explicacao: explicadorDaCadeira.getExplicacoes()) {
            if (explicacao.getData().getYear() == data.getYear() &&
                    explicacao.getData().getMonthValue() == data.getMonthValue() &&
                    explicacao.getData().getDayOfMonth() == data.getDayOfMonth() &&
                    explicacao.getData().getHour() == data.getHour()) {
                System.out.println("Data já ocupada. Não é possível marcar atendimento.");
                return null;
            }

            /* Se não existe explicação marcada para aquela data, vamos verificar se o
        explicador está disponível para essa data, se sim, a explicação é marcada */
        }
        for(Disponibilidade disp: explicadorDaCadeira.getDisponibilidades()){
                //System.out.println(disp.getDia()+" "+disp.getHoraDeInicio());
            if(data.getDayOfWeek() == disp.getDia() && data.getHour() >= disp.getHoraDeInicio() && data.getHour() + 1 <= disp.getHoraDeFim()) {
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
