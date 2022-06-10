package com.example.praticaspring2.service;

import com.example.praticaspring2.entities.Aluno;
import com.example.praticaspring2.entities.Turma;
import com.example.praticaspring2.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;



    public Set<Aluno> isNullAlunos(Turma newTurma){
        Set<Aluno> alunos = new HashSet<>();
        Aluno exist;
        for (Aluno aluno : newTurma.getAlunos()){
            exist = repository.findByMatricula(aluno.getMatricula());
            if (exist != null) {
                alunos.add(exist);
                continue;
            }
            alunos.add(aluno);
        }
        return alunos;
    }

    public boolean alunoIsOnTheList(Turma turma, Aluno newAluno){

        for (Aluno aluno : turma.getAlunos()){
            if (aluno.getMatricula() == newAluno.getMatricula())
                return true;
        }

        return false;
    }

    public Aluno isNullAluno(int matricula){
        Aluno aluno = repository.findByMatricula(matricula);
        if (aluno == null){
            return null;
        }
        return aluno;
    }
}
