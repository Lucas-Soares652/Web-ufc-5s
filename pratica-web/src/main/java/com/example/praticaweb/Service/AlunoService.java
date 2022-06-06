package com.example.praticaweb.Service;

import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import com.example.praticaweb.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Set<Aluno> saveAlunos(Turma newTurma){
        Set<Aluno> alunos = new HashSet<>();
        Aluno exist;
        for (Aluno aluno : newTurma.getAlunos()){
            exist = alunoRepository.findByMatricula(aluno.getMatricula());
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
        Aluno aluno = alunoRepository.findByMatricula(matricula);
        if (aluno == null){
            return null;
        }
        return aluno;
    }
}
