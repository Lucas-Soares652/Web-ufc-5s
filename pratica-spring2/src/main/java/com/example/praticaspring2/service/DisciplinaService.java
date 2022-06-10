package com.example.praticaspring2.service;

import com.example.praticaspring2.entities.Disciplina;
import com.example.praticaspring2.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    @Transactional(readOnly = true)
    public Disciplina isNullDisciplina(Disciplina newDisciplina){
        Disciplina disciplina = repository.findByCodigo(newDisciplina.getCodigo());
        if (disciplina != null){
            return disciplina;
        }
        return newDisciplina;
    }
}
