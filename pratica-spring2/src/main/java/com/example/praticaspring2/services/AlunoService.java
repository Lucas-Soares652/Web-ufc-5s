package com.example.praticaspring2.services;

import com.example.praticaspring2.entities.Aluno;
import com.example.praticaspring2.entities.Turma;
import com.example.praticaspring2.exceptionHandler.aluno.AlunoBadRequestException;
import com.example.praticaspring2.exceptionHandler.aluno.AlunoBadRequestExceptionTwo;
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
            aluno.setData_nasc(newAluno.getData_nasc());
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
    public Set<Aluno> isNullAlunos(Turma newTurma) throws Exception{
        try {
            Set<Aluno> alunos = new HashSet<>();
            Aluno exist;
            for (Aluno aluno : newTurma.getAlunos()) {
                exist = repository.findByMatricula(aluno.getMatricula());
                if (exist == null)
                    throw new AlunoBadRequestExceptionTwo("Aluno não existe na base de dados", aluno);
                alunos.add(exist);
            }
            return alunos;
        }
        catch (Exception e){
            throw new Exception(e);
        }
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
    public Aluno isNullAluno(Aluno aluno) throws Exception{
        try {
            Aluno exist;
            exist = repository.findByMatricula(aluno.getMatricula());
            if (exist == null)
                throw new AlunoBadRequestException("Aluno não existe na base de dados");
            return exist;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}
