package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import modelo.Professor;
import modelo.Telefone;
import modelo.TipoTelefone;

public class TelefoneDAO {

    private Connection connection;

    public TelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    // A professor passada como parâmetro já deve ter sido criada no banco
    public void create(Telefone telefone, Professor professor) {
        try {
            String sql = "INSERT INTO telefoneprofessor (tipo, codigo_pais, codigo_area, numero, id_professor) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, telefone.getTipo().valor);
                pstm.setInt(2, telefone.getCodigoPais());
                pstm.setInt(3, telefone.getCodigoArea());
                pstm.setInt(4, telefone.getNumero());
                pstm.setInt(5, professor.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        telefone.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Telefone> retriveAll() {

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();

        try {
            String sql = "SELECT id, tipo, codigo_pais, codigo_area, numero, id_professor FROM telefoneprofessor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int tel_id = rst.getInt("id");
                    TipoTelefone tipo = TipoTelefone.values()[rst.getInt("tipo")];
                    int cod_pais = rst.getInt("codigo_pais");
                    int cod_area = rst.getInt("codigo_area");
                    int numero = rst.getInt("numero");
                    int id_professor = rst.getInt("id_professor");

                    Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero, id_professor);
                    telefones.add(t);
                }
            }
            return telefones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Telefone> retriveAllPorPessoa(Professor professor) {

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();

        try {
            String sql = "SELECT id, tipo, codigo_pais, codigo_area, numero FROM telefoneprofessor WHERE id_professor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, professor.getId());
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int tel_id = rst.getInt("id");
                    TipoTelefone tipo = TipoTelefone.values()[rst.getInt("tipo")];
                    int cod_pais = rst.getInt("codigo_pais");
                    int cod_area = rst.getInt("codigo_area");
                    int numero = rst.getInt("numero");
                    int id_professor = rst.getInt("id_professor");

                    Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero, id_professor);
                    telefones.add(t);
                }
            }
            return telefones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
