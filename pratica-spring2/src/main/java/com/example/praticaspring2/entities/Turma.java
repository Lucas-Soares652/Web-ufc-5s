package com.example.praticaspring2.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    @ManyToMany(cascade = { CascadeType.ALL })
    private Set<Aluno> alunos = new HashSet<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    private Set<Horario> horarios = new HashSet<>();

    public Turma(){}

    public Long getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }
}
