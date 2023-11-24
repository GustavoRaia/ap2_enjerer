package modelo;


public class AlunoTurma {
    private int id;
    private int idAluno;
    private int idTurma;

    // Construtores =============================

    // Construtor com ID
    public AlunoTurma(int id, int idAluno, int idTurma) {
        this.id = id;
        this.idAluno = idAluno;
        this.idTurma = idTurma;
    }

    // Construtor com ID
    public AlunoTurma(int id, Aluno aluno, Turma turma) {
        this.id = id;
        this.idAluno = aluno.getId();
        this.idTurma = turma.getId();
    }

    // Construtor com ID
    public AlunoTurma(Aluno aluno, Turma turma) {
        this.idAluno = aluno.getId();
        this.idTurma = turma.getId();
    }

    // Construtor com ID
    public AlunoTurma(Aluno aluno, int idTurma) {
        this.idAluno = aluno.getId();
        this.idTurma = idTurma;
    }

    // Construtor Sem ID
    public AlunoTurma(int idAluno, int idTurma) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
    }

    // Construtor Vazio
    public AlunoTurma() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // ID do Aluno
    public int getIdAluno() {return idAluno;}
    public void setIdAluno(int idAluno) {this.idAluno = idAluno;}

    // ID da Turma
    public int getIdTurma() {return idTurma;}
    public void setIdTurma(int idTurma) {this.idTurma = idTurma;}
    
}
