package com.example.praticaspring2.repositories;

import com.example.praticaspring2.entities.Turma;

import org.springframework.data.repository.CrudRepository;

public interface TurmaRepository extends CrudRepository<Turma, Long> {
    Turma findByCodigo(int codigo);
}
