package modelo;

import java.time.LocalDate;

public class FichaMedica {

    // Atributos
    private int id;
    private int idAluno;
    private double altura;
    private double peso;
    private double imc;
    private String tipoSanguineo;
    private String contatoEmergencia;
    private LocalDate dataExame;

    // Construtores =============================

    // Construtor com ID
    public FichaMedica(int id, int idAluno, double altura, double peso, double imc, String tipoSanguineo, String contatoEmergencia, LocalDate dataExame) {
        this.id = id;
        this.idAluno = idAluno;
        this.altura = altura;
        this.peso = peso;
        this.imc = calcularIMC(altura, peso);
        this.tipoSanguineo = tipoSanguineo;
        this.contatoEmergencia = contatoEmergencia;
        this.dataExame = dataExame;
    }

    // Construtor sem ID
    public FichaMedica(int idAluno, double altura, double peso, double imc, String tipoSanguineo, String contatoEmergencia, LocalDate dataExame) {
        this.idAluno = idAluno;
        this.altura = altura;
        this.peso = peso;
        this.imc = calcularIMC(altura, peso);
        this.tipoSanguineo = tipoSanguineo;
        this.contatoEmergencia = contatoEmergencia;
        this.dataExame = dataExame;
    }

    // Construtor Vazio
    public FichaMedica() {
    }

    // Getters e Setters ========================

    // ID
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // ID do Aluno
    public int getIdAluno() {return idAluno;}
    public void setIdAluno(int idAluno) {this.idAluno = idAluno;}

    // Altura
    public double getAltura() {return altura;}
    public void setAltura(double altura) {this.altura = altura;}

    // Peso
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}

    // IMC
    public double getImc() {return imc;}
    public void setImc(double imc) {this.imc = imc;}    

    // Tipo Sanguíneo
    public String getTipoSanguineo() {return tipoSanguineo;}
    public void setTipoSanguineo(String tipoSanguineo) {this.tipoSanguineo = tipoSanguineo;}

    // Contato de Emergência
    public String getContatoEmergencia() {return contatoEmergencia;}
    public void setContatoEmergencia(String contatoEmergencia) {this.contatoEmergencia = contatoEmergencia;}

    // Data do Exame
    public LocalDate getDataExame() {return dataExame;}
    public void setDataExame(LocalDate dataExame) {this.dataExame = dataExame;}

    // Métodos ==================================

    public double calcularIMC(double altura, double peso) {
        return peso / (altura * altura);
    }

}
