package modelo;
public class Telefone{

    private int id;
    private TipoTelefone tipo;
    private int codigoPais;
    private int codigoArea;
    private int numero;
    private int idProfessor;

    public Telefone(TipoTelefone tipo, int codigoPais, int codigoArea, int numero, int idProfessor) {
        this.tipo = tipo;
        this.codigoPais = codigoPais;
        this.codigoArea = codigoArea;
        this.numero = numero;
        this.idProfessor = idProfessor;
    }

    public Telefone(int id, TipoTelefone tipo, int codigoPais, int codigoArea, int numero, int idProfessor) {
        this.id = id;
        this.tipo = tipo;
        this.codigoPais = codigoPais;
        this.codigoArea = codigoArea;
        this.numero = numero;
        this.idProfessor = idProfessor;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public TipoTelefone getTipo() {return tipo;}
    public void setTipo(TipoTelefone tipo) {this.tipo = tipo;}

    public int getCodigoPais() {return codigoPais;}
    public void setCodigoPais(int codigoPais) {this.codigoPais = codigoPais;}

    public int getCodigoArea() {return codigoArea;}
    public void setCodigoArea(int codigoArea) {this.codigoArea = codigoArea;}
    
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}

    public int getIdProfessor() {return idProfessor;}
    public void setIdProfessor(int idProfessor) {this.idProfessor = idProfessor;}    

    @Override
    public String toString() {
        return "Id: " + this.id + "\n\t\tTipo: " + this.tipo + "\n\t\tNúmero: +" + this.codigoPais + this.codigoArea + this.numero;
    }


}