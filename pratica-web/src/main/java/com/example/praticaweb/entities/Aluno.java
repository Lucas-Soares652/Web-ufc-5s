package com.example.praticaweb.entities;

public class Aluno {

    private Long id;
    private int matricula;
    private String nome;
    private String email;

    public Aluno() {
    }

    public Aluno(Long id, int matricula, String nome, String email) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmai(String email) {
        this.email = email;
    }
}
