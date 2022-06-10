package com.example.praticaweb.repositories;

import com.example.praticaweb.entities.Turma;

import org.springframework.data.repository.CrudRepository;

public interface TurmaRepository extends CrudRepository<Turma, Long> {
    Turma findByCodigo(int codigo);
}
