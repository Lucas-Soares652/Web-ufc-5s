package com.example.praticaweb.repositories;

import com.example.praticaweb.entities.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    Aluno findByMatricula(int matricula);
}
