package com.example.praticaspring2.repositories;

import com.example.praticaspring2.entities.Disciplina;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    Disciplina findByCodigo(int codigo);
}
