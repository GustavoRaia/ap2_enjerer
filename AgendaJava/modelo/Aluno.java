package modelo;

import java.time.LocalDate;
import java.time.Period;

public class Aluno {

    // Atributos de Aluno
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private int idade;

    // Construtores =============================

    // Construtor com ID
    public Aluno(int id, String nome, String cpf, String telefone, LocalDate dataNascimento, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
    }

    // Construror sem ID
    public Aluno(String nome, String cpf, String telefone, LocalDate dataNascimento, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
    }

    // Construror sem Idade como atributo
    public Aluno(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
    }

    // Construtor Vazio
    public Aluno() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // Nome
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    // CPF
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    // Telefone
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    // Data Nascimento
    public LocalDate getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    // Idade
    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    // Métodos ==================================

    // Calcular Idade
    private int calculaIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }
    
}
