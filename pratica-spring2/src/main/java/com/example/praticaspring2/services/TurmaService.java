package com.example.praticaspring2.services;

import com.example.praticaspring2.entities.Aluno;
import com.example.praticaspring2.entities.Turma;
import com.example.praticaspring2.exceptionHandler.aluno.AlunoBadRequestException;
import com.example.praticaspring2.exceptionHandler.turma.TurmaBadRequestException;
import com.example.praticaspring2.exceptionHandler.turma.TurmaNotFoundException;
import com.example.praticaspring2.exceptionHandler.turma.TurmasNotFoundException;
import com.example.praticaspring2.repositories.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private DateService dateService;

    @Transactional
    public Turma addTurma(Turma newTurma) throws Exception{
        try {
            Turma turma = repository.findByCodigo(newTurma.getCodigo());
            if (turma != null)
                throw new TurmaBadRequestException();

            turma = new Turma();
            turma.setCodigo(newTurma.getCodigo());
            turma.setDisciplina(disciplinaService.isNullDisciplina(newTurma.getDisciplina()));
            turma.setAlunos(alunoService.isNullAlunos(newTurma));
            turma.setHorarios(dateService.isExistsDate(newTurma.getHorarios()));
            repository.save(turma);

            return turma;

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Turma> getAllTurmas() throws Exception{
        try {
            Iterable<Turma> turmas = repository.findAll();
            if (!turmas.iterator().hasNext())
                throw new TurmasNotFoundException();

            return turmas;

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
    public Turma addAlunoByTurmaId(Long id, Aluno newAluno) throws Exception{
        try {
            Optional<Turma> turma = repository.findById(id);
            if (turma.isEmpty())
                throw new TurmaNotFoundException();
            if (alunoService.alunoIsOnTheList(turma.get(), newAluno))
                throw new AlunoBadRequestException("Aluno já está matriculado na turma");

            turma.get().getAlunos().add(alunoService.isNullAluno(newAluno));
            repository.save(turma.get());

            return turma.get();

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}
