import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aluno extends Usuario {
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;

    public Aluno(String email, String senha, String nome, String matricula) {
        this(email, senha, nome, matricula, new ArrayList<>());
    }

    public Aluno(String email, String senha, String nome, String matricula, List<Disciplina> disciplinas) {
        super(email, senha);
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = disciplinas;
    }

    public Aluno(String email, String senha) {
        super(email, senha);
        this.disciplinas = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void removerCadastroDisciplina(Disciplina disciplina, Date data) {
        // Implementar lógica de remoção se necessário
    }

    public Boolean validarCadastroDisciplina(Disciplina disciplina) {
        int obrigatorias = 0;
        int optativas = 0;

        for (Disciplina d : disciplinas) {
            if (d.getTipo() == 1) obrigatorias++;
            else if (d.getTipo() == 2) optativas++;
        }

        if (disciplina.getVagasDisponiveis() <= 0) {
            System.out.println("Não há vagas disponíveis na disciplina " + disciplina.getNome());
            return false;
        }

        if (disciplina.getTipo() == 1 && obrigatorias >= 4) {
            System.out.println("Você já está matriculado em 4 disciplinas obrigatórias.");
            return false;
        }

        if (disciplina.getTipo() == 2 && optativas >= 2) {
            System.out.println("Você já está matriculado em 2 disciplinas optativas.");
            return false;
        }

        return true;
    }

    public void visualizarDisciplinasDisponiveis(List<Disciplina> todasDisciplinas) {
        for (Disciplina disciplina : todasDisciplinas) {
            System.out.println("Nome: " + disciplina.getNome() +
                    ", Código: " + disciplina.getCodigo() +
                    ", Vagas Disponíveis: " + disciplina.getVagasDisponiveis());
        }
    }

    public void cadastrarDisciplina(Disciplina disciplina) {
        if (validarCadastroDisciplina(disciplina)) {
            disciplinas.add(disciplina);
            disciplina.reduzirVagasDisponiveis();
            salvarEmArquivo();
            System.out.println("Matrícula na disciplina " + disciplina.getNome() + " realizada com sucesso!");
        } else {
            System.out.println("Não foi possível se matricular na disciplina " + disciplina.getNome() + ".");
        }
    }

    private void salvarEmArquivo() {
        String caminhoCsv = "src/main/resources/usuarios.csv";
        List<String> linhas = new ArrayList<>();
        String linhaAtual;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            while ((linhaAtual = br.readLine()) != null) {
                if (linhaAtual.startsWith(getEmail() + ",")) {
                    String[] campos = linhaAtual.split(",", -1);
                    String disciplinasExistentes = campos.length > 3 ? campos[3] : "";

                    List<String> listaDisciplinas = new ArrayList<>();

                    if (!disciplinasExistentes.isEmpty()) {
                        disciplinasExistentes = disciplinasExistentes.replace("[", "").replace("]", "").trim();
                        if (!disciplinasExistentes.isEmpty()) {
                            String[] disciplinasArray = disciplinasExistentes.split(",\\s*");
                            for (String disciplina : disciplinasArray) {
                                if (!disciplina.isEmpty()) {
                                    listaDisciplinas.add(disciplina);
                                }
                            }
                        }
                    }

                    for (Disciplina disciplina : disciplinas) {
                        if (!listaDisciplinas.contains(disciplina.getCodigo())) {
                            listaDisciplinas.add(disciplina.getCodigo());
                        }
                    }

                    String novaLinha = campos[0] + "," + campos[1] + "," + campos[2] + ",[" + String.join("-", listaDisciplinas) + "]";
                    linhas.add(novaLinha);
                } else {
                    linhas.add(linhaAtual);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reescreve o arquivo com as novas linhas atualizadas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoCsv))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
