package com.example.praticaweb.repositories;

import com.example.praticaweb.entities.Turma;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TurmaRepository extends CrudRepository<Turma, Long> {
    Turma findByCodigo(int codigo);
    @Query(value = "SELECT * FROM turma "
            + "WHERE disciplina like ?1%", nativeQuery = true)
    Iterable<Turma> findByDisciplina(String disciplina);
}
