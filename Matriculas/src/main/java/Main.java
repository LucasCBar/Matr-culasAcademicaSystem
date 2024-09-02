import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Bem-vindo ao Sistema de Matrículas");
//        int opcao = scanner.nextInt();
//        scanner.nextLine();
//
//            System.out.print("Digite o email: ");
//            String email = scanner.nextLine();
//            System.out.print("Digite a senha: ");
//            String senha = scanner.nextLine();
//            Usuario u = new Usuario(email, senha);
//            boolean result = u.realizaLogin(email, senha);
//            System.out.println(result);
//
//        scanner.close();

        List<Disciplina> todasDisciplinas = Disciplina.carregarDisciplinas("src/main/resources/disciplinas.csv");

        Aluno aluno = new Aluno("usuario1@example.com", "senha123", "Usuário Um", "123456");

        aluno.visualizarDisciplinasDisponiveis(todasDisciplinas);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código da disciplina para se matricular:");
        String codigoDisciplina = scanner.nextLine();

        Disciplina disciplinaParaCadastro = Disciplina.encontrarDisciplinaPorCodigo(todasDisciplinas, codigoDisciplina);

        if (disciplinaParaCadastro != null) {
            aluno.cadastrarDisciplina(disciplinaParaCadastro);
        } else {
            System.out.println("Disciplina não encontrada.");
        }

        scanner.close();
    }
}