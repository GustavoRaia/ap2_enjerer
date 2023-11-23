package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Aluno;
import modelo.FichaMedica;

public class FichaMedicaDAO {

    private Connection connection;

    public FichaMedicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(FichaMedica fichaMedica, Aluno aluno) {
        try {
            String sql = "INSERT INTO ficha_medica (altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, fichaMedica.getAltura());
                pstm.setDouble(2, fichaMedica.getPeso());
                pstm.setDouble(3, fichaMedica.getImc());
                pstm.setString(4, fichaMedica.getTipoSanguineo());
                pstm.setString(5, fichaMedica.getContatoEmergencia());
                pstm.setObject(6, fichaMedica.getDataExame());
                pstm.setInt(7, aluno.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        fichaMedica.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<FichaMedica> getAllFichaMedica() {

        ArrayList<FichaMedica> telefones = new ArrayList<FichaMedica>();

        try {
            String sql = "SELECT id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno FROM ficha_medica";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    Double altura = rst.getDouble("altura");
                    Double peso = rst.getDouble("peso");
                    Double imc = rst.getDouble("imc");
                    String tipo_sanguineo = rst.getString("tipo_sanguineo");
                    String contato_emergencia = rst.getString("contato_emergencia");
                    LocalDate data_exame = rst.getObject("data_exame", LocalDate.class);
                    int id_aluno = rst.getInt("id_aluno");

                    FichaMedica t = new FichaMedica(id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno);
                    telefones.add(t);
                }
            }
            return telefones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FichaMedica getFichaMedicaPorAluno(Aluno aluno) {

        try {
            String sql = "SELECT ficha_medica.id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno " + 
                         "FROM ficha_medica, aluno WHERE aluno.id = ? AND ficha_medica.id_aluno = aluno.id;";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {

                pstm.setInt(1, aluno.getId());
                pstm.execute();

                ResultSet rst = pstm.getResultSet();

                while(rst.next()) {
                    int id = rst.getInt("id");
                    Double altura = rst.getDouble("altura");
                    Double peso = rst.getDouble("peso");
                    Double imc = rst.getDouble("imc");
                    String tipo_sanguineo = rst.getString("tipo_sanguineo");
                    String contato_emergencia = rst.getString("contato_emergencia");
                    LocalDate data_exame = rst.getObject("data_exame", LocalDate.class);
                    int id_aluno = rst.getInt("id_aluno");

                    FichaMedica fichaMedica = new FichaMedica(id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno);
                    
                    return fichaMedica;                    
                }
                return new FichaMedica();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar o Peso do Aluno na Ficha Médica
    public void updatePesoFichaMedica(FichaMedica fichaMedica, Double novoPeso) {
        try {
            String sql = "UPDATE ficha_medica SET peso = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, novoPeso);
                pstm.setInt(2, fichaMedica.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar a Altura do Aluno na Ficha Médica
    public void updateAlturaFichaMedica(FichaMedica fichaMedica, Double novaAltura) {
        try {
            String sql = "UPDATE ficha_medica SET altura = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, novaAltura);
                pstm.setInt(2, fichaMedica.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar o Contato de Emergência do Aluno na Ficha Médica
    public void updateContatoEmergenciaFichaMedica(FichaMedica fichaMedica, String novoContatoEmergencia) {
        try {
            String sql = "UPDATE ficha_medica SET contato_emergencia = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoContatoEmergencia);
                pstm.setInt(2, fichaMedica.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar a Data do Exame na Ficha Médica
    public void updateDataExameFichaMedica(FichaMedica fichaMedica, LocalDate novaDataExame) {
        try {
            String sql = "UPDATE ficha_medica SET data_exame = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novaDataExame);
                pstm.setInt(2, fichaMedica.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deleteFichaMedica(FichaMedica fichaMedica) {
        try {
            String sql = "DELETE FROM ficha_medica WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, fichaMedica.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

}
