package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Aula {

    // Atributos de Aula
    private int id;
    private int idAluno;
    private int idProfessor;
    private String tipo;
    private LocalDate diaAula; 
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private ArrayList<Aluno> alunos_aula;

    // Construtores =============================

    // Construtor com ID
    public Aula(int id, int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim, ArrayList<Aluno> alunos_aula) {
        this.id = id;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.alunos_aula = alunos_aula;
    }

    // Construtor com ID
    public Aula(int id, int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
        this.id = id;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.alunos_aula = new ArrayList<Aluno>();
    }
    
    // Construtor sem ID
    public Aula(int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim, ArrayList<Aluno> alunos_aula) {
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.alunos_aula = alunos_aula;
    }

    // Construtor sem ID
    public Aula(int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.tipo = tipo;
        this.diaAula = diaAula;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.alunos_aula = new ArrayList<Aluno>();
    }    

    // // Construtor com ID
    // public Aula(int id, int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
    //     this.id = id;
    //     this.idAluno = idAluno;
    //     this.idProfessor = idProfessor;
    //     this.tipo = tipo;
    //     this.diaAula = diaAula;
    //     this.horarioInicio = horarioInicio;
    //     this.horarioFim = horarioFim;
    // }
    
    // // Construtor sem ID
    // public Aula(int idAluno, int idProfessor, String tipo, LocalDate diaAula, LocalTime horarioInicio, LocalTime horarioFim) {
    //     this.idAluno = idAluno;
    //     this.idProfessor = idProfessor;
    //     this.tipo = tipo;
    //     this.diaAula = diaAula;
    //     this.horarioInicio = horarioInicio;
    //     this.horarioFim = horarioFim;
    // }    

    // Construtor Vazio
    public Aula() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // ID do Aluno
    public int getIdAluno() {return idAluno;}
    public void setIdAluno(int idAluno) {this.idAluno = idAluno;}

    // ID do Professor
    public int getIdProfessor() {return idProfessor;}
    public void setIdProfessor(int idProfessor) {this.idProfessor = idProfessor;}

    // Tipo da Aula
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

    // Alunos na Aula
    public ArrayList<Aluno> getAlunos_aula() {return alunos_aula;}
    public void setAlunos_aula(ArrayList<Aluno> alunos_aula) {this.alunos_aula = alunos_aula;}

    // Métodos ==================================
    
}
