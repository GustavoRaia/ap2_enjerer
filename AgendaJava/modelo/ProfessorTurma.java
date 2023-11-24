package modelo;

public class ProfessorTurma {
    private int id;
    private int idProfessor;
    private int idTurma;

    // Construtores =============================

    // Construtor com ID
    public ProfessorTurma(int id, Professor professor, int idTurma) {
        this.id = id;
        this.idProfessor = professor.getId();
        this.idTurma = idTurma;
    }

    // Construtor com ID
    public ProfessorTurma(int id, int idProfessor, int idTurma) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idTurma = idTurma;
    }

    // Construtor sem ID
    public ProfessorTurma(Professor professor, int idTurma) {
        this.idProfessor = professor.getId();
        this.idTurma = idTurma;
    }

    // Construtor Vazio
    public ProfessorTurma() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // ID do Professor
    public int getIdProfessor() {return idProfessor;}
    public void setIdProfessor(int idProfessor) {this.idProfessor = idProfessor;}

    // ID da Turma
    public int getIdTurma() {return idTurma;}
    public void setIdTurma(int idTurma) {this.idTurma = idTurma;}


}
