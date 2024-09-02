import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema de Matrículas");
        System.out.println("Faça login para começar a navegar");

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        Usuario u = new Usuario(email, senha);
        boolean result = u.realizaLogin(email, senha);
        if (!result) {
            throw new Exception("Usuário e/ou senha incorreto(s)");
        }

        System.out.println("Logado!");
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("Selecione uma opção");
        System.out.println("1 - Cadastro de Aluno em disciplinas");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir o caractere de nova linha

        while (opcao != 0) {
            if (opcao == 1) {
                List<Disciplina> todasDisciplinas = Disciplina.carregarDisciplinas("src/main/resources/disciplinas.csv");

                Aluno aluno = new Aluno(email, senha, "Usuário Um", "123456");

                aluno.visualizarDisciplinasDisponiveis(todasDisciplinas);

                System.out.println("Digite o código da disciplina para se matricular:");
                String codigoDisciplina = scanner.nextLine();

                Disciplina disciplinaParaCadastro = Disciplina.encontrarDisciplinaPorCodigo(todasDisciplinas, codigoDisciplina);

                if (disciplinaParaCadastro != null) {
                    aluno.cadastrarDisciplina(disciplinaParaCadastro);
                } else {
                    System.out.println("Disciplina não encontrada.");
                }
            }

            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir o caractere de nova linha
        }

        scanner.close();
    }
}
