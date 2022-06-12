package com.example.praticaspring2.services;

import com.example.praticaspring2.entities.Disciplina;
import com.example.praticaspring2.exceptionHandler.disciplina.DisciplinaBadRequestException;
import com.example.praticaspring2.exceptionHandler.disciplina.DisciplinasNotFoundException;
import com.example.praticaspring2.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    @Transactional
    public Disciplina addDisciplina(Disciplina newDisciplina) throws Exception{
        try {
            Disciplina disciplina = repository.findByCodigo(newDisciplina.getCodigo());
            if (disciplina != null)
                throw new DisciplinaBadRequestException("Disciplina já existe");

            disciplina = new Disciplina();
            disciplina.setCodigo(newDisciplina.getCodigo());
            disciplina.setCreditos(newDisciplina.getCreditos());
            disciplina.setNome(newDisciplina.getNome());
            repository.save(disciplina);

            return disciplina;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Disciplina> getAllDisciplinas() throws Exception{
        try {
            Iterable<Disciplina> disciplinas = repository.findAll();
            if (!disciplinas.iterator().hasNext())
                throw new DisciplinasNotFoundException();

            return disciplinas;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Disciplina isNullDisciplina(Disciplina newDisciplina) throws Exception{
        try{
            Disciplina disciplina = repository.findByCodigo(newDisciplina.getCodigo());
            if (disciplina == null)
                throw new DisciplinaBadRequestException("Disciplina não existe");

            return disciplina;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}