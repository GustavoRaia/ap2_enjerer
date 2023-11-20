// import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// import modelo.Evento;
// import modelo.Aluno;
import modelo.Aluno;
import modelo.FichaMedica;
import modelo.Professor;
import modelo.Telefone;
import modelo.TipoTelefone;
import dao.ConnectionFactory;
import dao.FichaMedicaDAO;
import dao.ProfessorDAO;
import dao.AlunoDAO;

public class Principal {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        // Aluno com Ficha Médica =================================================================

        // Criando Objeto de 9 alunos
        Aluno aluno1 = new Aluno("Amanda Senra", "00011122200", "+5521995926289", LocalDate.of(2000, 1, 01));        
        Aluno aluno2 = new Aluno("Gabriel Martinez", "00011122211", "+5521995926290", LocalDate.of(2001, 2, 05));
        Aluno aluno3 = new Aluno("Joao Curvello", "00011122222", "+5521995926273", LocalDate.of(2002, 3, 10));
        Aluno aluno4 = new Aluno("Joao Correia", "00011122233", "+5521995926221", LocalDate.of(2003, 4, 15));
        Aluno aluno5 = new Aluno("Joao Constant", "00011122244", "+5521995960288", LocalDate.of(2004, 5, 20));
        Aluno aluno6 = new Aluno("Puterrier da Malária", "00011122255", "+5521995066272", LocalDate.of(2005, 6, 25));
        Aluno aluno7 = new Aluno("Thaís Bustamante", "00011122266", "+5521995926107", LocalDate.of(2000, 7, 30));
        Aluno aluno8 = new Aluno("Théo Mauricio", "00011122277", "+5521995080186", LocalDate.of(2001, 8, 01));
        Aluno aluno9 = new Aluno("Victor Lobianco", "00011122288", "+5521995766901", LocalDate.of(2002, 9, 05));

        // Estabelecendo Conexao com o DAO de Aluno
        AlunoDAO alunoDAO = new AlunoDAO(connection);

        // Criando os Objetos de Aluno no BD
        alunoDAO.createComFicha(aluno1);
        alunoDAO.createComFicha(aluno2);
        alunoDAO.createComFicha(aluno3);
        alunoDAO.createComFicha(aluno4);
        alunoDAO.createComFicha(aluno5);
        alunoDAO.createComFicha(aluno6);
        alunoDAO.createComFicha(aluno7);
        alunoDAO.createComFicha(aluno8);
        alunoDAO.createComFicha(aluno9);

        // Criando 9 objetos de Ficha Médica
        FichaMedica fichaMedica1 = new FichaMedica(1.65, 70.4, "A+", "+5521992542312", LocalDate.of(2023, 10, 10), 1);
        FichaMedica fichaMedica2 = new FichaMedica(1.70, 82.4, "AB", "+5521988556701", LocalDate.of(2023, 11, 10), 2);
        FichaMedica fichaMedica3 = new FichaMedica( 1.75, 68.0,"O+", "+5521990317908", LocalDate.of(2023, 11, 01), 3);
        FichaMedica fichaMedica4 = new FichaMedica(1.80, 75.5, "B-", "+5521982908204", LocalDate.of(2023, 10, 28), 4);
        FichaMedica fichaMedica5 = new FichaMedica(1.81, 80.0, "A-", "+5521981770203", LocalDate.of(2023, 10, 28), 5);
        FichaMedica fichaMedica6 = new FichaMedica(1.79, 78.8, "B+", "+5521982413202", LocalDate.of(2023, 9, 30), 6);
        FichaMedica fichaMedica7 = new FichaMedica(1.56, 61.2, "B-", "+5521921564201", LocalDate.of(2023, 11, 16), 7);
        FichaMedica fichaMedica8 = new FichaMedica(1.73, 86.5, "AB+", "+5521982832543", LocalDate.of(2023, 11, 20), 8);
        FichaMedica fichaMedica9 = new FichaMedica(2.00, 105.9, "O-", "+5521991567088", LocalDate.of(2023, 11, 19), 9);

        // Estabelecendo Conexao com o DAO de Ficha Médica
        FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAO(connection);

        // Criando os Objetos de Ficha Médica no BD
        fichaMedicaDAO.create(fichaMedica1);
        fichaMedicaDAO.create(fichaMedica2);
        fichaMedicaDAO.create(fichaMedica3);
        fichaMedicaDAO.create(fichaMedica4);
        fichaMedicaDAO.create(fichaMedica5);
        fichaMedicaDAO.create(fichaMedica6);
        fichaMedicaDAO.create(fichaMedica7);
        fichaMedicaDAO.create(fichaMedica8);
        fichaMedicaDAO.create(fichaMedica9);

        ArrayList<Aluno> alunos = alunoDAO.getAllAluno();
        ArrayList<FichaMedica> fichasMedicas = fichaMedicaDAO.getAllFichaMedica();

        imprimeDadosAluno(alunos, fichasMedicas);
        
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

        // Criando Objetos de Professor com Telefones
        Professor professor4 = new Professor("Enrico Maragno", "00011675800", LocalDate.of(2003, 4, 15), 2500);
        Telefone telefone40 = new Telefone(TipoTelefone.Celular, 55, 21, 964695794, 4);
        Telefone telefone41 = new Telefone(TipoTelefone.Celular, 55, 21, 964695790, 4);
        professor4.addTelefone(telefone40);
        professor4.addTelefone(telefone41);

