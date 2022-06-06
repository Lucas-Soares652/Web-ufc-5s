package com.example.praticaweb.Service;

import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import com.example.praticaweb.exceptionHandler.*;
import com.example.praticaweb.repositories.AlunoRepository;
import com.example.praticaweb.repositories.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;


@Service
public class TurmaService {

    @Autowired
    TurmaRepository repository;

    @Autowired
    AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public Iterable<Turma> findAll() throws Exception{
        try {
            Iterable<Turma> list = repository.findAll();
            if (list == null)
                throw new TurmasNotFoundException();

            return list;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
    public Turma addTurma(Turma newTurma) throws Exception {
        try {
            Turma turma = repository.findByCodigo(newTurma.getCodigo());
            if (turma != null)
                throw new TurmaBadRequestException();

            turma = new Turma();
            turma.setCodigo(newTurma.getCodigo());
            turma.setDisciplina(newTurma.getDisciplina());
            turma.setSemestre(newTurma.getSemestre());
            turma.setAlunos(saveAlunos(newTurma));
            repository.save(turma);

            return turma;

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    private Set<Aluno> saveAlunos(Turma newTurma){
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

    public Turma findByCodigo(int codigo) throws Exception{

        try{
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null){
                throw new TurmaNotFoundException();
            }

            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    public Turma editTurmaByCodigo(int codigo, Turma editTurma) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            turma.setCodigo(editTurma.getCodigo());
            turma.setSemestre(editTurma.getSemestre());
            turma.setDisciplina(editTurma.getDisciplina());
            turma.setAlunos(saveAlunos(editTurma));
            repository.save(turma);

            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    public void deleteTurmaByCodigo(int codigo) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            turma.getAlunos().removeAll(turma.getAlunos());
            repository.save(turma);

            repository.delete(turma);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    public Set<Aluno> findAllAlunosByCodigoTurma(int codigo) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();
            else if(turma.getAlunos().isEmpty()){
                throw new AlunosNotFoundException();
            }

            return turma.getAlunos();
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    public Turma addAlunoByCodigoTurma(int codigo, Aluno aluno) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            if (existAluno(turma, aluno))
                throw new AlunoBadRequestException();

            turma.getAlunos().add(aluno);
            repository.save(turma);
            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    private boolean existAluno(Turma turma, Aluno newAluno){
        Aluno exist = alunoRepository.findByMatricula(newAluno.getMatricula());;
        if (exist == null)
            return false;

        for (Aluno aluno : turma.getAlunos()){
            if (aluno.getMatricula() == newAluno.getMatricula())
                return true;
        }

        return false;
    }

    public Turma deleteAlunoByMatriculaAndCodigoTurma(int codigo, int matricula) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            Aluno aluno = existAlunoByMatricula(turma, matricula);
            if (aluno == null)
                throw new AlunoNotFoundException();

            turma.getAlunos().remove(aluno);
            repository.save(turma);

            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    private Aluno existAlunoByMatricula(Turma turma, int matricula){
        for (Aluno aluno : turma.getAlunos()){
            if (aluno.getMatricula() == matricula)
                return aluno;
        }

        return null;
    }
}
