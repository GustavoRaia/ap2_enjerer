// import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.Aluno;
import modelo.AlunoTurma;
import modelo.ProfessorTurma;
import modelo.Aula;
import modelo.FichaMedica;
import modelo.Professor;
import modelo.Telefone;
import modelo.Turma;
import modelo.TipoTelefone;
import dao.ConnectionFactory;
import dao.FichaMedicaDAO;
import dao.ProfessorDAO;
import dao.ProfessorTurmaDAO;
import dao.TelefoneDAO;
import dao.TurmaDAO;
import dao.AlunoDAO;
import dao.AlunoTurmaDAO;
import dao.AulaDAO;

public class Principal {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        // Aluno com Ficha Médica =================================================================

        // Criando Objetos de Ficha Médica
        FichaMedica fichaMedica1 = new FichaMedica(1.65, 70.4, "A+", "+5521992542312", LocalDate.of(2023, 10, 10), 1);
        FichaMedica fichaMedica2 = new FichaMedica(1.70, 82.4, "AB", "+5521988556701", LocalDate.of(2023, 8, 10), 2);
        FichaMedica fichaMedica3 = new FichaMedica( 1.75, 68.0,"O+", "+5521990317908", LocalDate.of(2023, 11, 01), 3);
        FichaMedica fichaMedica4 = new FichaMedica(1.80, 75.5, "B-", "+5521982908204", LocalDate.of(2023, 10, 28), 4);

        // Criando Objeto de Alunos
        Aluno aluno1 = new Aluno("Amanda Senra", "00011122200", "+5521995926289", LocalDate.of(2000, 1, 01), fichaMedica2);        
        Aluno aluno2 = new Aluno("Gabriel Martinez", "00011122211", "+5521995926290", LocalDate.of(2001, 2, 05), fichaMedica1);
        Aluno aluno3 = new Aluno("Joao Curvello", "00011122222", "+5521995926273", LocalDate.of(2002, 3, 10), fichaMedica3);
        Aluno aluno4 = new Aluno("Joao Correia", "00011122233", "+5521995926221", LocalDate.of(2003, 4, 15), fichaMedica4);

        // Estabelecendo Conexao com o DAO de Aluno
        AlunoDAO alunoDAO = new AlunoDAO(connection);
        
        // Estabelecendo Conexao com o DAO de Ficha Médica
        FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAO(connection);

        // Criando os Objetos de Aluno no BD
        alunoDAO.createComFicha(aluno1);
        alunoDAO.createComFicha(aluno2);
        alunoDAO.createComFicha(aluno3);
        alunoDAO.createComFicha(aluno4);

        System.out.println("\nImprimindo os dados após o INSERT");
        imprimeDadosAluno(alunoDAO, fichaMedicaDAO);

        alunoDAO.updateTelefoneAluno(aluno2, "+5521995497954");

        fichaMedicaDAO.updateAlturaFichaMedica(aluno1.getFichaMedica(), 1.73);
        fichaMedicaDAO.updatePesoFichaMedica(aluno1.getFichaMedica(), 80.3);
        fichaMedicaDAO.updateContatoEmergenciaFichaMedica(aluno2.getFichaMedica(), "+5521923437798");
        fichaMedicaDAO.updateDataExameFichaMedica(aluno1.getFichaMedica(), LocalDate.of(2023, 11, 19));

        System.out.println("Imprimindo os dados após o UPDATE\n");
        System.out.println("- Altura Aluno 1 (1.7 -> 1.73)");
        System.out.println("- Peso Aluno 1 (82.4 -> 80.3)");
        System.out.println("- CTT Emergência Aluno 2 (+5521995926290 -> +5521923437798)");
        System.out.println("- Data do Exame Aluno 1 (2023-08-10 -> 2023-11-19)");
        imprimeDadosAluno(alunoDAO, fichaMedicaDAO);

        fichaMedicaDAO.deleteFichaMedica(fichaMedica1);

        System.out.println("Imprimindo os dados após o DELETE\n");
        System.out.println("- Ficha Médica do Aluno 2 ");
        imprimeDadosAluno(alunoDAO, fichaMedicaDAO);

        // ========================================================================================


        // Professor e Telefones ==================================================================

        // Criando Objetos de Professor com Telefones
        Professor professor1 = new Professor("Frederico Guedes", "00011009479", LocalDate.of(2000, 1, 01), 2200);
        Telefone telefone10 = new Telefone(TipoTelefone.Celular, 55, 21, 982141939, 1);
        Telefone telefone11 = new Telefone(TipoTelefone.Celular, 55, 21, 999309064, 1);        
        professor1.addTelefone(telefone10);
        professor1.addTelefone(telefone11);

