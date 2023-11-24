package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.Turma;

public class TurmaDAO {

    private Connection connection;

    public TurmaDAO(Connection connection) {
        this.connection = connection;
    }
    
    // Função para criar uma turma
    public void createTurma(Turma turma) {

        try {
            String sql = "INSERT INTO turma (tipo, dia_aula, horario_inicio, horario_fim) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, turma.getTipo());
                pstm.setObject(2, turma.getDiaAula());
                pstm.setObject(3, turma.getHorarioInicio());
                pstm.setObject(4, turma.getHorarioFim());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função para Deletar uma Turma
    public void deleteTurma(int id) {
        try {
            String sql = "DELETE FROM turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, id);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Função para Deletar uma Turma
    public void deleteTurma(Turma turma) {
        try {
            String sql = "DELETE FROM turma WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, turma.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Pega Todas as Turmas Cadastradas
    public ArrayList<Turma> retriveAllTurmas() {

        ArrayList<Turma> turmas = new ArrayList<Turma>();

        try {
            String sql = "SELECT id, tipo, dia_aula, horario_inicio, horario_fim FROM turma;";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String tipo = rst.getString("tipo");
                    LocalDate diaAula = rst.getObject("dia_aula", LocalDate.class);
                    LocalTime horarioInicio = rst.getObject("horario_inicio", LocalTime.class);
                    LocalTime horarioFim = rst.getObject("horario_fim", LocalTime.class);
                    
                    Turma a = new Turma(id, tipo, diaAula, horarioInicio, horarioFim);

                    turmas.add(a);
                }
            }
            return turmas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateHorarioInicio(Turma turma, LocalTime novoHorarioInicio) {
        try {
            String sql = "UPDATE turma SET horario_inicio = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioInicio);
                pstm.setInt(2, turma.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateHorarioInicio(int id, LocalTime novoHorarioInicio) {
        try {
            String sql = "UPDATE turma SET horario_inicio = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioInicio);
                pstm.setInt(2, id);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateHorarioFim(Turma turma, LocalTime novoHorarioFim) {
        try {
            String sql = "UPDATE turma SET horario_fim = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioFim);
                pstm.setInt(2, turma.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateHorarioFim(int id, LocalTime novoHorarioFim) {
        try {
            String sql = "UPDATE turma SET horario_fim = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioFim);
                pstm.setInt(2, id);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTipoTurma(Turma turma, String novoTipo) {
        try {
            String sql = "UPDATE turma SET tipo = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoTipo);
                pstm.setInt(2, turma.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTipoTurma(int id, String novoTipo) {
        try {
            String sql = "UPDATE turma SET tipo = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoTipo);
                pstm.setInt(2, id);
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
