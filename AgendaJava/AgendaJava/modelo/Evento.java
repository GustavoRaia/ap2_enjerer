package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {

    private int id;
    private LocalDate data;
    private String descricao;
    private ArrayList<Pessoa> pessoas;

    public Evento(LocalDate data, String descricao, ArrayList<Pessoa> pessoas) {
        this.data = data;
        this.descricao = descricao;
        this.pessoas = pessoas;
    }

    public Evento(int id, LocalDate data, String descricao, ArrayList<Pessoa> pessoas) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.pessoas = pessoas;
    }

    public Evento(LocalDate data, String descricao) {
        this.data = data;
        this.descricao = descricao;
        this.pessoas = new ArrayList<Pessoa>();
    }

    public Evento(int id, LocalDate data, String descricao) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.pessoas = new ArrayList<Pessoa>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public void removePessoa(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
    }

    @Override
    public String toString() {
        return "{'evento':{'id': " + this.id + ", 'data': '" + this.data + "', 'descricao': '" + this.descricao + "'}}";
    }

}
