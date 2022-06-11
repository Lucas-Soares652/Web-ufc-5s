package com.example.praticaweb.services;

import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import com.example.praticaweb.exceptionHandler.*;
import com.example.praticaweb.repositories.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private AlunoService alunoservice;

    @Transactional(readOnly = true)
    public Iterable<Turma> findAll() throws Exception{
        try {
            Iterable<Turma> list = repository.findAll();
            if (!list.iterator().hasNext())
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
            turma.setAlunos(alunoservice.saveAlunos(newTurma));
            repository.save(turma);

            return turma;

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public Turma editTurmaByCodigo(int codigo, Turma editTurma) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            turma.setCodigo(editTurma.getCodigo());
            turma.setSemestre(editTurma.getSemestre());
            turma.setDisciplina(editTurma.getDisciplina());
            turma.setAlunos(alunoservice.saveAlunos(editTurma));
            repository.save(turma);

            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
    public void deleteTurmaByCodigo(int codigo) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            turma.getAlunos().removeAll(turma.getAlunos());

            repository.delete(turma);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public Turma addAlunoByCodigoTurma(int codigo, Aluno aluno) throws Exception{
        try {
            Turma turma = repository.findByCodigo(codigo);
            if (turma == null)
                throw new TurmaNotFoundException();

            if (alunoservice.alunoIsOnTheList(turma, aluno))
                throw new AlunoBadRequestException();

            Aluno student = alunoservice.isNullAluno(aluno.getMatricula());
            if (student != null){
                turma.getAlunos().add(student);
                repository.save(turma);
                return turma;
            }

            turma.getAlunos().add(aluno);
            repository.save(turma);
            return turma;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
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