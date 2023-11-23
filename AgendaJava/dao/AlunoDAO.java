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

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while(rst.next()) {
                        aluno.setId(rst.getInt(1));
                        FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAO(connection);
                        fichaMedicaDAO.create(aluno.getFichaMedica(), aluno);                        
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que pega todos os Alunos Cadastrados
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

    public Aluno retriveAluno(int idAluno){

        try{
            String sql = "SELECT nome, cpf, telefone, data_nascimento, idade FROM aluno WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, idAluno);
                pstm.execute();

                ResultSet rst = pstm.getResultSet();

                rst.next();
                
                String nome = rst.getString("nome");
                String cpf = rst.getString("cpf");
                String telefone = rst.getString("telefone");
                LocalDate dataNascimento = rst.getObject("data_nascimento", LocalDate.class);
                int idade = rst.getInt("idade");
                
                Aluno professor = new Aluno(nome, cpf, telefone, dataNascimento, idade);
                return professor;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    // Função para Alterar o Telefone do Aluno
    public void updateTelefoneAluno(Aluno aluno, String novoTelefone) {
        try {
            String sql = "UPDATE aluno SET telefone = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoTelefone);
                pstm.setInt(2, aluno.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que Exclui um Aluno do Banco
    public void deleteAluno(Aluno aluno) {
        try {
            String sql = "DELETE FROM aluno WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aluno.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}