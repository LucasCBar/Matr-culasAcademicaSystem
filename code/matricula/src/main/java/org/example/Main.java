package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Secretaria secretaria = new Secretaria("Bloco A", 1);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Menu do Sistema -----");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Disciplina");
            System.out.println("4. Matricular Aluno em Disciplina");
            System.out.println("5. Listar Alunos por Disciplina");
            System.out.println("6. Validar Disciplinas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

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
        }
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
        scanner.nextLine(); // Limpar o buffer

        // Para simplificação, o professor da disciplina será o primeiro da lista
        Professor professor = secretaria.getProfessores().isEmpty() ? null : secretaria.getProfessores().get(0);
        Disciplina disciplina = new Disciplina(nome, codigo, true, 1, new ArrayList<>(), professor, new Date(), custo);
        secretaria.cadastrarDisciplina(disciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private static void matricularAlunoEmDisciplina(Secretaria secretaria, Scanner scanner) {
        if (secretaria.getAlunos().isEmpty() || secretaria.getDiciplinas().isEmpty()) {
            System.out.println("Não há alunos ou disciplinas cadastrados para realizar a matrícula.");
            return;
        }

        System.out.print("Digite o email do aluno para matrícula: ");
        String emailAluno = scanner.nextLine();
        Aluno aluno = secretaria.getAlunos().stream()
                .filter(a -> a.getEmail().equals(emailAluno))
                .findFirst()
                .orElse(null);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = secretaria.getDiciplinas().stream()
                .filter(d -> d.getCodigo().equals(codigoDisciplina))
                .findFirst()
                .orElse(null);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        // Usando o método existente de Aluno para cadastrar em uma disciplina
        aluno.cadastrarDisciplina(disciplina, new Date());
        System.out.println("Aluno matriculado com sucesso na disciplina " + disciplina.getNome());
    }

    private static void listarAlunosPorDisciplina(Secretaria secretaria, Scanner scanner) {
        if (secretaria.getDiciplinas().isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }

        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = secretaria.getDiciplinas().stream()
                .filter(d -> d.getCodigo().equals(codigoDisciplina))
                .findFirst()
                .orElse(null);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        // Usando o método de Professor para listar alunos de uma disciplina
        Professor professor = disciplina.getProfessor();
        if (professor == null) {
            System.out.println("Nenhum professor atribuído a esta disciplina.");
            return;
        }

        List<Aluno> alunos = professor.listarAlunosDisciplina(disciplina);
        if (alunos == null || alunos.isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta disciplina.");
        } else {
            System.out.println("Alunos matriculados na disciplina " + disciplina.getNome() + ":");
            for (Aluno aluno : alunos) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }

    private static void validarDisciplinas(Secretaria secretaria) {
        for (Disciplina disciplina : secretaria.getDiciplinas()) {
            // Usando o método de Disciplina para validar se a disciplina será oferecida
            Boolean isValid = disciplina.validarDisciplina();
            if (isValid != null && isValid) {
                System.out.println("Disciplina " + disciplina.getNome() + " validada para o próximo semestre.");
            } else {
                System.out.println("Disciplina " + disciplina.getNome() + " cancelada devido ao número insuficiente de alunos.");
            }
        }
    }
}
