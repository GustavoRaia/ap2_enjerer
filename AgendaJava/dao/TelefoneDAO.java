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
            String sql = "INSERT INTO telefone_professor (tipo, codigo_pais, codigo_area, numero, id_professor) VALUES (?, ?, ?, ?, ?)";

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

    // Função que Recupera Todos os Telefones Cadastrados
    public ArrayList<Telefone> retriveAll() {

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();

        try {
            String sql = "SELECT id, tipo, codigo_pais, codigo_area, numero, id_professor FROM telefone_professor";

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

    // Recupera todos os Telefones de um Professor Específico
    public ArrayList<Telefone> retriveAllPorProfessor(Professor professor) {

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();

        try {
            String sql = "SELECT id, tipo, codigo_pais, codigo_area, numero FROM telefone_professor WHERE id_professor = ?";

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

    /* Deleta todos os Telefones de um Professor
    public void deleteAllTelefone(int idProfessor) {
        try {
            String sql = "DELETE FROM telefone_professor WHERE telefone_professor.id_professor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, idProfessor);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

    // Função que deleta um telefone específico de um Professor.
    public void deleteTelefone(int idProfessor, String telefoneExcluir) {
        try {
            String sql = "DELETE FROM telefone_professor WHERE telefone_professor.id_professor = ? AND telefone_professor.numero = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, idProfessor);
                pstm.setString(2, telefoneExcluir);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que deleta um telefone específico de um Professor (por ID).
    public void deleteTelefonePorId(int idProfessor, int idExclusao) {
        try {
            String sql = "DELETE FROM telefone_professor WHERE telefone_professor.id_professor = ? AND telefone_professor.id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, idProfessor);
                pstm.setInt(2, idExclusao);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que altera o Número do Telefone do Professor
    public void updateTelefoneProfessor(int idAluno, String telefoneAntigo, String telefoneNovo) {
        try {
            String sql = "UPDATE telefone_professor, professor SET telefone_professor.numero = ? " + 
                                "WHERE telefone_professor.numero = ? " + 
                                     "AND telefone_professor.id_professor = ? " + 
                                     "AND telefone_professor.id_professor = professor.id;";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, telefoneNovo);
                pstm.setString(2, telefoneAntigo);
                pstm.setInt(3, idAluno);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que altera o Tipo do Telefone do Professor
    public void updateTipoTelefoneProfessor(int idAluno, String telefone, int tipoNovo) {
        try {
            String sql = "UPDATE telefone_professor, professor SET telefone_professor.tipo = ? " + 
                             "WHERE telefone_professor.numero = ? " + 
                                 "AND telefone_professor.id_professor = ? " + 
                                 " AND telefone_professor.id_professor = professor.id;";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, tipoNovo);
                pstm.setString(2, telefone);
                pstm.setInt(3, idAluno);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}