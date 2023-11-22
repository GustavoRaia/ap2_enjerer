package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Professor {

    // Atributos de Professor
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private int idade;
    private double salario;
    private ArrayList<Telefone> telefones;

    // Construtores =============================

    // Construtor com ID
    public Professor(int id, String nome, String cpf, LocalDate dataNascimento, int idade, double salario, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = telefones;
    }

    // Construtor sem Telefone
    public Professor(int id, String nome, String cpf, LocalDate dataNascimento, int idade, double salario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = new ArrayList<Telefone>();
    }

    // Consrutor sem ID
    public Professor(String nome, String cpf, LocalDate dataNascimento, int idade, double salario, ArrayList<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = telefones;
    }

    // Consrutor sem ID
    public Professor(String nome, String cpf, LocalDate dataNascimento, int idade, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = new ArrayList<Telefone>();
    }

    // Consrutor sem Idade como atributo
    public Professor(String nome, String cpf, LocalDate dataNascimento, double salario, ArrayList<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = telefones;
    }

    // Consrutor sem Idade e Telefones como atributo
    public Professor(String nome, String cpf, LocalDate dataNascimento, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.salario = salario;
        this.telefones = new ArrayList<Telefone>();
    }

    // Construtor Vazio
    public Professor() {
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

    // Data de Nascimento
    public LocalDate getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    // Idade
    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    // Salário
    public double getSalario() {return salario;}
    public void setSalario(double salario) {this.salario = salario;}

    // Telefones
    public ArrayList<Telefone> getTelefones() {return telefones;}
    public void setTelefones(ArrayList<Telefone> telefones) {this.telefones = telefones;}    

    // Métodos ==================================

    // Calcular Idade
    private int calculaIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

    public void removeTelefone(Telefone telefone) {
        this.telefones.remove(telefone);
    }

    @Override
    public String toString() {
        return "Id: " + this.id + 
                "\nNome: " + this.nome + 
                "\nCPF: " + this.cpf +
                "\nData de Nascimento: " + this.dataNascimento +
                "\nIdade: " + this.idade +
                "\nSalário: " + this.salario;
    }

}