        // Criando Objetos de Professor com Telefones
        Professor professor2 = new Professor("Waldemar Guimaraes", "00011011908", LocalDate.of(2001, 2, 05), 2000);
        Telefone telefone20 = new Telefone(TipoTelefone.Celular, 55, 21, 989963144, 2);
        Telefone telefone21 = new Telefone(TipoTelefone.Residencial, 55, 21, 222983121, 2);
        Telefone telefone22 = new Telefone(TipoTelefone.Profissional, 55, 21, 960197272, 2);
        professor2.addTelefone(telefone20);
        professor2.addTelefone(telefone21);
        professor2.addTelefone(telefone22);

        // Criando Objetos de Professor com Telefones
        Professor professor3 = new Professor("Bruno Silva", "00011080732", LocalDate.of(2002, 3, 10), 3500);
        Telefone telefone30 = new Telefone(TipoTelefone.Celular, 55, 21, 994378235, 3);
        professor3.addTelefone(telefone30);

        // // Criando Objetos de Professor com Telefones
        Professor professor4 = new Professor("Enrico Maragno", "00011675800", LocalDate.of(2003, 4, 15), 2500);
        Telefone telefone40 = new Telefone(TipoTelefone.Celular, 55, 21, 964695794, 4);
        Telefone telefone41 = new Telefone(TipoTelefone.Celular, 55, 21, 964695790, 4);
        professor4.addTelefone(telefone40);
        professor4.addTelefone(telefone41);

        // Estabelecendo Conexão com o DAO de Professor
        ProfessorDAO professorDAO = new ProfessorDAO(connection);
        TelefoneDAO telefoneDAO = new TelefoneDAO(connection);

        // Criando os Objetos de Professor no BD
        professorDAO.createComTelefone(professor1);
        professorDAO.createComTelefone(professor2);
        professorDAO.createComTelefone(professor3);
        professorDAO.createComTelefone(professor4);

        // Chamando função de Printar utilizando ArrayList de professores como parâmetro
        System.out.println("Imprimindo os dados após o INSERT");
        imprimeDadosProfessor(professorDAO);

        professorDAO.updateSalarioProfessor(professor1, 5000.0);
        professorDAO.updateSalarioProfessor(professor2, 3500.0);

        telefoneDAO.updateTelefoneProfessor(professor2, "222983121", "997356384");

        telefoneDAO.updateTipoTelefoneProfessor(professor3, "994378235", 2);

        // Chamando função de Printar utilizando ArrayList de professores como parâmetro
        System.out.println("Imprimindo os Dados após o UPDATE\n");
        System.out.println("- Salário Professor 1 (2200 -> 5000)");
        System.out.println("- Salário Professor 2 (2000 -> 3500)");
        System.out.println("- Telefone Professor 2 (+5521222983121 -> +5521997356384)");
        System.out.println("- Tipo Telefone Professor 3 (Celular -> Profissional)");
        imprimeDadosProfessor(professorDAO);

        // Deleta a Tabela de Telefones antes do Professor, para não quebrar a FK
        professorDAO.deleteProfessor(professor4);

        // Deleta um único telefone de um Professor
        telefoneDAO.deleteTelefone(professor2, "989963144");

        // Chamando função de Printar utilizando ArrayList de professores como parâmetro
        System.out.println("Imprimindo os Dados após o DELETE\n");
        System.out.println("- Professor 1 excluído (Frederico Guedes)");
        System.out.println("- Telefone 2 do Professor 2 excluído (+5521222983121)");
        imprimeDadosProfessor(professorDAO);

        // ========================================================================================
        

        // Aulas com Alunos e Professores =========================================================

        // Criando Objetos de Aulas marcadas
        Aula aula1 = new Aula(aluno4, professor2, "Personal", LocalDate.of(2023, 11, 19) ,LocalTime.of(21, 0, 0), LocalTime.of(23, 0, 0));
        Aula aula2 = new Aula(aluno4, professor2, "Treinão de Perna", LocalDate.of(2023, 11, 20) ,LocalTime.of(15, 0, 0), LocalTime.of(16, 0, 0));
        Aula aula3 = new Aula(aluno4, professor3, "Personal", LocalDate.of(2023, 11, 21) ,LocalTime.of(20, 0, 0), LocalTime.of(22, 0, 0));

        // Estabelecendo Conexão com o DAO de Aula
        AulaDAO aulaDAO = new AulaDAO(connection);

