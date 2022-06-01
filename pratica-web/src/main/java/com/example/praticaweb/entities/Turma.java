package com.example.praticaweb.entities;

import java.util.List;

public class Turma {

    private Long id;
    private int codigo;
    private String disciplina;
    private int semestre;
    public List<Aluno> alunos;

    public Turma() {
    }

    public Turma(Long id, int codigo, String disciplina, int semestre, List<Aluno> alunos) {
        this.id = id;
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.semestre = semestre;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno aluno){
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno){
        this.alunos.remove(aluno);
    }
}
