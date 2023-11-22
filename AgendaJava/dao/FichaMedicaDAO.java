package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.FichaMedica;

public class FichaMedicaDAO {

    private Connection connection;

    public FichaMedicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(FichaMedica fichaMedica) {
        try {
            String sql = "INSERT INTO ficha_medica (altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame, id_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, fichaMedica.getAltura());
                pstm.setDouble(2, fichaMedica.getPeso());
                pstm.setDouble(3, fichaMedica.getImc());
                pstm.setString(4, fichaMedica.getTipoSanguineo());
                pstm.setString(5, fichaMedica.getContatoEmergencia());
                pstm.setObject(6, fichaMedica.getDataExame());
                pstm.setInt(7, fichaMedica.getIdAluno());
                pstm.execute();

                // try (ResultSet rst = pstm.getGeneratedKeys()) {
                //     while (rst.next()) {
                //         fichaMedica.setId(rst.getInt(1));
                //     }
                // }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public void create(FichaMedica fichaMedica) {
        try {
            String sql = "INSERT INTO ficha_medica (altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, fichaMedica.getAltura());
                pstm.setDouble(2, fichaMedica.getPeso());
                pstm.setDouble(3, fichaMedica.getImc());
                pstm.setString(4, fichaMedica.getTipoSanguineo());
                pstm.setString(5, fichaMedica.getContatoEmergencia());
                pstm.setObject(6, fichaMedica.getDataExame());
                pstm.execute();

                // try (ResultSet rst = pstm.getGeneratedKeys()) {
                //     while (rst.next()) {
                //         fichaMedica.setId(rst.getInt(1));
                //     }
                // }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

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

    /*
    public ArrayList<FichaMedica> getAllFichaMedica() {

        ArrayList<FichaMedica> telefones = new ArrayList<FichaMedica>();

        try {
            String sql = "SELECT id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame FROM ficha_medica";

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

                    FichaMedica t = new FichaMedica(id, altura, peso, imc, tipo_sanguineo, contato_emergencia, data_exame);
                    telefones.add(t);
                }
            }
            return telefones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

    // public ArrayList<FichaMedica> retriveAllPorPessoa(Aluno Aluno) {

    //     ArrayList<FichaMedica> telefones = new ArrayList<FichaMedica>();

    //     try {
    //         String sql = "SELECT id, tipo, codigo_pais, codigo_area, numero FROM telefone_professor WHERE id_professor = ?";

    //         try (PreparedStatement pstm = connection.prepareStatement(sql)) {
    //             pstm.setInt(1, Aluno.getId());
    //             pstm.execute();
    //             ResultSet rst = pstm.getResultSet();
    //             while (rst.next()) {
    //                 int tel_id = rst.getInt("id");
    //                 TipoTelefone tipo = TipoTelefone.values()[rst.getInt("tipo")];
    //                 int cod_pais = rst.getInt("codigo_pais");
    //                 int cod_area = rst.getInt("codigo_area");
    //                 int numero = rst.getInt("numero");
    //                 int id_professor = rst.getInt("id_professor");

    //                 FichaMedica t = new FichaMedica(tel_id, tipo, cod_pais, cod_area, numero, id_professor);
    //                 telefones.add(t);
    //             }
    //         }
    //         return telefones;
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    // Função para Alterar o Peso do Aluno na Ficha Médica
    public void updatePesoFichaMedica(int idFichaMedica, Double novoPeso) {
        try {
            String sql = "UPDATE ficha_medica SET peso = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, novoPeso);
                pstm.setInt(2, idFichaMedica);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar a Altura do Aluno na Ficha Médica
    public void updateAlturaFichaMedica(int idFichaMedica, Double novaAltura) {
        try {
            String sql = "UPDATE ficha_medica SET altura = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, novaAltura);
                pstm.setInt(2, idFichaMedica);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar o Contato de Emergência do Aluno na Ficha Médica
    public void updateContatoEmergenciaFichaMedica(int idFichaMedica, String novoContatoEmergencia) {
        try {
            String sql = "UPDATE ficha_medica SET contato_emergencia = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoContatoEmergencia);
                pstm.setInt(2, idFichaMedica);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar a Data do Exame na Ficha Médica
    public void updateDataExameFichaMedica(int idFichaMedica, LocalDate novaDataExame) {
        try {
            String sql = "UPDATE ficha_medica SET data_exame = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novaDataExame);
                pstm.setInt(2, idFichaMedica);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
