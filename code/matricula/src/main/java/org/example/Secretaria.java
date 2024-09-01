package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Secretaria extends Usuario {
    private String local;
    private int id;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> diciplinas;

    public Secretaria(String email, String senha, String local, int id) {
        super(email, senha);
        this.local = local;
        this.id = id;
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.diciplinas = new ArrayList<>();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Disciplina> getDiciplinas() {
        return diciplinas;
    }

    public void setDiciplinas(List<Disciplina> diciplinas) {
        this.diciplinas = diciplinas;
    }

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void cadastrarDisciplina(Disciplina disciplina) {
        diciplinas.add(disciplina);
    }

    public void definirPrazoMatricula(Date prazo) {
    }

    public String gerarCurriculo() {
        return "";
    }

    @Override
    public boolean realizarLogin(String email, String senha) {
        return this.getEmail().equals(email) && this.getSenha().equals(senha);
    }
}
