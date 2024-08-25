package org.example;

import java.util.Date;
import java.util.List;

public class Aluno extends Usuario {
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;

    public Aluno(String email, String senha, String nome, String matricula, List<Disciplina> disciplinas) {
        super(email, senha);
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
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

    public void cadastrarDisciplina(Disciplina disciplina, Date data) {
    }

    public void removerCadastroDisciplina(Disciplina disciplina, Date data) {
    }

    public Boolean validarCadastroDisciplina(List<Disciplina> disciplinas) {
        return false;
    }

    public Boolean pagarSemestre(List<Disciplina> disciplinas) {
        return false;
    }

    @Override
    public void realizarLogin() {
        // Implementar login
    }
}
