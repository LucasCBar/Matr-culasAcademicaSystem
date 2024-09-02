import java.util.List;

public class Professor extends Usuario {
    private String matricula;
    private List<Disciplina> disciplinas;

    public Professor(String email, String senha, String matricula, List<Disciplina> disciplinas) {
        super(email, senha);
        this.matricula = matricula;
        this.disciplinas = disciplinas;
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

    public List<Aluno> listarAlunosDisciplina(Disciplina disciplina) {
        return null; // Implementar listagem de alunos
    }
}
