package dao;

import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Professor;
import modelo.Telefone;
import modelo.TipoTelefone;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void createComTelefone(Professor professor) {

        try {
            String sql = "INSERT INTO professor (nome, cpf, data_nascimento, idade, salario) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getCpf());
                pstm.setObject(3, professor.getDataNascimento());
                pstm.setInt(4, professor.getIdade());
                pstm.setDouble(5, professor.getSalario());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        professor.setId(rst.getInt(1));
                        for (Telefone telefone : professor.getTelefones()) {
                            TelefoneDAO tdao = new TelefoneDAO(connection);
                            tdao.create(telefone, professor);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Retorna uma Busca de Professor pelo seu ID
    public Professor retriveProfessor(int iDInstrutor){

        try{
            String sql = "SELECT nome, cpf, data_nascimento, salario FROM professor WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, iDInstrutor);
                pstm.execute();

                ResultSet rst = pstm.getResultSet();

                rst.next();
                 
                String nome = rst.getString("nome");
                String cpf = rst.getString("cpf");
                LocalDate dataNascimento = rst.getObject("data_nascimento", LocalDate.class);
                Double salario = rst.getDouble("salario");
                
                Professor professor = new Professor(nome, cpf, dataNascimento, salario);
                return professor;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    // Recupera os Dados do Professor sem os Telefones Cadastrados
    public ArrayList<Professor> retriveAllSemTelefone() {

        ArrayList<Professor> professores = new ArrayList<Professor>();

        try {
            String sql = "SELECT id, nome, cpf, data_nascimento, idade, salario FROM professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    LocalDate data = rst.getObject("data_nascimento", LocalDate.class);
                    int idade = rst.getInt("idade");
                    Double salario = rst.getDouble("salario");
                    Professor p = new Professor(id, nome, cpf, data, idade, salario);
                    professores.add(p);
                }
            }
            return professores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Recupera os dados do Professor com os Telefones Cadastrados
    public ArrayList<Professor> retriveAllComTelefone() {

        ArrayList<Professor> professores = new ArrayList<Professor>();
        Professor ultima = null;

        try {

            String sql = "SELECT p.id, p.nome, p.cpf, p.data_nascimento, p.idade, p.salario, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero, t.id_professor "
                    + "FROM professor AS p "
                    + "LEFT JOIN telefone_professor AS t ON p.id = t.id_professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || ultima.getId() != rst.getInt(1)) {
                            int p_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            LocalDate data = rst.getObject(4, LocalDate.class);
                            int idade = rst.getInt(5);
                            Double salario = rst.getDouble(6);
                            Professor p = new Professor(p_id, nome, cpf, data, idade, salario);

                            professores.add(p);
                            ultima = p;
                        }

                        if (rst.getInt(6) != 0) {
                            int tel_id = rst.getInt(7);
                            TipoTelefone tipo = TipoTelefone.values()[rst.getInt(8)];
                            int cod_pais = rst.getInt(9);
                            int cod_area = rst.getInt(10);
                            int numero = rst.getInt(11);
                            int id_professor = rst.getInt(12);

                            Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero, id_professor);
                            ultima.addTelefone(t);
                        }
                    }
                }
                return professores;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que deleta o Professor pelo ID
    public void deleteProfessor(Professor professor) {
        try {
            String sql = "DELETE FROM professor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, professor.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Função para Alterar o Salário do Professor
    public void updateSalarioProfessor(Professor professor, Double novoSalario) {
        try {
            String sql = "UPDATE professor SET salario = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, novoSalario);
                pstm.setInt(2, professor.getId());
                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
