import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private String codigo;
    private Boolean status;
    private int tipo;
    private List<Aluno> alunos;
    private Professor professor;
    private int capacidadeMaxima;
    private int capacidadeMinima;
    private String periodoMatricula;
    private Double custo;
    private int vagasDisponiveis;

    public Disciplina(String nome, String codigo, Boolean status, int tipo, int capacidadeMaxima, int capacidadeMinima, String periodoMatricula, Double custo, int vagasDisponiveis) {
        this.nome = nome;
        this.codigo = codigo;
        this.status = status;
        this.tipo = tipo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeMinima = capacidadeMinima;
        this.periodoMatricula = periodoMatricula;
        this.custo = custo;
        this.alunos = new ArrayList<>();
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getCapacidadeMinima() {
        return capacidadeMinima;
    }

    public void setCapacidadeMinima(int capacidadeMinima) {
        this.capacidadeMinima = capacidadeMinima;
    }

    public String getPeriodoMatricula() {
        return periodoMatricula;
    }

    public void setPeriodoMatricula(String periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Boolean validarDisciplina() {
        return null; // Implementar validação de disciplina
    }

    public static List<Disciplina> lerDisciplinas(String caminhoCsv) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            // Pular o cabeçalho
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                String nome = dados[0];
                String codigo = dados[1];
                Boolean status = Boolean.parseBoolean(dados[2]);
                int tipo = Integer.parseInt(dados[3]);
                int capacidadeMaxima = Integer.parseInt(dados[4]);
                int capacidadeMinima = Integer.parseInt(dados[5]);
                String periodoMatricula = dados[6];
                Double custo = Double.parseDouble(dados[7]);
                int vagasDisponiveis = Integer.parseInt(dados[8]);

                disciplinas.add(new Disciplina(nome, codigo, status, tipo, capacidadeMaxima, capacidadeMinima, periodoMatricula, custo, vagasDisponiveis));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void reduzirVagasDisponiveis() {
        if (vagasDisponiveis > 0) {
            vagasDisponiveis--;
            atualizarVagasNoCSV();
        }
    }

    private void atualizarVagasNoCSV() {
        String caminhoArquivo = "src/main/resources/disciplinas.csv";
        List<String> linhasAtualizadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",");
                if (valores[1].equals(this.codigo)) {
                    valores[valores.length - 1] = String.valueOf(this.vagasDisponiveis);
                    linha = String.join(",", valores);
                }
                linhasAtualizadas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhasAtualizadas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Disciplina> carregarDisciplinas(String caminhoCsv) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                String nome = dados[0];
                String codigo = dados[1];
                Boolean status = Boolean.parseBoolean(dados[2]);
                int tipo = Integer.parseInt(dados[3]);
                int capacidadeMaxima = Integer.parseInt(dados[4]);
                int capacidadeMinima = Integer.parseInt(dados[5]);
                String periodoMatricula = dados[6];
                Double custo = Double.parseDouble(dados[7]);
                int vagasDisponiveis = Integer.parseInt(dados[8]);

                disciplinas.add(new Disciplina(nome, codigo, status, tipo, capacidadeMaxima, capacidadeMinima, periodoMatricula, custo, vagasDisponiveis));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }

    public static Disciplina encontrarDisciplinaPorCodigo(List<Disciplina> disciplinas, String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }
}

