package com.example.praticaspring2.repositories;

import com.example.praticaspring2.entities.Horario;
import org.springframework.data.repository.CrudRepository;

public interface HorarioRepository extends CrudRepository<Horario, Long> {
    Horario findByHorario(java.util.Date horario);
}