        // Criando os Objetos de Aula no BD
        aulaDAO.createAula(aula1, aluno4, professor2);
        aulaDAO.createAula(aula2, aluno2, professor2);
        aulaDAO.createAula(aula3, aluno2, professor3);

        System.out.println("Imprimindo os Dados após o INSERT\n");
        imprimeAulas(aulaDAO, professorDAO, alunoDAO);
        
        aulaDAO.updateDiaAula(aula2, LocalDate.of(2023, 11, 24));
        aulaDAO.updateHorarioInicio(aula1, LocalTime.of(20, 30, 0));
        aulaDAO.updateHorarioFim(aula1, LocalTime.of(22, 0, 0));
        aulaDAO.updateTipoAula(aula3, "Treino de Ombro");

        System.out.println("Imprimindo os Dados após o UPDATE\n");
        System.out.println("- Dia da Aula 2 (2023-11-20 -> 2023-11-24)");
        System.out.println("- Horário de Início da Aula 1 (21:00 -> 20:30)");
        System.out.println("- Horário de Fim da Aula 1 (23:00 -> 22:00)");
        System.out.println("- Tipo da Aula 3 (Personal -> Treino de Ombro)");
        imprimeAulas(aulaDAO, professorDAO, alunoDAO);

        aulaDAO.cancelarAula(aula2);

        System.out.println("Imprimindo os Dados após o DELETE\n");
        System.out.println("- Aula 2 Excluída (Treinão de Perna - Waldemar Guimaraes)");
        imprimeAulas(aulaDAO, professorDAO, alunoDAO);

        // ============================================================================================


        // Turmas com Alunos e Professores ============================================================

        // Criando Objetos de Turma
        Turma turma1 = new Turma("Dança", LocalDate.of(2023, 11, 24), LocalTime.of(16,0,0), LocalTime.of(17,0,0));
        Turma turma2 = new Turma("Zumba", LocalDate.of(2023, 11, 24), LocalTime.of(18,0,0), LocalTime.of(19,0,0));
        Turma turma3 = new Turma("Pilates", LocalDate.of(2023, 11, 23), LocalTime.of(20,0,0), LocalTime.of(22,0,0));
        Turma turma4 = new Turma("Dança", LocalDate.of(2023, 11, 26), LocalTime.of(10,0,0), LocalTime.of(11,0,0));

        // Estabelecendo Conexao com o DAO de Turma
        TurmaDAO turmaDAO = new TurmaDAO(connection);

        // Criando os Objetos de Turma no BD
        turmaDAO.createTurma(turma1);
        turmaDAO.createTurma(turma2);
        turmaDAO.createTurma(turma3);
        turmaDAO.createTurma(turma4);

        // Criando Objetos da Relação Aluno Turma
        AlunoTurma alunoTurma1 = new AlunoTurma(aluno1, 1);
        AlunoTurma alunoTurma2 = new AlunoTurma(aluno4, 1);
        AlunoTurma alunoTurma3 = new AlunoTurma(aluno1, 2);
        AlunoTurma alunoTurma4 = new AlunoTurma(aluno2, 2);
        AlunoTurma alunoTurma5 = new AlunoTurma(aluno3, 2);
        AlunoTurma alunoTurma6 = new AlunoTurma(aluno4, 2);
        AlunoTurma alunoTurma7 = new AlunoTurma(aluno1, 3);
        AlunoTurma alunoTurma8 = new AlunoTurma(aluno3, 3);
        AlunoTurma alunoTurma9 = new AlunoTurma(aluno2, 4);
        AlunoTurma alunoTurma10 = new AlunoTurma(aluno3, 4);
        AlunoTurma alunoTurma11 = new AlunoTurma(aluno4, 4);

        AlunoTurmaDAO alunoTurmaDAO = new AlunoTurmaDAO(connection);

        alunoTurmaDAO.createAlunoTurma(alunoTurma1);
        alunoTurmaDAO.createAlunoTurma(alunoTurma2);
        alunoTurmaDAO.createAlunoTurma(alunoTurma3);
        alunoTurmaDAO.createAlunoTurma(alunoTurma4);
        alunoTurmaDAO.createAlunoTurma(alunoTurma5);
        alunoTurmaDAO.createAlunoTurma(alunoTurma6);
        alunoTurmaDAO.createAlunoTurma(alunoTurma7);
        alunoTurmaDAO.createAlunoTurma(alunoTurma8);
        alunoTurmaDAO.createAlunoTurma(alunoTurma9);
        alunoTurmaDAO.createAlunoTurma(alunoTurma10);
        alunoTurmaDAO.createAlunoTurma(alunoTurma11);
    
