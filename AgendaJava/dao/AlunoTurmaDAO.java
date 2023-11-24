package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.AlunoTurma;

public class AlunoTurmaDAO {
    private Connection connection;

    public AlunoTurmaDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Função para criar uma Relação Aluno Turma
    public void createAlunoTurma(AlunoTurma alunoTurma) {

        try {
            String sql = "INSERT INTO aluno_turma (id_aluno, id_turma) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, alunoTurma.getIdAluno());
                pstm.setInt(2, alunoTurma.getIdTurma());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Deletar uma Relação Aluno Turma
    public void deleteAlunoTurma(AlunoTurma alunoTurma) {
        try {
            String sql = "DELETE FROM aluno_turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, alunoTurma.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Deletar uma Relação Aluno Turma
    public void deleteAlunoTurma(int id) {
        try {
            String sql = "DELETE FROM aluno_turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, id);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Pega Todas as Relações Aluno Turma
    public ArrayList<AlunoTurma> retriveAlunoTurma() {

        ArrayList<AlunoTurma> alunoTurmas = new ArrayList<AlunoTurma>();

        try {
            String sql = "SELECT id, id_aluno, id_turma FROM aluno_turma";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    int idAluno = rst.getInt("id_aluno");
                    int idTurma = rst.getInt("id_turma");
                    
                    AlunoTurma a = new AlunoTurma(id, idAluno, idTurma);

                    alunoTurmas.add(a);
                }
            }
            return alunoTurmas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
