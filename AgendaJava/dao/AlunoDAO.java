package dao;

import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Aluno;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    /*
    public void createAluno(Aluno aluno) {

        try {
            String sql = "INSERT INTO aluno (nome, cpf, telefone, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setString(3, aluno.getTelefone());
                pstm.setObject(4, aluno.getDataNascimento());
                pstm.setInt(5, aluno.getIdade());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */

    public void createComFicha(Aluno aluno) {

        try {
            String sql = "INSERT INTO aluno (nome, cpf, telefone, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setString(3, aluno.getTelefone());
                pstm.setObject(4, aluno.getDataNascimento());
                pstm.setInt(5, aluno.getIdade());

                pstm.execute();

                // FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAO(connection);
                // fichaMedicaDAO.create(fichaMedica, aluno);         

                // try (ResultSet rst = pstm.getGeneratedKeys()) {
                //     while (rst.next()) {
                //         aluno.setId(rst.getInt(1));
                //         for(FichaMedica fichaMedica : aluno;) {
                //         FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAO(connection);
                //         fichaMedicaDAO.create(fichaMedica, aluno);                            
                //         }
                //     }
                // }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> getAllAluno() {

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        try {
            String sql = "SELECT id, nome, cpf, telefone, data_nascimento, idade FROM aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    String telefone = rst.getString("telefone");
                    LocalDate data = rst.getObject("data_nascimento", LocalDate.class);
                    int idade = rst.getInt("idade");
                    
                    Aluno a = new Aluno(id, nome, cpf, telefone, data, idade);

                    alunos.add(a);
                }
            }
            return alunos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Alterar o Peso do Aluno
    public void updateTelefoneAluno(int idAluno, String novoTelefone) {
        try {
            String sql = "UPDATE aluno SET telefone = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoTelefone);
                pstm.setInt(2, idAluno);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}