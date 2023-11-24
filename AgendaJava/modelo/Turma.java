package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turma {

    // Atributos de Turma
    private int id;
    private String tipo;
    private LocalDate diaAula;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    // Construtores =============================

    // Construtor com ID
    public Turma(int id, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
        this.id = id;
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    // Construtor sem ID
    public Turma(String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    // Construtor vazio
    public Turma() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() { return id; }
    public void setId(int id) {this.id = id;}

    // Tipo da Turma
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    // Dia da Aula
    public LocalDate getDiaAula() {return diaAula;}
    public void setDiaAula(LocalDate diaAula) {this.diaAula = diaAula;}

    // Horário de Inicio da Aula
    public LocalTime getHorarioInicio() {return horarioInicio;}
    public void setHorarioInicio(LocalTime horarioInicio) {this.horarioInicio = horarioInicio;}

    // Horário de Fim da Aula
    public LocalTime getHorarioFim() {return horarioFim;}
    public void setHorarioFim(LocalTime horarioFim) {this.horarioFim = horarioFim;}

}