        // Criando Objetos da Relação Professor Turma
        ProfessorTurma professorTurma1 = new ProfessorTurma(professor3, 3);
        ProfessorTurma professorTurma2 = new ProfessorTurma(professor2, 4);
        ProfessorTurma professorTurma3 = new ProfessorTurma(professor1, 1);
        ProfessorTurma professorTurma4 = new ProfessorTurma(professor2, 1);
        ProfessorTurma professorTurma5 = new ProfessorTurma(professor3, 2);
        ProfessorTurma professorTurma6 = new ProfessorTurma(professor1, 2);

        ProfessorTurmaDAO professorTurmaDAO = new ProfessorTurmaDAO(connection);

        professorTurmaDAO.createProfessorTurma(professorTurma1);
        professorTurmaDAO.createProfessorTurma(professorTurma2);
        professorTurmaDAO.createProfessorTurma(professorTurma3);
        professorTurmaDAO.createProfessorTurma(professorTurma4);
        professorTurmaDAO.createProfessorTurma(professorTurma5);
        professorTurmaDAO.createProfessorTurma(professorTurma6);

        System.out.println("Imprimindo os Dados após o INSERT");
        imprimeTurmas(turmaDAO, alunoTurmaDAO, professorTurmaDAO, alunoDAO, professorDAO);

        turmaDAO.updateHorarioInicio(4, LocalTime.of(11,0,0));
        turmaDAO.updateHorarioFim(4, LocalTime.of(12,0,0));
        turmaDAO.updateTipoTurma(1, "Cardiozão");

        // turmaDAO.updateHorarioInicio(turma4, LocalTime.of(11,0,0));
        // turmaDAO.updateHorarioFim(turma4, LocalTime.of(12,0,0));
        // turmaDAO.updateTipoTurma(turma1, "Cardiozão");

        System.out.println("Imprimindo os Dados após o UPDATE\n");
        System.out.println("- Horário de Início da Turma 4 (10:00 -> 11:00)");
        System.out.println("- Horário de Fim da Turma 4 (11:00 -> 12:00)");
        System.out.println("- Tipo da Turma 1 (Dança -> Cardiozão)");
        imprimeTurmas(turmaDAO, alunoTurmaDAO, professorTurmaDAO, alunoDAO, professorDAO);

        turmaDAO.deleteTurma(3);
        professorTurmaDAO.deleteProfessorTurma(6);
        alunoTurmaDAO.deleteAlunoTurma(11);

        // turmaDAO.deleteTurma(turma3);
        // professorTurmaDAO.deleteProfessorTurma(professorTurma6);
        // alunoTurmaDAO.deleteAlunoTurma(alunoTurma11);

