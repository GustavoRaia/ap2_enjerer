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

public class AulaDAO {

    private Connection connection;

    public AulaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAula(Aula aula) {
        try {
            String sql = "INSERT INTO aula (id_aluno, id_professor, tipo, dia_aula, horario_inicio, horario_fim) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aula.getIdAluno());
                pstm.setInt(2, aula.getIdProfessor());
                pstm.setString(3, aula.getTipo());
                pstm.setObject(4, aula.getDiaAula());
                pstm.setObject(5, aula.getHorarioInicio());
                pstm.setObject(6, aula.getHorarioFim());

                pstm.execute();

                // try (ResultSet rst = pstm.getGeneratedKeys()) {
                //     while (rst.next()) {
                //         evento.setId(rst.getInt(1));
                //     }
                // }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
    
}
