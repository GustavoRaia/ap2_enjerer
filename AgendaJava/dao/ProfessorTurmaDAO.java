package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ProfessorTurma;

public class ProfessorTurmaDAO {
    private Connection connection;

    public ProfessorTurmaDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Função para criar uma Relação Professor Turma
    public void createProfessorTurma(ProfessorTurma professorTurma) {

        try {
            String sql = "INSERT INTO professor_turma (id_professor, id_turma) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, professorTurma.getIdProfessor());
                pstm.setInt(2, professorTurma.getIdTurma());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Deletar uma Relação Professor Turma
    public void deleteProfessorTurma(ProfessorTurma professorTurma) {
        try {
            String sql = "DELETE FROM professor_turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, professorTurma.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Deletar uma Relação Professor Turma
    public void deleteProfessorTurma(int id) {
        try {
            String sql = "DELETE FROM professor_turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, id);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Pega Todas as Relações Professor Turma
    public ArrayList<ProfessorTurma> retriveProfessorTurma() {

        ArrayList<ProfessorTurma> professorTurmas = new ArrayList<ProfessorTurma>();

        try {
            String sql = "SELECT id, id_professor, id_turma FROM professor_turma";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    int idProfessor = rst.getInt("id_professor");
                    int idTurma = rst.getInt("id_turma");
                    
                    ProfessorTurma a = new ProfessorTurma(id, idProfessor, idTurma);

                    professorTurmas.add(a);
                }
            }
            return professorTurmas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
