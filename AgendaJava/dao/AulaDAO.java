package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import modelo.Aula;
import modelo.Professor;
import modelo.Aluno;

public class AulaDAO {

    private Connection connection;

    public AulaDAO(Connection connection) {
        this.connection = connection;
    }

    // Cria uma Aula e Cadastra no Banco de Dados
    public void createAula(Aula aula, Aluno aluno, Professor professor) {
        try {
            String sql = "INSERT INTO aula (id_aluno, id_professor, tipo, dia_aula, horario_inicio, horario_fim) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aluno.getId());
                pstm.setInt(2, professor.getId());
                pstm.setString(3, aula.getTipo());
                pstm.setObject(4, aula.getDiaAula());
                pstm.setObject(5, aula.getHorarioInicio());
                pstm.setObject(6, aula.getHorarioFim());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aula.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Pega Todas as Aulas Cadastradas
    public ArrayList<Aula> getAllAulas() {

        ArrayList<Aula> aulas = new ArrayList<Aula>();

        try {
            // String sql = "SELECT id, id_aluno, id_professor, tipo, dia_aula, horario_inicio, horario_fim FROM aula, professor, aluno " + 
            // "WHERE aula.id_aluno = aluno.id AND aula.id_professor = professor.id";
            String sql = "SELECT aula.id, id_aluno, id_professor, tipo, dia_aula, horario_inicio, horario_fim, professor.nome FROM aula, professor " +
                         "WHERE aula.id_professor = professor.id";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    int idAluno = rst.getInt("id_aluno");
                    int idProfessor = rst.getInt("id_professor");
                    String tipo = rst.getString("tipo");
                    LocalDate diaAula = rst.getObject("dia_aula", LocalDate.class);
                    LocalTime horarioInicio = rst.getObject("horario_inicio", LocalTime.class);
                    LocalTime horarioFim = rst.getObject("horario_fim", LocalTime.class);
                    
                    Aula a = new Aula(id, idAluno, idProfessor, tipo, diaAula, horarioInicio, horarioFim);

                    aulas.add(a);
                }
            }
            return aulas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateDiaAula(Aula aula, LocalDate novoDia) {
        try {
            String sql = "UPDATE aula SET dia_aula = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoDia);
                pstm.setInt(2, aula.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateHorarioInicio(Aula aula, LocalTime novoHorarioInicio) {
        try {
            String sql = "UPDATE aula SET horario_inicio = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioInicio);
                pstm.setInt(2, aula.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateHorarioFim(Aula aula, LocalTime novoHorarioFim) {
        try {
            String sql = "UPDATE aula SET horario_fim = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoHorarioFim);
                pstm.setInt(2, aula.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateTipoAula(Aula aula, String novoTipoAula) {
        try {
            String sql = "UPDATE aula SET tipo = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, novoTipoAula);
                pstm.setInt(2, aula.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que Deleta a Aula do Banco
    public void cancelarAula(Aula aula) {
        try {
            String sql = "DELETE FROM aula WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aula.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
