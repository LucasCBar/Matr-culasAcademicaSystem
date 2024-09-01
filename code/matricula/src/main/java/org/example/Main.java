
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Secretaria secretaria = new Secretaria("admin@admin.com", "123", "Bloco A", 1);

        secretaria.cadastrarAluno(new Aluno("aluno@teste.com", "senhaAluno", "Aluno Teste", "12345", new ArrayList<>()));
        secretaria.cadastrarProfessor(new Professor("professor@teste.com", "senhaProfessor", "67890", new ArrayList<>()));
        secretaria.cadastrarDisciplina(new Disciplina("Matemática", "MAT101", true, 150.0));

        Scanner scanner = new Scanner(System.in);

        Usuario usuarioAtual = realizarLogin(secretaria, scanner);
        if (usuarioAtual == null) {
            System.out.println("Login falhou. Saindo...");
            scanner.close();
            return;
        }

        while (true) {
            if (usuarioAtual instanceof Secretaria) {
                mostrarMenuSecretaria();
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarAluno(secretaria, scanner);
                        break;
                    case 2:
                        cadastrarProfessor(secretaria, scanner);
                        break;
                    case 3:
                        cadastrarDisciplina(secretaria, scanner);
                        break;
                    case 4:
                        matricularAlunoEmDisciplina(secretaria, scanner);
                        break;
                    case 5:
                        listarAlunosPorDisciplina(secretaria, scanner);
                        break;
                    case 6:
                        validarDisciplinas(secretaria);
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } else if (usuarioAtual instanceof Aluno) {
                mostrarMenuAluno();
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 4) {
                    matricularAlunoEmDisciplina(secretaria, scanner);
                } else if (opcao == 5) {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                } else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            } else if (usuarioAtual instanceof Professor) {
                mostrarMenuProfessor();
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 5) {
                    listarAlunosPorDisciplina(secretaria, scanner);
                } else if (opcao == 6) {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                } else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }
        }
    }

    private static Usuario realizarLogin(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (secretaria.realizarLogin(email, senha)) {
            return secretaria;
        }

        Aluno aluno = secretaria.getAlunos().stream()
                .filter(a -> a.getEmail().equals(email) && a.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (aluno != null) {
            return aluno;
        }

        Professor professor = secretaria.getProfessores().stream()
                .filter(p -> p.getEmail().equals(email) && p.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (professor != null) {
            return professor;
        }

        return null;
    }

    private static void mostrarMenuSecretaria() {
        System.out.println("----- Menu do Sistema -----");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Cadastrar Disciplina");
        System.out.println("4. Matricular Aluno em Disciplina");
        System.out.println("5. Listar Alunos por Disciplina");
        System.out.println("6. Validar Disciplinas");
        System.out.println("7. Sair");
    }

    private static void mostrarMenuAluno() {
        System.out.println("----- Menu do Aluno -----");
        System.out.println("4. Matricular Aluno em Disciplina");
        System.out.println("5. Sair");
    }

    private static void mostrarMenuProfessor() {
        System.out.println("----- Menu do Professor -----");
        System.out.println("5. Listar Alunos por Disciplina");
        System.out.println("6. Sair");
    }

    private static void cadastrarAluno(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do aluno: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = new Aluno(email, senha, nome, matricula, new ArrayList<>());
        secretaria.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void cadastrarProfessor(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o email do professor: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do professor: ");
        String senha = scanner.nextLine();
        System.out.print("Digite a matrícula do professor: ");
        String matricula = scanner.nextLine();

        Professor professor = new Professor(email, senha, matricula, new ArrayList<>());
        secretaria.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void cadastrarDisciplina(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o código da disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o custo da disciplina: ");
        Double custo = scanner.nextDouble();
        scanner.nextLine();

        Disciplina disciplina = new Disciplina(nome, codigo, true, custo);
        secretaria.cadastrarDisciplina(disciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private static void matricularAlunoEmDisciplina(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine();
        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();

        Aluno aluno = secretaria.getAlunos().stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        Disciplina disciplina = secretaria.getDiciplinas().stream()
                .filter(d -> d.getCodigo().equals(codigoDisciplina))
                .findFirst()
                .orElse(null);

        if (aluno != null && disciplina != null) {
            aluno.getDisciplinas().add(disciplina);
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("Aluno ou disciplina não encontrados.");
        }
    }

    private static void listarAlunosPorDisciplina(Secretaria secretaria, Scanner scanner) {
        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();

        Disciplina disciplina = secretaria.getDiciplinas().stream()
                .filter(d -> d.getCodigo().equals(codigoDisciplina))
                .findFirst()
                .orElse(null);

        if (disciplina != null) {
            System.out.println("Alunos matriculados na disciplina " + disciplina.getNome() + ":");
            for (Aluno aluno : secretaria.getAlunos()) {
                if (aluno.getDisciplinas().contains(disciplina)) {
                    System.out.println(aluno.getNome() + " - " + aluno.getEmail());
                }
            }
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void validarDisciplinas(Secretaria secretaria) {
        System.out.println("Disciplinas válidas:");
        for (Disciplina disciplina : secretaria.getDiciplinas()) {
            if (disciplina.validarDisciplina()) {
                System.out.println(disciplina.getNome() + " - " + disciplina.getCodigo());
            }
        }
    }
}
