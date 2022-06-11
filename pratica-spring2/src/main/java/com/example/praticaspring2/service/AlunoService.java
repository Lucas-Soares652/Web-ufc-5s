package com.example.praticaspring2.service;

import com.example.praticaspring2.entities.Aluno;
import com.example.praticaspring2.entities.Turma;
import com.example.praticaspring2.exceptionHandler.aluno.AlunoBadRequestException;
import com.example.praticaspring2.exceptionHandler.aluno.AlunosNotFoundException;
import com.example.praticaspring2.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Transactional
    public Aluno addAluno(Aluno newAluno) throws Exception{
        try {
            Aluno aluno = repository.findByMatricula(newAluno.getMatricula());
            if(aluno != null)
                throw new AlunoBadRequestException("Aluno já cadastrado");

            aluno = new Aluno();
            aluno.setMatricula(newAluno.getMatricula());
            aluno.setNome(newAluno.getNome());
            aluno.setDataNasc(newAluno.getDataNasc());
            aluno.setEmail(newAluno.getEmail());
            aluno.setTelefone(newAluno.getTelefone());
            aluno.setEndereco(newAluno.getEndereco());
            repository.save(aluno);

            return aluno;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Aluno> getAllAlunos() throws Exception{
        try {
            Iterable<Aluno> alunos = repository.findAll();
            if (!alunos.iterator().hasNext())
                throw new AlunosNotFoundException();

            return alunos;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public boolean alunoIsOnTheList(Turma turma, Aluno newAluno){

        for (Aluno aluno : turma.getAlunos()){
            if (aluno.getMatricula() == newAluno.getMatricula())
                return true;
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Aluno isNullAluno(int matricula){
        Aluno aluno = repository.findByMatricula(matricula);
        if (aluno == null){
            return null;
        }
        return aluno;
    }
}
