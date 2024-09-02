package org.example;

import java.util.Date;
import java.util.List;

public class Disciplina {
    private String nome;
    private String codigo;
    private Boolean status;
    private int tipo;
    private List<Aluno> alunos;
    private Professor professor;
    private int capacidadeMaxima = 60;
    private int capacidadeMinima = 3;
    private Date periodoMatricula;
    private Double custo;

    public Disciplina(String nome, String codigo, Boolean status, int tipo, List<Aluno> alunos, Professor professor,
            Date periodoMatricula, Double custo) {
        this.nome = nome;
        this.codigo = codigo;
        this.status = status;
        this.tipo = tipo;
        this.alunos = alunos;
        this.professor = professor;
        this.periodoMatricula = periodoMatricula;
        this.custo = custo;
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

    public Date getPeriodoMatricula() {
        return periodoMatricula;
    }

    public void setPeriodoMatricula(Date periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Boolean validarDisciplina() {
        Date dataAtual = new Date();

        if (dataAtual.after(this.periodoMatricula)) {
            this.status = false;

            if (this.alunos.size() >= this.capacidadeMinima) {

                this.professor = null;

                for (int i = 0; i < this.alunos.size(); i++) {
                    this.alunos.get(i).removerCadastroDisciplina(this, dataAtual);
                }
            }
            return false;
        }

        if (this.alunos.size() >= this.capacidadeMaxima) {
            this.status = false;
            return false;
        }

        this.status = true;
        return true;
    }
}