        // Estabelecendo Conexão com o DAO de Professor
        ProfessorDAO professorDAO = new ProfessorDAO(connection);

        // Criando os Objetos de Professor no BD
        professorDAO.createComTelefone(professor1);
        professorDAO.createComTelefone(professor2);
        professorDAO.createComTelefone(professor3);
        professorDAO.createComTelefone(professor4);

        // Criando ArrayList de Professores
        ArrayList<Professor> professores = professorDAO.retriveAllComTelefone();

        // Chamando função de Printar utilizando ArrayList de professores como parâmetro
        imprimeDadosProfessor(professores);


        // Aulas com Alunos e Professores =========================================================

        // Evento evento1 = new Evento(LocalDate.of(2023, 11, 10), "Pagar Contas");
        // Evento evento2 = new Evento(LocalDate.of(2023, 11, 16), "Reunião de Acompanhamento");
        // evento2.addPessoa(pessoa1);
        // evento2.addPessoa(aluno2);
        // Evento evento3 = new Evento(LocalDate.of(2023, 11, 16), "Apresentação dos Resultados");
        // evento3.addPessoa(pessoa1);
        // evento3.addPessoa(aluno2);
        // evento3.addPessoa(aluno3);

        // System.out.println("Comecei a printar os objetos de evento criados em memoria\n");
        // System.out.println(evento1);
        // System.out.println(evento2);
        // System.out.println(evento3);
        // System.out.println("Acabei de printar os objetos de evento em memoria\n\n\n");

        // EventoDAO edao = new EventoDAO(connection);

        // edao.createComPessoa(evento1);
        // edao.createComPessoa(evento2);
        // edao.createComPessoa(evento3);

        // ArrayList<Evento> eventos = edao.retriveAllComPessoaComTelefone();

        // System.out.println("Comecei a printar os objetos de evento criados a partir dos dados do BD\n");
        // for (Evento evento : eventos) {
        //     System.out.println(evento);
        //     for (Aluno Aluno : evento.getPessoas()) {
        //         System.out.println(Aluno);
        //         for (Telefone telefone : Aluno.getTelefones()) {
        //             System.out.println(telefone);
        //         }
        //     }
        // }
        // System.out.println("Acabei de printar os objetos de evento criados a partir dos dados do BD\n\n\n");

        /*
         * //Exemplo de Injection
         * Aluno pessoa10 = new Aluno("%",
         * "%' UNION SELECT cpf FROM Aluno WHERE nome LIKE '%", LocalDate.of(2002, 9,
         * 05));
         * ArrayList<Aluno> pessoas2 = alunoDAO.retrieveInjection(pessoa10);
         * for (Aluno Aluno : pessoas2) {
         * System.out.println(Aluno);
         * }
         */

    }

    // Função para Imprimir os Dados do Aluno juntamente com a Ficha Médica.
    private static void imprimeDadosAluno(ArrayList<Aluno> alunos, ArrayList<FichaMedica> fichasMedicas) {
        for(Aluno a : alunos) {
            System.out.println("======= Dados do Aluno " + a.getId() + " =================================\n");
            System.out.println("\tNome: \t\t\t" +  a.getNome());
            System.out.println("\tCPF: \t\t\t" + a.getCpf());
            System.out.println("\tTelefone: \t\t" + a.getTelefone());
            System.out.println("\tNascimento: \t\t" + a.getDataNascimento());
            System.out.println("\tIdade: \t\t\t" + a.getIdade());
            
            System.out.println("\n\t------------- Ficha Médica ---------------");
            for(FichaMedica f : fichasMedicas) {
                if(f.getIdAluno() == a.getId()) {
                    System.out.println("\tAltura: \t\t" +  f.getAltura());
                    System.out.println("\tPeso: \t\t\t" + f.getPeso());
                    System.out.println("\tIMC: \t\t\t" + f.getImc());
                    System.out.println("\tTipo Sanguíneo: \t" + f.getTipoSanguineo());
                    System.out.println("\tContato de Emergência: \t" + f.getContatoEmergencia());
                    System.out.println("\tData do Exame: \t\t" + f.getDataExame());
                    System.out.println("\t------------------------------------------\n");                    
                }
            }
        }
        System.out.println("==========================================================\n");
    }

    // Função para Imprimir os Dados do Professor juntamente com os Telefones cadastrados.
    private static void imprimeDadosProfessor(ArrayList<Professor> professores) {
        System.out.println("\n==========================================================\n");
        for (Professor professor : professores) {
            System.out.println("Id: \t\t" + professor.getId());
            System.out.println("Nome: \t\t" + professor.getNome());
            System.out.println("CPF: \t\t" + professor.getCpf());
            System.out.println("Nascimento: \t" + professor.getDataNascimento());
            System.out.println("Idade: \t\t" + professor.getIdade());
            System.out.println("Salário: \t" + professor.getSalario());

            ArrayList<Telefone> telefones = professor.getTelefones();
            for (int i = 0; i < telefones.size(); i++) {
                Telefone telefone = telefones.get(i);
                System.out.println("\nTelefone " + (i+1) + ":\t" + telefone);
            }

            System.out.println("\n==========================================================");
        }
    }

    // Função para Imprimir as aulas Cadastradas juntamente com os alunos e professores

}