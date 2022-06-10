package com.example.praticaspring2.repositories;

import com.example.praticaspring2.entities.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    Aluno findByMatricula(int matricula);
}