        System.out.println("Imprimindo os Dados após o DELETE\n");
        System.out.println("Turma 3 Excluída (Pilates - 2023-11-23)");
        System.out.println("Aluno da Turma 4 Excluído (Joao Correia)");
        System.out.println("Professor da Turma 2 Excluído (Frederico Guedes)");
        imprimeTurmas(turmaDAO, alunoTurmaDAO, professorTurmaDAO, alunoDAO, professorDAO);

    }

    // Funções de Impressão de Dados na Tela ======================================================

    // Função para Imprimir os Dados do Aluno juntamente com a Ficha Médica.
    private static void imprimeDadosAluno(AlunoDAO alunoDAO, FichaMedicaDAO fichaMedicaDAO) {
        ArrayList<Aluno> alunos = alunoDAO.getAllAluno();
        for(Aluno a : alunos) {
            System.out.println("\n======= Dados do Aluno " + a.getId() + " ==============================\n");
            System.out.println("\tNome: \t\t\t" +  a.getNome());
            System.out.println("\tCPF: \t\t\t" + a.getCpf());
            System.out.println("\tTelefone: \t\t" + a.getTelefone());
            System.out.println("\tNascimento: \t\t" + a.getDataNascimento());
            System.out.println("\tIdade: \t\t\t" + a.getIdade());
            
            if(fichaMedicaDAO.getFichaMedicaPorAluno(a).getId() != 0) {
                System.out.println("\n\t------------- Ficha Médica ---------------");
                System.out.println("\tAltura: \t\t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getAltura());
                System.out.println("\tPeso: \t\t\t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getPeso());
                System.out.println("\tIMC: \t\t\t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getImc());
                System.out.println("\tTipo Sanguíneo: \t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getTipoSanguineo());
                System.out.println("\tContato de Emergência: \t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getContatoEmergencia());
                System.out.println("\tData do Exame: \t\t" + fichaMedicaDAO.getFichaMedicaPorAluno(a).getDataExame());
                System.out.println("\t------------------------------------------");                   
            } else {
                System.out.println("\n\t------------- Ficha Médica ---------------\n");
                System.out.println("\n\t------------------------------------------");              
            }
        }
        System.out.println("\n=======================================================\n");
    }

    // Função para Imprimir os Dados do Professor juntamente com os Telefones cadastrados.
    private static void imprimeDadosProfessor(ProfessorDAO professorDAO) {
        ArrayList<Professor> professores = professorDAO.retriveAllComTelefone();
        for (Professor professor : professores) {
            System.out.println("\n======= Dados do Professor " + professor.getId() + " =====================\n");
            System.out.println("\tId: \t\t" + professor.getId());
            System.out.println("\tNome: \t\t" + professor.getNome());
            System.out.println("\tCPF: \t\t" + professor.getCpf());
            System.out.println("\tNascimento: \t" + professor.getDataNascimento());
            System.out.println("\tIdade: \t\t" + professor.getIdade());
            System.out.println("\tSalário: \t" + professor.getSalario());

            System.out.println("\n\t--------- Lista de Telefones ---------"); 

            ArrayList<Telefone> telefones = professor.getTelefones();
            for (int i = 0; i < telefones.size(); i++) {
                Telefone telefone = telefones.get(i);
                System.out.println("\n\tTelefone " + (i+1) + ":\t" + telefone);
            }
            System.out.println("\n\t--------------------------------------"); 
        }
        System.out.println("\n==================================================\n");
    }

    // Função para Imprimir as aulas Cadastradas juntamente com os alunos e professores
    private static void imprimeAulas(AulaDAO aulaDAO, ProfessorDAO professorDAO, AlunoDAO alunoDAO) {
        ArrayList<Aula> aulas = aulaDAO.getAllAulas();
        for (Aula a : aulas) {
            System.out.println("\n======= Dados da Aula " + a.getId() + " ===============================\n");
            System.out.println("\tAluno: \t\t\t" + alunoDAO.retriveAluno(a.getIdAluno()).getNome());
            System.out.println("\tProfessor: \t\t" + professorDAO.retriveProfessor(a.getIdProfessor()).getNome());
            System.out.println("\tTipo da Aula: \t\t" + a.getTipo());
            System.out.println("\tDia da Aula: \t\t" + a.getDiaAula());
            System.out.println("\tHorário de Início: \t" + a.getHorarioInicio());
            System.out.println("\tHorário de Fim: \t" + a.getHorarioFim());
        }
        System.out.println("\n========================================================\n");
    }

    private static void imprimeTurmas(TurmaDAO turmaDAO, AlunoTurmaDAO alunoTurmaDAO, ProfessorTurmaDAO professorTurmaDAO, AlunoDAO alunoDAO, ProfessorDAO professorDAO) {
        ArrayList<Turma> turmas = turmaDAO.retriveAllTurmas();
        ArrayList<AlunoTurma> alunosTurmas = alunoTurmaDAO.retriveAlunoTurma();
        ArrayList<ProfessorTurma> professoresTurmas = professorTurmaDAO.retriveProfessorTurma();

        for(Turma t : turmas) {
            System.out.println("\n======= Dados da Turma " + t.getId() + " ===============================\n");
            System.out.println("\tId: \t\t\t" + t.getId());
            System.out.println("\tTipo: \t\t\t" + t.getTipo());
            System.out.println("\tDia da Aula: \t\t" + t.getDiaAula());
            System.out.println("\tHorario de Inicio: \t" + t.getHorarioInicio());
            System.out.println("\tHorario de Fim: \t" + t.getHorarioFim());

            System.out.println("\n\t--------------- Alunos ---------------\n"); 
            for(AlunoTurma at : alunosTurmas) {
                if(at.getIdTurma() == t.getId()) {
                    System.out.println("\t" + alunoDAO.retriveAluno(at.getIdAluno()).getNome());
                }
            }

            System.out.println("\n\t------------- Professores ------------\n"); 
            for(ProfessorTurma pt : professoresTurmas) {
                if(pt.getIdTurma() == t.getId()) {

                    System.out.println("\t" + professorDAO.retriveProfessor(pt.getIdProfessor()).getNome());
                }
            }
            System.out.println("\n\t--------------------------------------"); 
        }
        System.out.println("\n========================================================\n");
    }
}